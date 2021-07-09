package de.invesdwin.nowicket.examples.guide.page.wicket.websocket;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.lang3.RandomUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.IWebSocketRequestHandler;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.component.websocket.AWebSocketFallbackTimerBehavior;
import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;
import de.invesdwin.nowicket.generated.binding.processor.context.ComponentRegistry;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.element.GridColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.ITabbedHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.tabbed.ModelTabbedPanel;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.nowicket.util.WebSockets;
import de.invesdwin.util.time.date.FDate;

@MountPath("websocket")
@NotThreadSafe
public class WebSocketPage extends AExampleWebPage {

    public WebSocketPage() {
        this(Model.of(new WebSocket()));
    }

    public WebSocketPage(final IModel<WebSocket> model) {
        super(model);
        new GeneratedBinding(this).withBindingInterceptor(new BindingInterceptor() {
            @Override
            protected Component create(final IHtmlElement<?, ?> e) {
                if (e.getWicketId().equals(WebSocketConstants.tabs)) {
                    return new ModelTabbedPanel((ITabbedHtmlElement<?, ?>) e);
                }
                return super.create(e);
            }
        }).bind();
        add(new AWebSocketFallbackTimerBehavior(org.apache.wicket.util.time.Duration.seconds(1)) {

            private FDate prevLastRefresh = FDate.MIN_DATE;
            private boolean roundtripComplete = false;

            @Override
            protected void onTimer(final IPartialPageRequestHandler handler) {
                final HtmlContext context = HtmlContext.get(WebSocketPage.this);
                final WebSocket model = (WebSocket) context.getMarkupContainer().getDefaultModel().getObject();
                //listen to the users preference
                if (!model.isRefreshAutomatically()) {
                    return;
                }
                //fake an update from an asynchronous process here
                if (RandomUtils.nextBoolean()) {
                    model.refresh();
                }
                //prevent processRequestFinally to update all forms
                GuiService.get().disableUpdateAllComponentsForCurrentRequest();
                try {
                    final FDate newLastRefresh = model.getLastRefresh();
                    final ComponentRegistry componentRegistry = context.getComponentRegistry();
                    //do some sort of check to prevent unnecessary updates
                    if (!newLastRefresh.equals(prevLastRefresh)) {
                        /*
                         * update the grid column instead of the tab itself, since that is the only component that gets
                         * a placeholder tab printed when the component is invisible
                         */
                        final Component tabs = componentRegistry.getComponent(
                                WebSocketConstants.tabs + GridColumnHtmlElement.GRID_COLUMN_WICKET_ID_SUFFIX);
                        handler.add(tabs);
                        final Component lastRefresh = componentRegistry.getComponent(WebSocketConstants.lastRefresh);
                        handler.add(lastRefresh);
                        prevLastRefresh = newLastRefresh;
                    }
                    final Component lastRefreshCheck = componentRegistry
                            .getComponent(WebSocketConstants.lastRefreshCheck);
                    handler.add(lastRefreshCheck);
                } finally {
                    //process outstanding gui tasks if there are any
                    GuiService.get().processRequestFinally(WebSocketPage.this);
                }
            }

            @Override
            protected String createClientResponseScript() {
                if (!roundtripComplete) {
                    final IPartialPageRequestHandler handler = RequestCycles
                            .getPartialPageRequestHandler(getComponent());
                    if (handler instanceof AjaxRequestTarget) {
                        return "'Ajax'";
                    } else if (handler instanceof IWebSocketRequestHandler) {
                        return "'WebSocket'";
                    }
                }
                return null;
            }

            @Override
            protected void processClientResponse(final String clientResponse) {
                GuiService.get().showStatusMessage("Hello " + clientResponse + "!");
                roundtripComplete = true;
            }

        });
    }

    /**
     * This is example code on how to push messages directly either via casting the handler or by obtaining the web
     * socket connection from the registry.
     */
    private void pushMessage() {
        //handler method
        final IPartialPageRequestHandler handler = RequestCycles.getPartialPageRequestHandler(this);
        if (handler instanceof IWebSocketRequestHandler) {
            final IWebSocketRequestHandler webSocketHandler = (IWebSocketRequestHandler) handler;
            webSocketHandler.push("message");
        }
        //registry method
        final IWebSocketConnection webSocketConnection = WebSockets.getConnection(this);
        if (webSocketConnection != null) {
            try {
                webSocketConnection.sendMessage("message");
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
