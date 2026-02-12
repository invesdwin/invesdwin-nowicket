package com.bsgcoach.internal;

import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

import com.bsgcoach.web.BsgCoachWebApplicationConfig;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.util.collections.factory.ILockCollectionFactory;

@ThreadSafe
public class ExampleWebApplication extends AWebApplication {

    public static final Set<String> BASE_PACKAGE = ILockCollectionFactory.getInstance(false)
            .newImmutableSet("com.bsgcoach");

    @Override
    protected IWebApplicationConfig newConfig() {
        return new BsgCoachWebApplicationConfig();
    }

    @Override
    public Set<String> getClasspathBasePackages() {
        return BASE_PACKAGE;
    }

}
