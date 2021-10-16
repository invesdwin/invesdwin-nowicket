package de.invesdwin.nowicket.application.filter.init;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.application.PageFactory;

@ThreadSafe
public class PageFactoryUpdatingRequestCycleListener implements IRequestCycleListener {

    @Override
    public void onEndRequest(final RequestCycle cycle) {
        final IPageRequestHandler handler = PageRequestHandlerTracker.getLastHandler(cycle);
        if (handler != null) {
            PageFactory.get().updatePage((Page) handler.getPage());
        }
    }

}
