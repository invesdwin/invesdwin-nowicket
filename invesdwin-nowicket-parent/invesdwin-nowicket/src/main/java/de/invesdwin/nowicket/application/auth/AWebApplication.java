package de.invesdwin.nowicket.application.auth;

import java.io.File;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.invesdwin.nowicket.application.IWebApplication;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;

@NotThreadSafe
public abstract class AWebApplication extends org.apache.wicket.authroles.authentication.AuthenticatedWebApplication {

    public static AWebApplication get() {
        return (AWebApplication) org.apache.wicket.authroles.authentication.AuthenticatedWebApplication.get();
    }

    @Override
    protected abstract Class<? extends AWebSession> getWebSessionClass();

    public abstract IWebApplication getDelegate();

    public abstract Iterable<IWebApplicationInitializerHook> getWebApplicationInitializerHooks();

    @Override
    protected final Class<? extends WebPage> getSignInPageClass() {
        return getSignInPage();
    }

    public abstract Class<? extends WebPage> getSignInPage();

    public abstract Class<? extends WebPage> getSignOutPage();

    public abstract Class<? extends WebPage> getPageExpiredPage();

    public abstract Class<? extends WebPage> getInternalErrorPage();

    public abstract Class<? extends WebPage> getAccessDeniedPage();

    public abstract Class<? extends WebPage> getPageNotFoundPage();

    @Override
    public AWebSession newSession(final Request request, final Response response) {
        return (AWebSession) super.newSession(request, response);
    }

    public abstract String getSessionEncryptionKey();

    public abstract Set<String> getClasspathBasePackages();

    public abstract File getSessionsDirectory();

}
