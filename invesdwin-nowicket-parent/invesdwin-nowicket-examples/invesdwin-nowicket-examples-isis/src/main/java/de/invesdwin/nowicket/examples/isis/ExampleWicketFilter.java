package de.invesdwin.nowicket.examples.isis;

import javax.annotation.concurrent.ThreadSafe;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWicketFilter;

@ThreadSafe
public class ExampleWicketFilter extends AWicketFilter {

    @Override
    protected IWebApplicationConfig newConfig() {
        return new ExampleWebApplicationConfig();
    }

}
