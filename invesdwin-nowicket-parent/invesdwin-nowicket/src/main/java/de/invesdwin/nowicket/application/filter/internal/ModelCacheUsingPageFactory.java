package de.invesdwin.nowicket.application.filter.internal;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.IPageFactory;
import org.apache.wicket.Page;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.ext.XLogger.Level;

import de.invesdwin.nowicket.application.IPageFactoryHook;
import de.invesdwin.nowicket.application.PageFactory;

@NotThreadSafe
public class ModelCacheUsingPageFactory implements IPageFactory {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory
            .getXLogger(ModelCacheUsingPageFactory.class);
    private final IPageFactory delegate;

    public ModelCacheUsingPageFactory(final IPageFactory delegate) {
        this.delegate = delegate;
    }

    public static <C extends IRequestablePage> C postProcessUsedPage(final Object newModelObject, final C usedPage) {
        if (usedPage instanceof Page) {
            final Page cUsedPage = (Page) usedPage;
            if (cUsedPage.getDefaultModelObject() != null && usedPage instanceof IPageFactoryHook) {
                final IPageFactoryHook hook = (IPageFactoryHook) usedPage;
                hook.onPageRefresh(newModelObject);
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

    @SuppressWarnings("unchecked")
    private <C extends IRequestablePage> C tryPageFactoryCache(final C newPage) {
        if (newPage instanceof Page) {
            final Page cNewPage = (Page) newPage;
            final Object modelObject = cNewPage.getDefaultModelObject();
            if (modelObject != null) {
                try {
                    final Page page = PageFactory.get().getRegisteredPage(modelObject);
                    if (page != null) {
                        return (C) page;
                    }
                } catch (final Throwable t) {
                    LOG.catching(Level.WARN,
                            new RuntimeException(
                                    "Not a generated model page or no suitable constructor available, falling back to default created page.",
                                    t));
                    return newPage;
                }
            }
        }
        return newPage;
    }

    @Override
    public <C extends IRequestablePage> boolean isBookmarkable(final Class<C> pageClass) {
        return delegate.isBookmarkable(pageClass);
    }
}
