package de.invesdwin.nowicket.examples.guide.internal;

import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.examples.guide.page.ExampleWebApplicationConfig;
import de.invesdwin.util.collections.factory.ILockCollectionFactory;

@ThreadSafe
public class ExampleWebApplication extends AWebApplication {

    public static final Set<String> BASE_PACKAGE = ILockCollectionFactory.getInstance(false)
            .newImmutableSet("de.invesdwin.nowicket.examples");

    @Override
    protected IWebApplicationConfig newConfig() {
        return new ExampleWebApplicationConfig();
    }

    @Override
    public Set<String> getClasspathBasePackages() {
        return BASE_PACKAGE;
    }

}
