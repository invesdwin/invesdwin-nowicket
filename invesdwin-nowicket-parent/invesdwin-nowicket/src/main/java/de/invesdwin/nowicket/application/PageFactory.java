package de.invesdwin.nowicket.application;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.apache.commons.collections4.map.LRUMap;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.PageReference;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.page.CouldNotLockPageException;
import org.apache.wicket.page.PageAccessSynchronizer;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.internal.ModelCacheUsingPageFactory;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.collections.fast.concurrent.ASynchronizedFastIterableDelegateList;
import de.invesdwin.util.lang.Strings;

@ThreadSafe
public final class PageFactory implements Serializable {

    private static final int MAX_PAGE_REFERENCES_PER_MODEL = 10;
    private static final int MAX_MODELS_PER_CLASS = 100;
    private static final String PAGE_CLASS_MODEL_SUFFIX = Page.class.getSimpleName();
    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(PageFactory.class);
    private static final MetaDataKey<PageFactory> KEY_PAGE_FACTORY = new MetaDataKey<PageFactory>() {
    };
    /*
     * need to store a list of page references since the equals/hashcode might result in multiple pages being valid to
     * be stored
     * 
     * to prevent hashcode clashes, here we separate the maps by layer of class + object hashcode instead of combining
     * those in a Pair key
     * 
     * need to use map here instead of LoadingCacheMap since we need a serializable instance
     */
    @GuardedBy("modelClass_modelObjectHashCode_pageReferences" /* self on each level */)
    private final Map<Class<?>, Map<Integer, List<PageReferenceAndModel>>> modelClass_modelObjectHashCode_pageReferences = new LinkedHashMap<Class<?>, Map<Integer, List<PageReferenceAndModel>>>();

    private PageFactory() {}

    public static PageFactory get() {
        PageFactory pageFactory = AWebSession.get().getMetaData(KEY_PAGE_FACTORY);
        if (pageFactory == null) {
            pageFactory = new PageFactory();
            AWebSession.get().setMetaData(KEY_PAGE_FACTORY, pageFactory);
        }
        return pageFactory;
    }

    public Page getPage(final Object modelObject) {
        Page usedPage = getRegisteredPage(modelObject);
        if (usedPage == null) {
            usedPage = newPage(modelObject);
        } else {
            //only update here when using registeredPage
            ModelCacheUsingPageFactory.postProcessUsedPage(modelObject, usedPage);
        }
        //execute guitasks that were created for the new page on the used page
        GuiTasksHolder.get(usedPage).setGuiTasks(GuiTasksHolder.get().getGuiTasks());
        return usedPage;
    }

