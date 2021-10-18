package de.invesdwin.nowicket.component.websocket;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.event.WebSocketPayload;

import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.math.Booleans;

/**
 * This is a WebSocketBehavior that supports GuiService calls by setting up the partial page request handler before
 * executing the events.
 * 
 * @author subes
 *
 */
@NotThreadSafe
public abstract class AWebSocketBehavior extends WebSocketBehavior {

    private static final MetaDataKey<Boolean> KEY_INSTANCE = new MetaDataKey<Boolean>() {
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
        page.setMetaData(KEY_INSTANCE, Boolean.TRUE);
    }

    public static boolean isWebsocket(final Page page) {
        return Booleans.isTrue(page.getMetaData(KEY_INSTANCE));
    }

}
