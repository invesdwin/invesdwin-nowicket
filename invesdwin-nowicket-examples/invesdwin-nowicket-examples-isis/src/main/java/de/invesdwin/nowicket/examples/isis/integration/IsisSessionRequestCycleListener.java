package de.invesdwin.nowicket.examples.isis.integration;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

@NotThreadSafe
public class IsisSessionRequestCycleListener implements IRequestCycleListener {

    @Override
    public void onBeginRequest(final RequestCycle cycle) {
        IsisInjector.openSession();
    }

    @Override
    public void onEndRequest(final RequestCycle cycle) {
        IsisInjector.closeSession();
    }

}
