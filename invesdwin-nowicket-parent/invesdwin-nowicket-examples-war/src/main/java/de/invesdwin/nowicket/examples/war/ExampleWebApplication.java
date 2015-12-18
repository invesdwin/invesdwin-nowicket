package de.invesdwin.nowicket.examples.war;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;

public class ExampleWebApplication extends AWebApplication {

	public static final Set<String> BASE_PACKAGE = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("de.invesdwin.nowicket.examples.war")));
	
	@Override
	protected IWebApplicationConfig newConfig() {
		return new ExampleWebApplicationConfig();
	}

	@Override
	public Set<String> getClasspathBasePackages() {
		return BASE_PACKAGE;
	}
	
}