    private Page newPage(final Object modelObject) {
        final Class<Page> pageClass = findPageClass(modelObject.getClass());
        Assertions.assertThat(pageClass)
                .as("Unable to find any %s for model class [%s] or any of its super classes!", PAGE_CLASS_MODEL_SUFFIX,
                        modelObject.getClass().getName())
                .isNotNull();
        try {
            final Constructor<Page> constructor = pageClass.getConstructor(IModel.class);
            return constructor.newInstance(Model.of((Serializable) modelObject));
        } catch (final NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException e) {
            try {
                final Constructor<Page> constructor = pageClass.getConstructor(modelObject.getClass());
                return constructor.newInstance(modelObject);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e1) {
                LOG.catching(e);
                throw new RuntimeException(e1);
            }
        } catch (final InvocationTargetException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                //page constructor might throw redirect exception that needs to be handled by wicket properly
                throw (RuntimeException) cause;
            } else {
                throw new RuntimeException(cause);
            }
        }
    }

    public Class<Page> findPageClass(final Object modelObject) {
        if (modelObject == null) {
            return null;
        } else {
            return findPageClass(modelObject.getClass());
        }
    }

    @SuppressWarnings("unchecked")
    public Class<Page> findPageClass(final Class<?> modelClass) {
        if (modelClass == null) {
            return null;
        }
        try {
            String relModelPath = modelClass.getName();
            final String modelNameSuffix = ModelClassContext.extractModelNameSuffix(modelClass);
            if (Strings.isNotBlank(modelNameSuffix)) {
                relModelPath = Strings.removeEnd(relModelPath, modelNameSuffix);
            }
            return (Class<Page>) Class.forName(relModelPath + PAGE_CLASS_MODEL_SUFFIX);
        } catch (final ClassNotFoundException e) {
            return findPageClass(modelClass.getSuperclass());
        }
    }

    public Page getRegisteredPage(final Object modelObject) {
        final List<PageReferenceAndModel> pageReferences = getPageReferencesForModel(modelObject);
        //need to check all pageReferences to find the one that matches
        for (final PageReferenceAndModel existingPageReference : pageReferences) {
            try {
                //see: http://mail-archives.apache.org/mod_mbox/wicket-users/201211.mbox/%3CCANgwjP4xsMKo6kKjVSOOnf_qKvdV+nbhXh8bkZ0R6oZN1BS8YA@mail.gmail.com%3E
                final PageAccessSynchronizer pageAccessSynchronizer = new PageAccessSynchronizer(
                        org.apache.wicket.util.time.Duration.ONE_SECOND);
                pageAccessSynchronizer.lockPage(existingPageReference.getPageId());
                try {
                    final Page existingPage = existingPageReference.getPage();
                    /*
                     * relying on proper equals here, keeping the old model since otherwise user input could get lost if
                     * those attributes were not relevant for equals/hashCode
                     */
                    if (existingPage != null && modelObject.equals(existingPage.getDefaultModelObject())) {
                        existingPageReference.restoreLatestModel(existingPage);
                        return existingPage;
                    }
                } finally {
                    pageAccessSynchronizer.unlockPage(existingPageReference.getPageId());
                }
                //CHECKSTYLE:OFF
            } catch (final CouldNotLockPageException e) {
                //CHECKSTYLE:ON
                //ignore, check the other pages or create a new instance in the worst case
            }
        }
        return null;
    }

    private List<PageReferenceAndModel> getPageReferencesForModel(final Object modelObject) {
        final Class<?> modelClass = modelObject.getClass();
        final int modelObjectHashCode = modelObject.hashCode();
        Map<Integer, List<PageReferenceAndModel>> modelObjectHashCode_pageReferences;
        synchronized (modelClass_modelObjectHashCode_pageReferences) {
            modelObjectHashCode_pageReferences = modelClass_modelObjectHashCode_pageReferences.get(modelClass);
            if (modelObjectHashCode_pageReferences == null) {
                modelObjectHashCode_pageReferences = new LRUMap<Integer, List<PageReferenceAndModel>>(
                        MAX_MODELS_PER_CLASS);
                modelClass_modelObjectHashCode_pageReferences.put(modelClass, modelObjectHashCode_pageReferences);
            }
        }
        List<PageReferenceAndModel> list;
        synchronized (modelObjectHashCode_pageReferences) {
            list = modelObjectHashCode_pageReferences.get(modelObjectHashCode);
            if (list == null) {
                //prevent concurrent modification exception
                list = new ASynchronizedFastIterableDelegateList<PageReferenceAndModel>() {
                    @Override
                    protected List<PageReferenceAndModel> newDelegate() {
                        return new ArrayList<>();
                    }
                };
                modelObjectHashCode_pageReferences.put(modelObjectHashCode, list);
            }
        }
        //remove LRU
        while (list.size() > MAX_PAGE_REFERENCES_PER_MODEL) {
            list.remove(0);
        }
        return list;
    }

    public void updatePage(final Page page) {
        final Object modelObject = page.getDefaultModelObject();
        if (modelObject == null || ModelCacheUsingPageFactory.isCacheDisabledForPage(page)) {
            return;
        }
        // clean up existing page references if they are equal to the new one
        final List<PageReferenceAndModel> pageReferences = getPageReferencesForModel(modelObject);
        for (final PageReferenceAndModel existingPageReference : pageReferences) {
            try {
                //see: http://mail-archives.apache.org/mod_mbox/wicket-users/201211.mbox/%3CCANgwjP4xsMKo6kKjVSOOnf_qKvdV+nbhXh8bkZ0R6oZN1BS8YA@mail.gmail.com%3E
                final PageAccessSynchronizer pageAccessSynchronizer = new PageAccessSynchronizer(
                        org.apache.wicket.util.time.Duration.ONE_SECOND);
                pageAccessSynchronizer.lockPage(existingPageReference.getPageId());
                try {
                    final Page existingPage = existingPageReference.getPage();
                    //remove if page reference is lost or if its an equal model (which might be outdated)
                    if (existingPage == null || modelObject.equals(existingPage.getDefaultModelObject())) {
                        pageReferences.remove(existingPageReference);
                    }
                } finally {
                    pageAccessSynchronizer.unlockPage(existingPageReference.getPageId());
                }
                //CHECKSTYLE:OFF
            } catch (final CouldNotLockPageException e) {
                //CHECKSTYLE:ON
                //ignore, next call might clean up the existing references
            }
        }
        pageReferences.add(new PageReferenceAndModel(page));
    }

    /**
     * store updated model so next retrieval returns newest model despite ajax requests updating it
     */
    private static class PageReferenceAndModel implements Serializable {

        private final PageReference pageReference;

        //TODO maybe serialize model to disk? since pagestore does not help any here; ignoring until there are memory problems
        private final Object modelObject;

        PageReferenceAndModel(final Page page) {
            this.pageReference = page.getPageReference();
            this.modelObject = page.getDefaultModelObject();
            Assertions.assertThat(modelObject).isNotNull();
        }

        public int getPageId() {
            return pageReference.getPageId();
        }

        public Page getPage() {
            return pageReference.getPage();
        }

        public void restoreLatestModel(final Page page) {
            page.setFreezePageId(true);
            page.setDefaultModelObject(null);
            page.setDefaultModelObject(modelObject);
            page.setFreezePageId(false);
        }

    }

}
