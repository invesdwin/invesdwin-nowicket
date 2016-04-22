package de.invesdwin.nowicket.examples.isis;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.html.WebPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.examples.isis.page.HomePage;

@Immutable
public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

}
