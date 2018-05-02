package de.invesdwin.nowicket.application.filter.internal;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.IPageFactory;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxNewWindowNotifyingBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.crypt.Base64;
import org.slf4j.ext.XLogger.Level;

import de.invesdwin.nowicket.application.IPageFactoryHook;
import de.invesdwin.nowicket.application.PageFactory;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;
import de.invesdwin.util.lang.Objects;

@ThreadSafe
public class ModelCacheUsingPageFactory implements IPageFactory {

    private static final String PAGE_PARAM_NO_CACHE = ModelCacheUsingPageFactory.class.getSimpleName() + "_NO_CACHE";

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
            GuiTasksHolder.setPage(cUsedPage);
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
            if (!shouldUsePageFactoryCache(cNewPage)) {
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

    private boolean shouldUsePageFactoryCache(final Page newPage) {
        final PageParameters pageParameters = newPage.getPageParameters();
        final String noCacheStr = pageParameters.get(PAGE_PARAM_NO_CACHE).toString();
        final boolean noCache = noCacheStr != null;
        if (noCache) {
            try {
                final String decrypted = AWebApplication.get()
                        .getSecuritySettings()
                        .getCryptFactory()
                        .newCrypt()
                        .decryptUrlSafe(noCacheStr);
                final byte[] serialized = Base64.decodeBase64(decrypted);
                final GuiTasksHolder deserialized = Objects.deserialize(serialized);
                GuiTasksHolder.get(newPage).setGuiTasks(deserialized.getGuiTasks());
            } catch (final Throwable t) {
                LOG.catching(Level.WARN, new RuntimeException("Ignoring " + PAGE_PARAM_NO_CACHE + " payload ["
                        + noCacheStr + "] due to error on deserialization.", t));
            }
            pageParameters.remove(PAGE_PARAM_NO_CACHE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public <C extends IRequestablePage> boolean isBookmarkable(final Class<C> pageClass) {
        return delegate.isBookmarkable(pageClass);
    }

    public static AjaxNewWindowNotifyingBehavior newAjaxNewWindowNotifyingBehavior() {
        return new AjaxNewWindowNotifyingBehavior() {
            @Override
            protected void onNewWindow(final AjaxRequestTarget target) {
                final Page page = target.getPage();
                final PageParameters pageParameters = new PageParameters(page.getPageParameters());
                if (!pageParameters.getNamedKeys().contains(PAGE_PARAM_NO_CACHE)) {
                    final byte[] serialized = Objects.serialize(GuiTasksHolder.get(page));
                    final String base64Str = new String(Base64.encodeBase64(serialized));
                    final String encrypted = AWebApplication.get()
                            .getSecuritySettings()
                            .getCryptFactory()
                            .newCrypt()
                            .encryptUrlSafe(base64Str);
                    pageParameters.add(PAGE_PARAM_NO_CACHE, encrypted);
                }
                page.setResponsePage(page.getPageClass(), pageParameters);
            }
        };
    }
}
