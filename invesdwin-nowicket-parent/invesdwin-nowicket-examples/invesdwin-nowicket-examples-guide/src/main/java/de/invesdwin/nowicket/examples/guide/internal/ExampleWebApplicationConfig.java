package de.invesdwin.nowicket.examples.guide.internal;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.html.WebPage;
import org.springframework.security.authentication.AuthenticationManager;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.examples.guide.Main;
import de.invesdwin.nowicket.examples.guide.pages.auth.ExampleSignInPage;
import de.invesdwin.nowicket.examples.guide.pages.auth.ExampleSignOutPage;
import de.invesdwin.nowicket.examples.guide.pages.home.HomePage;

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

}
