package de.invesdwin.nowicket.examples.isis.integration;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

@NotThreadSafe
public class IsisSessionRequestCycleListener extends AbstractRequestCycleListener {

    @Override
    public void onBeginRequest(final RequestCycle cycle) {
        IsisInjector.openSession();
    }

    @Override
    public void onEndRequest(final RequestCycle cycle) {
        IsisInjector.closeSession();
    }

}
