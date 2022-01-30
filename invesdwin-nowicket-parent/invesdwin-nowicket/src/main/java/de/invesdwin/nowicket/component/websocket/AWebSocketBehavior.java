package de.invesdwin.nowicket.component.websocket;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.event.WebSocketPayload;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.apache.wicket.protocol.ws.api.registry.PageIdKey;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.util.RequestCycles;

/**
 * This is a WebSocketBehavior that supports GuiService calls by setting up the partial page request handler before
 * executing the events.
 * 
 * @author subes
 *
 */
@NotThreadSafe
public abstract class AWebSocketBehavior extends WebSocketBehavior {

    private static final MetaDataKey<Integer> KEY_REQUEST_CYCLE_HASH = new MetaDataKey<Integer>() {
    };

    @Override
    public void onEvent(final Component component, final IEvent<?> event) {
        final Object payload = event.getPayload();
        if (payload instanceof WebSocketPayload) {
            final WebSocketPayload<?> wsPayload = (WebSocketPayload<?>) payload;
            final WebSocketRequestHandler webSocketHandler = wsPayload.getHandler();
            //need to set this manually
            RequestCycles.setPartialPageRequestHandler(component, webSocketHandler);
        }
        super.onEvent(component, event);
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);
        final Page page = component.getPage();
        setWebsocket(page);
    }

    private void setWebsocket(final Page page) {
        page.setMetaData(KEY_REQUEST_CYCLE_HASH, RequestCycles.getRequestCycle(page).hashCode());
    }

    public static boolean isWebsocket(final Page page) {
        return page.getMetaData(KEY_REQUEST_CYCLE_HASH) != null;
    }

    /**
     * Ignores websockets that were registered in exactly this request cycle. On other request cycles from pontentially
     * other tabs that we might steal will return true.
     */
    public static boolean isForeignWebsocket(final Page page) {
        final boolean foreignRequestCycle = page
                .getMetaData(KEY_REQUEST_CYCLE_HASH) != RequestCycles.getRequestCycle(page).hashCode();
        if (foreignRequestCycle) {
            final AWebApplication application = AWebApplication.get();
            final WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
            final IWebSocketConnectionRegistry registry = webSocketSettings.getConnectionRegistry();
            final IWebSocketConnection connection = registry.getConnection(application, AWebSession.get().getId(),
                    new PageIdKey(page.getPageId()));
            final boolean foreignWebsocket = connection != null && connection.isOpen();
            return foreignWebsocket;
        } else {
            return false;
        }
    }

}
