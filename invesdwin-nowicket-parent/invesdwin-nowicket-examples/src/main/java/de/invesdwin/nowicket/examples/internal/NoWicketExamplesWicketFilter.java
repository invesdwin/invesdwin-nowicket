package de.invesdwin.nowicket.examples.internal;

import de.invesdwin.nowicket.application.IWebApplication;
import de.invesdwin.nowicket.application.filter.ADelegateWicketFilter;

public class NoWicketExamplesWicketFilter extends ADelegateWicketFilter {

	@Override
	protected IWebApplication resolveDelegate() {
		return new NoWicketExamplesWebApplication();
	}

}
