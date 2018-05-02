package de.invesdwin.nowicket.application.filter;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.core.request.mapper.StalePageException;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.application.filter.internal.ModelCacheUsingPageFactory;

@Immutable
public final class StalePageRequestCycleListener extends AbstractRequestCycleListener {

    @Override
    public IRequestHandler onException(final RequestCycle cycle, final Exception ex) {
        if (ex instanceof StalePageException) {
            return ModelCacheUsingPageFactory.onStalePageException();
        }
        return null;
    }

}