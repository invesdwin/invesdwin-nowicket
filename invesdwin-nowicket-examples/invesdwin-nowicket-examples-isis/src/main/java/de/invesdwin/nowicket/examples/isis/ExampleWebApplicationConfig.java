package de.invesdwin.nowicket.examples.isis;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.html.WebPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.examples.isis.page.auth.ExampleSignInPage;
import de.invesdwin.nowicket.examples.isis.page.users.UsersPage;
import de.invesdwin.nowicket.security.spring.ShiroAuthenticationService;

@Immutable
public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return UsersPage.class;
    }

    @Override
    public IAuthenticationService getAuthenticationService() {
        return new ShiroAuthenticationService();
    }

    @Override
    public Class<? extends WebPage> getSignInPage() {
        return ExampleSignInPage.class;
    }

}
