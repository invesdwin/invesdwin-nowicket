package de.invesdwin.nowicket.application.filter;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.ComponentNotFoundException;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.core.request.mapper.StalePageException;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.apache.wicket.protocol.ws.api.registry.PageIdKey;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.internal.ModelCacheUsingPageFactory;
import de.invesdwin.nowicket.component.websocket.AWebSocketBehavior;

@Immutable
public final class StalePageRequestCycleListener implements IRequestCycleListener {

    /**
     * prevent websocket connections from being stolen by other tabs when url is just copied
     * 
     * https://stackoverflow.com/questions/41678672/wicket-web-sockets-cannot-work-with-multiple-browser-windows
     */
    @Override
    public void onRequestHandlerResolved(final RequestCycle cycle, final IRequestHandler handler) {
        if (handler instanceof RenderPageRequestHandler) {
            final RenderPageRequestHandler cHandler = (RenderPageRequestHandler) handler;
            final Page page = (Page) cHandler.getPage();
            if (page != null && page.getRenderCount() > 0) {
                //                final AWebApplication application = AWebApplication.get();
                //                final WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
                //                final IWebSocketConnectionRegistry registry = webSocketSettings.getConnectionRegistry();
                //                final IWebSocketConnection connection = registry.getConnection(application, AWebSession.get().getId(),
                //                        new PageIdKey(page.getPageId()));
                //                if (connection != null && connection.isOpen()) {
                //we can shortcut the lookups a bit
                if (AWebSocketBehavior.isWebsocket(page)) {
                    ModelCacheUsingPageFactory.onNewWindowRestart(page, page);
                }
            }
        }
    }

    }

    @Override
    public IRequestHandler onException(final RequestCycle cycle, final Exception ex) {
        if (ex instanceof StalePageException) {
            final StalePageException cEx = (StalePageException) ex;
            //going back through the history is sometimes impossible, just show the latest version in that case
            return ModelCacheUsingPageFactory.onStalePageException((Page) cEx.getPage());
        }
        if (ex instanceof ComponentNotFoundException) {
            //handle more gracefully when clicking on table buttons very fast by showing the latest version instead
            //org.apache.wicket.core.request.handler.ComponentNotFoundException: Component 'form_rows:rows_gridColumn:rows_gridColumn_body:rows:dataTable:body:rows:51:cells:1:cell' has been removed from page.
            return ModelCacheUsingPageFactory.onStalePageException();
        }
        return null;
    }

}