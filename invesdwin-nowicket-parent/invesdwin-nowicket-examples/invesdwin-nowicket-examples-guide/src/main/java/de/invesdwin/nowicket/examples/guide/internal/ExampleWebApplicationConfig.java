package de.invesdwin.nowicket.examples.guide.internal;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.html.WebPage;
import org.springframework.security.authentication.AuthenticationManager;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.examples.guide.Main;
import de.invesdwin.nowicket.examples.guide.pages.error.AccessDeniedPage;
import de.invesdwin.nowicket.examples.guide.pages.error.InternalErrorPage;
import de.invesdwin.nowicket.examples.guide.pages.error.PageExpiredPage;
import de.invesdwin.nowicket.examples.guide.pages.error.PageNotFoundPage;
import de.invesdwin.nowicket.examples.guide.pages.home.HomePage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.ExampleSignInPage;
import de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.ExampleSignOutPage;

@Immutable
public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return (AuthenticationManager) Main.getApplicationContext().getBean("exampleAuthenticationManager");
    }

    @Override
    public Class<? extends WebPage> getSignInPage() {
        return ExampleSignInPage.class;
    }

    @Override
    public Class<? extends WebPage> getSignOutPage() {
        return ExampleSignOutPage.class;
    }

    @Override
    public Class<? extends WebPage> getAccessDeniedPage() {
        return AccessDeniedPage.class;
    }

    @Override
    public Class<? extends WebPage> getInternalErrorPage() {
        return InternalErrorPage.class;
    }

    @Override
    public Class<? extends WebPage> getPageExpiredPage() {
        return PageExpiredPage.class;
    }

    @Override
    public Class<? extends WebPage> getPageNotFoundPage() {
        return PageNotFoundPage.class;
    }

}
