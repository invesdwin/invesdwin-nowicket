package com.bsgcoach.internal;

import javax.annotation.concurrent.ThreadSafe;

import com.bsgcoach.web.BsgCoachWebApplicationConfig;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWicketFilter;

@ThreadSafe
public class ExampleWicketFilter extends AWicketFilter {

    @Override
    protected IWebApplicationConfig newConfig() {
        return new BsgCoachWebApplicationConfig();
    }

}
