package de.invesdwin.nowicket.application;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.filter.init.WebApplicationInitializer;

public interface IWebApplicationConfig {

    ResourceReference DEFAULT_FAVICON = new PackageResourceReference(IWebApplicationConfig.class, "/favicon.ico");

    /**
     * The home page that is shown as the default page when no path is given; also this will be the page that is opened
     * when the user clicks on the brand logo or name.
     */
    Class<? extends WebPage> getHomePage();

    /**
     * may return null to keep the default
     */
    ResourceReference getFavicon();

    /**
     * may return null to keep the default
     */
    WebApplicationInitializer getInitializerOverride();

    /**
     * Here you can change settings like the locale and other stuff for new sessions.
     */
    void postProcessNewSession(AWebSession session);

    /**
     * This is the page that is used for signing in your users; may be left null to use a default page. A menu item is
     * generated for this when AuthenticationManager is not null; also this page will be shown when the user is
     * requesting a secured page.
     */
    Class<? extends WebPage> getSignInPage();

    /**
     * This is the page that is used for signing out your users; may be left null to use a default page. A menu item is
     * generated for this when AuthenticationManager is not null.
     */
    Class<? extends WebPage> getSignOutPage();

    /**
     * You can return null here if no authentication is used for this application; thus no login needed
     */
    IAuthenticationService getAuthenticationService();

    /**
     * This is the page that is shown when another page has expired. You may return null here to keep the default.
     */
    Class<? extends WebPage> getPageExpiredPage();

    /**
     * This is the page that is shown when an internal error occured. You may return null here to keep the default.
     */
    Class<? extends WebPage> getInternalErrorPage();

    /**
     * This is the page that is shown when access is denied to a page. You may return null here to keep the default.
     */
    Class<? extends WebPage> getAccessDeniedPage();

    /**
     * This is the page that is shown when a 404 error is shown. You may return null here to keep the default.
     */
    Class<? extends WebPage> getPageNotFoundPage();

    List<String> getWicketFilterIgnorePaths();

    /**
     * Return null to keep the default session cookie name which normally is "JSESSIONID". Otherwise return a unique
     * name for this application instance so that when multiple applications are run from the same domain the cookies
     * don't conflict with each other.
     */
    String getSessionCookieName();

}
