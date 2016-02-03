package com.eva.internal;

import javax.annotation.concurrent.ThreadSafe;

import com.eva.web.ExampleWebApplicationConfig;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWicketFilter;

@ThreadSafe
public class ExampleWicketFilter extends AWicketFilter {

    @Override
    protected IWebApplicationConfig newConfig() {
        return new ExampleWebApplicationConfig();
    }

}
