package de.invesdwin.nowicket.examples.internal;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWicketFilter;

public class ExampleWicketFilter extends AWicketFilter {

	@Override
	protected IWebApplicationConfig newConfig() {
		return new ExampleWebApplicationConfig();
	}

}
