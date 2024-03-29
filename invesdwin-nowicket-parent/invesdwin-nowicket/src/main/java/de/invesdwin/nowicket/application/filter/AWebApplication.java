package de.invesdwin.nowicket.application.filter;

import java.io.File;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.IPageFactory;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.init.WebApplicationInitializer;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;
import de.invesdwin.nowicket.application.filter.internal.DelegateWebSession;
import de.invesdwin.nowicket.application.filter.internal.ModelCacheUsingPageFactory;
import de.invesdwin.nowicket.page.auth.defaultpage.DefaultSignInPage;
import de.invesdwin.nowicket.page.auth.defaultpage.DefaultSignOutPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultAccessDeniedPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultInternalErrorPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageExpiredPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageNotFoundPage;
import de.invesdwin.util.collections.Collections;
import jakarta.activation.MimetypesFileTypeMap;

@ThreadSafe
public abstract class AWebApplication extends ABaseWebApplication {

    private IWebApplicationConfig delegate;

    @Override
    protected void init() {
        super.init();
        getCspSettings().blocking().disabled();
        WebApplicationInitializer initializer = getDelegate().getInitializerOverride();
        if (initializer == null) {
            initializer = new WebApplicationInitializer();
        }
        initializer.init();
    }

    public static AWebApplication get() {
        return (AWebApplication) ABaseWebApplication.get();
    }

    @Override
    public final IWebApplicationConfig getDelegate() {
        if (delegate == null) {
            delegate = newConfig();
        }
        return delegate;
    }

    protected abstract IWebApplicationConfig newConfig();

    @Override
    protected Class<? extends AWebSession> getWebSessionClass() {
        return DelegateWebSession.class;
    }

    @Override
    public Class<? extends WebPage> getSignInPage() {
        final Class<? extends WebPage> signInPage = getDelegate().getSignInPage();
        if (signInPage != null) {
            return signInPage;
        } else {
            return DefaultSignInPage.class;
        }
    }

    @Override
    public Class<? extends WebPage> getSignOutPage() {
        final Class<? extends WebPage> signOutPage = getDelegate().getSignOutPage();
        if (signOutPage != null) {
            return signOutPage;
        } else {
            return DefaultSignOutPage.class;
        }
    }

    @Override
    public Class<? extends WebPage> getPageExpiredPage() {
        final Class<? extends WebPage> pageExpiredErrorPage = getDelegate().getPageExpiredPage();
        if (pageExpiredErrorPage != null) {
            return pageExpiredErrorPage;
        } else {
            return DefaultPageExpiredPage.class;
        }
    }

    @Override
    public Class<? extends WebPage> getInternalErrorPage() {
        final Class<? extends WebPage> internalErrorPage = getDelegate().getInternalErrorPage();
        if (internalErrorPage != null) {
            return internalErrorPage;
        } else {
            return DefaultInternalErrorPage.class;
        }
    }

    @Override
    public Class<? extends WebPage> getAccessDeniedPage() {
        final Class<? extends WebPage> accessDeniedPage = getDelegate().getAccessDeniedPage();
        if (accessDeniedPage != null) {
            return accessDeniedPage;
        } else {
            return DefaultAccessDeniedPage.class;
        }
    }

    @Override
    public Class<? extends WebPage> getPageNotFoundPage() {
        final Class<? extends WebPage> pageNotFoundPage = getDelegate().getPageNotFoundPage();
        if (pageNotFoundPage != null) {
            return pageNotFoundPage;
        } else {
            return DefaultPageNotFoundPage.class;
        }
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return getDelegate().getHomePage();
    }

    @Override
    protected void outputDevelopmentModeWarning() {
        //disabled
    }

    @Override
    public String getMimeType(final String fileName) {
        if (fileName.endsWith(".js.map")) {
            return internalGetMimeType(fileName + ".json");
        } else {
            return internalGetMimeType(fileName);
        }
    }

    protected String internalGetMimeType(final String fileName) {
        String mimeType = super.getMimeType(fileName);
        if (mimeType == null) {
            //fallback to mime.types file
            mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(fileName);
        }
        return mimeType;
    }

    @Override
    public AWebSession newSession(final Request request, final Response response) {
        final AWebSession session = super.newSession(request, response);
        getDelegate().postProcessNewSession(session);
        return session;
    }

    @Override
    protected IPageFactory newPageFactory() {
        final IPageFactory delegate = super.newPageFactory();
        return new ModelCacheUsingPageFactory(delegate);
    }

    @Override
    public Iterable<IWebApplicationInitializerHook> getWebApplicationInitializerHooks() {
        return Collections.emptyList();
    }

    @Override
    public File getSessionsDirectory() {
        return new File(".sessions");
    }

    @Override
    public String getSessionCookieName() {
        return null;
    }
}
