package de.invesdwin.nowicket.examples.internal;

import org.apache.wicket.markup.html.WebPage;

import de.invesdwin.nowicket.application.WebApplicationSupport;
import de.invesdwin.nowicket.examples.pages.home.HomePage;

public class NoWicketExamplesWebApplication extends WebApplicationSupport {
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
}
