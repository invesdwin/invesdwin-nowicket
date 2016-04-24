package de.invesdwin.nowicket.examples.isis;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.html.WebPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.examples.isis.page.HomePage;
import de.invesdwin.nowicket.security.spring.ShiroAuthenticationService;

@Immutable
public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public IAuthenticationService getAuthenticationService() {
        return new ShiroAuthenticationService();
    }

}
