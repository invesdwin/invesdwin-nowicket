package de.invesdwin.nowicket.application.filter.internal;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.IPageFactory;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.ext.XLogger.Level;

import de.invesdwin.nowicket.application.IPageFactoryHook;
import de.invesdwin.nowicket.application.PageFactory;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;
import de.invesdwin.nowicket.util.RequestCycles;

@ThreadSafe
public class ModelCacheUsingPageFactory implements IPageFactory {

    public static final MetaDataKey<Boolean> NO_CACHE_KEY = new MetaDataKey<Boolean>() {
    };
    public static final String NO_CACHE_PARAM = ModelCacheUsingPageFactory.class.getSimpleName() + "_NO_CACHE";

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory
            .getXLogger(ModelCacheUsingPageFactory.class);
    private final IPageFactory delegate;

    public ModelCacheUsingPageFactory(final IPageFactory delegate) {
        this.delegate = delegate;
    }

    public static <C extends IRequestablePage> C postProcessUsedPage(final Object newModelObject, final C usedPage) {
        if (usedPage instanceof Page) {
            final Page cUsedPage = (Page) usedPage;
            //execute guitasks that were created for the new page on the used page
            RequestCycles.setPage(cUsedPage);
            if (cUsedPage.getDefaultModelObject() != null && usedPage instanceof IPageFactoryHook) {
                final IPageFactoryHook hook = (IPageFactoryHook) usedPage;
                hook.onPageModelRefresh(newModelObject);
            }
        }
        return usedPage;
    }

    @Override
    public <C extends IRequestablePage> C newPage(final Class<C> pageClass, final PageParameters parameters) {
        final C newPage = delegate.newPage(pageClass, parameters);
        final C usedPage = tryPageFactoryCache(newPage);
        usedPage.getPageParameters().mergeWith(newPage.getPageParameters()); //update params
        return postProcessUsedPage(extractNewModelObject(newPage), usedPage);
    }

    @Override
    public <C extends IRequestablePage> C newPage(final Class<C> pageClass) {
        final C newPage = delegate.newPage(pageClass);
        final C usedPage = tryPageFactoryCache(newPage);
        usedPage.getPageParameters().mergeWith(newPage.getPageParameters()); //update params
        return postProcessUsedPage(extractNewModelObject(newPage), usedPage);
    }

    private Object extractNewModelObject(final IRequestablePage newPage) {
        if (newPage instanceof Page) {
            final Page cNewPage = (Page) newPage;
            return cNewPage.getDefaultModelObject();
        } else {
            return null;
        }
    }

    private <C extends IRequestablePage> C tryPageFactoryCache(final C newPage) {
        final C usedPage = tryPageFactoryCacheInternal(newPage);
        /*
         * copy gui tasks from the constructor of the newly created page to the cached page instance for execution
         */
        if (usedPage != newPage && usedPage instanceof Page && newPage instanceof Page) {
            final Page cUsedPage = (Page) usedPage;
            final Page cNewPage = (Page) newPage;
            GuiTasksHolder.get(cUsedPage).setGuiTasks(GuiTasksHolder.get(cNewPage).getGuiTasks());
        }
        return usedPage;
    }

    @SuppressWarnings("unchecked")
    private <C extends IRequestablePage> C tryPageFactoryCacheInternal(final C newPage) {
        if (newPage instanceof Page) {
            final Page cNewPage = (Page) newPage;
            if (isCacheDisabledForPage(cNewPage)) {
                //copy gui tasks to execute on the new page
                GuiTasksHolder.get(cNewPage).setGuiTasks(GuiTasksHolder.get().getGuiTasks());
                return newPage;
            }
            final Object modelObject = cNewPage.getDefaultModelObject();
            if (modelObject != null) {
                try {
                    final Page page = PageFactory.get().getRegisteredPage(modelObject);
                    if (page != null) {
                        return (C) page;
                    }
                } catch (final Throwable t) {
                    LOG.catching(Level.WARN, new RuntimeException(
                            "Not a generated model page or no suitable constructor available, falling back to default created page.",
                            t));
                    return newPage;
                }
            }
        }
        return newPage;
    }

    public static boolean isCacheDisabledForPage(final Page newPage) {
        if (BooleanUtils.isTrue(newPage.getMetaData(NO_CACHE_KEY))) {
            return true;
        }
        final PageParameters pageParameters = newPage.getPageParameters();
        final String noCacheStr = pageParameters.get(NO_CACHE_PARAM).toString();
        if (noCacheStr != null) {
            final boolean noCache = BooleanUtils.toBoolean(noCacheStr);
            if (noCache) {
                newPage.setMetaData(NO_CACHE_KEY, true);
            }
            pageParameters.remove(NO_CACHE_PARAM);
            return noCache;
        }
        return false;
    }

    @Override
    public <C extends IRequestablePage> boolean isBookmarkable(final Class<C> pageClass) {
        return delegate.isBookmarkable(pageClass);
    }

    public static RenderPageRequestHandler onStalePageException() {
        final IPageFactory pageFactory = AWebApplication.get().getPageFactory();
        final Page page = RequestCycles.getPage();
        final PageParameters pageParameters = new PageParameters(page.getPageParameters());
        if (!pageParameters.getNamedKeys().contains(NO_CACHE_PARAM)) {
            pageParameters.add(NO_CACHE_PARAM, true);
        }
        final Page newPage = pageFactory.newPage(page.getClass(), pageParameters);
        return new RenderPageRequestHandler(new PageProvider(newPage));
    }
}
