package com.bsgcoach.internal;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

import com.bsgcoach.web.BsgCoachWebApplicationConfig;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.util.collections.Arrays;
import de.invesdwin.util.collections.Collections;

@ThreadSafe
public class ExampleWebApplication extends AWebApplication {

    public static final Set<String> BASE_PACKAGE = Collections
            .unmodifiableSet(new HashSet<String>(Arrays.asList("com.bsgcoach")));

    @Override
    protected IWebApplicationConfig newConfig() {
        return new BsgCoachWebApplicationConfig();
    }

    @Override
    public Set<String> getClasspathBasePackages() {
        return BASE_PACKAGE;
    }

}
