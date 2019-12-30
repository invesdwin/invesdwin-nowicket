package de.invesdwin.nowicket.application.filter;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.core.request.handler.ComponentNotFoundException;
import org.apache.wicket.core.request.mapper.StalePageException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.application.filter.internal.ModelCacheUsingPageFactory;

@Immutable
public final class StalePageRequestCycleListener implements IRequestCycleListener {

    @Override
    public IRequestHandler onException(final RequestCycle cycle, final Exception ex) {
        if (ex instanceof StalePageException) {
            //going back through the history is sometimes impossible, just show the latest version in that case
            return ModelCacheUsingPageFactory.onStalePageException();
        }
        if (ex instanceof ComponentNotFoundException) {
            //handle more gracefully when clicking on table buttons very fast by showing the latest version instead
            //org.apache.wicket.core.request.handler.ComponentNotFoundException: Component 'form_rows:rows_gridColumn:rows_gridColumn_body:rows:dataTable:body:rows:51:cells:1:cell' has been removed from page.
            return ModelCacheUsingPageFactory.onStalePageException();
        }
        return null;
    }

}