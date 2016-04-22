package de.invesdwin.nowicket.application.filter.init;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.application.PageFactory;

@ThreadSafe
public class PageFactoryUpdatingRequestCycleListener extends AbstractRequestCycleListener {

    @Override
    public void onEndRequest(final RequestCycle cycle) {
        final IPageRequestHandler handler = PageRequestHandlerTracker.getLastHandler(cycle);
        if (handler != null && handler.getRenderCount() > 0) {
            PageFactory.get().updatePage((Page) handler.getPage());
        }
    }

}
