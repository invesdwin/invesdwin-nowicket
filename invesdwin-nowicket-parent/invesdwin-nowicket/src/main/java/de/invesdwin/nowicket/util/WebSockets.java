package de.invesdwin.nowicket.util;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.registry.IKey;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.apache.wicket.protocol.ws.api.registry.PageIdKey;
import org.apache.wicket.protocol.ws.api.registry.ResourceNameKey;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.AWebApplication;

@Immutable
public final class WebSockets {

    private WebSockets() {}

    public static IWebSocketConnection getConnection(final Component component) {
        final Page page = component.getPage();
        final Integer pageId = Integer.parseInt(page.getId());
        final PageIdKey pageIdKey = new PageIdKey(pageId);
        return getConnection(pageIdKey);
    }

    public static IWebSocketConnection getConnection(final String resourceName) {
        return getConnection(new ResourceNameKey(resourceName));
    }

    private static IWebSocketConnection getConnection(final IKey key) {
        final AWebApplication application = AWebApplication.get();
        final WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
        final IWebSocketConnectionRegistry webSocketConnectionRegistry = webSocketSettings.getConnectionRegistry();
        final String sessionId = AWebSession.get().getId();
        final IWebSocketConnection connection = webSocketConnectionRegistry.getConnection(application, sessionId, key);
        return connection;
    }

}
