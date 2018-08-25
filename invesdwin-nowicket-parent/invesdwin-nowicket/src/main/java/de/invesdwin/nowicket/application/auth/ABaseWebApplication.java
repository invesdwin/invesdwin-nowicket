package de.invesdwin.nowicket.application.auth;

import java.io.File;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;

@NotThreadSafe
public abstract class ABaseWebApplication
        extends org.apache.wicket.authroles.authentication.AuthenticatedWebApplication {

    public static ABaseWebApplication get() {
        return (ABaseWebApplication) org.apache.wicket.authroles.authentication.AuthenticatedWebApplication.get();
    }

    @Override
    protected abstract Class<? extends AWebSession> getWebSessionClass();

    public abstract IWebApplicationConfig getDelegate();

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

    /**
     * Provide a string here to enable cookies to last over server restarts. This is required to have the remember-me
     * feature work properly.
     */
    public abstract String getSessionEncryptionKey();

    /**
     * These packages are scanned for model classes to generate markup for.
     */
    public abstract Set<String> getClasspathBasePackages();

    /**
     * Temporary session files (like uploads) will be stored in this folder.
     */
    public abstract File getSessionsDirectory();

    /**
     * Return null to keep the default session cookie name which normally is "JSESSIONID". Otherwise return a unique
     * name for this application instance so that when multiple applications are run from the same domain the cookies
     * don't conflict with each other.
     */
    public abstract String getSessionCookieName();

}
