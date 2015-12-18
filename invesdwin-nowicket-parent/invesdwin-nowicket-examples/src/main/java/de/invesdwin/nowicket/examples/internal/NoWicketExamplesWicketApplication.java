package de.invesdwin.nowicket.examples.internal;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.invesdwin.nowicket.application.IWebApplication;
import de.invesdwin.nowicket.application.filter.ADelegateWebApplication;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;

public class NoWicketExamplesWicketApplication extends ADelegateWebApplication {

	@Override
	protected IWebApplication resolveDelegate() {
		return new NoWicketExamplesWebApplication();
	}

	@Override
	public Set<String> getClasspathBasePackages() {
		return new HashSet(Arrays.asList("de.invesdwin.nowicket.examples"));
	}
}
