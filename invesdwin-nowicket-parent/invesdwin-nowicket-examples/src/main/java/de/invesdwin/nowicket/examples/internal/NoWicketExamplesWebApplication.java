package de.invesdwin.nowicket.examples.internal;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;

public class NoWicketExamplesWebApplication extends AWebApplication {

	@Override
	protected IWebApplicationConfig newConfig() {
		return new NoWicketExamplesWebApplicationConfig();
	}

	@Override
	public Set<String> getClasspathBasePackages() {
		return new HashSet<String>(Arrays.asList("de.invesdwin.nowicket.examples"));
	}
}
