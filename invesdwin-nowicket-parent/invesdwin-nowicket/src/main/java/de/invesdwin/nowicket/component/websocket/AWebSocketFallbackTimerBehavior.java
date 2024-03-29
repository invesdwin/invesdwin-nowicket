/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.invesdwin.nowicket.component.websocket;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.TextMessage;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.StringValue;
import org.danekja.java.util.function.serializable.SerializableConsumer;

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.date.FTimeUnit;
import de.invesdwin.util.time.duration.Duration;

/**
 * A behavior that generates an WebSocket update callback at a regular interval that falls back to an ajax update
 * mechanism if the websocket does not work.
 * 
 */
@NotThreadSafe
public abstract class AWebSocketFallbackTimerBehavior extends Behavior {

    private static final String ATTR_CLIENT_RESPONSE = "CLIENT_RESPONSE";
    private static final String ATTR_CLIENT_RESPONSE_PREFIX = ATTR_CLIENT_RESPONSE + ": ";
    private static final Duration MIN_WEBSOCKET_TIMEOUT = new Duration(10, FTimeUnit.SECONDS);

    public class FallbackAjaxTimerBehavior extends APreactAjaxTimerBehavior {

        private FDate firstAjaxEvent = null;

        public FallbackAjaxTimerBehavior(final Duration updateInterval) {
            super(updateInterval);
        }

        @Override
        protected void onTimer(final PreactPartialPageRequestHandler target) {
            if (websocket != null && !websocket.isStopped() && ajax != null && !ajax.isStopped()) {
                if (firstAjaxEvent == null) {
                    firstAjaxEvent = new FDate();
                } else if (new Duration(firstAjaxEvent).isGreaterThan(websocketTimeout)) {
                    websocket.stop(target);
                }
            }
            final StringValue clientResponseValue = getComponent().getRequest()
                    .getRequestParameters()
                    .getParameterValue(ATTR_CLIENT_RESPONSE);
            if (clientResponseValue != null) {
                final String clientResponseStr = clientResponseValue.toString();
                if (Strings.isNotBlank(clientResponseStr)) {
                    processClientResponse(clientResponseStr);
                }
            }
            AWebSocketFallbackTimerBehavior.this.onTimer(target);
        }

        @Override
        protected String findIndicatorId() {
            //disable the ajax indicator for these updates
            return null;
        }

        @Override
        protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);
            final String clientResponseJs = createClientResponseScript();
            if (Strings.isNotBlank(clientResponseJs)) {
                attributes.getDynamicExtraParameters()
                        .add("return {'" + ATTR_CLIENT_RESPONSE + "': " + clientResponseJs + "}");
            }
        }

    }

    public class FallbackWebSocketTimerBehavior extends APreactWebSocketTimerBehavior {

        public FallbackWebSocketTimerBehavior(final Duration updateInterval) {
            super(updateInterval);
        }

        @Override
        protected void onMessage(final WebSocketRequestHandler handler, final TextMessage message) {
            if (message.getText().startsWith(ATTR_CLIENT_RESPONSE_PREFIX)) {
                final String clientResponse = Strings.removeStart(message.getText(), ATTR_CLIENT_RESPONSE_PREFIX);
                processClientResponse(clientResponse);
            } else {
                super.onMessage(handler, message);
            }
        }

        @Override
        protected void onTimer(final PreactPartialPageRequestHandler handler) {
            if (ajax != null && !ajax.isStopped()) {
                ajax.stop(handler);
            }
            AWebSocketFallbackTimerBehavior.this.onTimer(handler);
            final String clientResponseJs = createClientResponseScript();
            if (Strings.isNotBlank(clientResponseJs)) {
                handler.appendJavaScript(
                        "Wicket.WebSocket.send('" + ATTR_CLIENT_RESPONSE_PREFIX + "'+" + clientResponseJs + ");");
            }
        }

    }

    private final Duration websocketTimeout;
    private final FallbackWebSocketTimerBehavior websocket;
    private final FallbackAjaxTimerBehavior ajax;

    /** the component that this handler is bound to. */
    private Component component;

    public AWebSocketFallbackTimerBehavior(final Duration updateInterval) {
        this.websocket = newWebSocketTimerBehavior(updateInterval);
        this.ajax = newAjaxTimerBehavior(updateInterval);
        this.websocketTimeout = updateInterval.multiply(10).orHigher(MIN_WEBSOCKET_TIMEOUT);
    }

    public FallbackWebSocketTimerBehavior getWebsocket() {
        return websocket;
    }

    public FallbackAjaxTimerBehavior getAjax() {
        return ajax;
    }

    protected FallbackAjaxTimerBehavior newAjaxTimerBehavior(final Duration updateInterval) {
        return new FallbackAjaxTimerBehavior(updateInterval);
    }

    protected FallbackWebSocketTimerBehavior newWebSocketTimerBehavior(final Duration updateInterval) {
        return new FallbackWebSocketTimerBehavior(updateInterval);
    }

    /**
     * Gets the component that this handler is bound to.
     * 
     * @return the component that this handler is bound to
     */
    protected final Component getComponent() {
        return component;
    }

    /**
     * Bind this handler to the given component.
     * 
     * @param hostComponent
     *            the component to bind to
     */
    @Override
    public final void bind(final Component hostComponent) {

        Args.notNull(hostComponent, "hostComponent");

        if (component != null) {
            throw new IllegalStateException("this kind of handler cannot be attached to "
                    + "multiple components; it is already attached to component " + component + ", but component "
                    + hostComponent + " wants to be attached too");
        }

        component = hostComponent;

        //let websocket handle the first request
        if (websocket != null) {
            hostComponent.add(websocket);
        }
        if (ajax != null) {
            hostComponent.add(ajax);
        }

        // call the callback
        onBind();
    }

    private void onBind() {}

    /**
     * Returns the update interval
     * 
     * @return The update interval
     */
    public final java.time.Duration getUpdateInterval() {
        if (websocket != null) {
            return websocket.getUpdateInterval();
        }
        if (ajax != null) {
            return ajax.getUpdateInterval();
        }
        return null;
    }

    /**
     * Listener method for the websocket timer event.
     * 
     * @param handler
     *            The request handler
     */
    protected abstract void onTimer(PreactPartialPageRequestHandler handler);

    protected String createClientResponseScript() {
        return null;
    }

    protected void processClientResponse(final String clientResponse) {

    }

    /**
     * @return {@code true} if has been stopped via {@link #stop(IPartialPageRequestHandler)}
     */
    public final boolean isStopped() {
        boolean running = false;
        if (websocket != null) {
            if (!websocket.isStopped()) {
                running = true;
            }
        }
        if (ajax != null) {
            if (!ajax.isStopped()) {
                running = true;
            }
        }
        return !running;
    }

    /**
     * Restart the timer.
     * 
     * @param target
     *            may be null
     */
    public final void restart(final IPartialPageRequestHandler target) {
        if (websocket != null) {
            websocket.restart(target);
        }
        if (ajax != null) {
            ajax.restart(target);
        }
    }

    /**
     * Stops the timer.
     * 
     * @param target
     *            may be null
     */
    public final void stop(final IPartialPageRequestHandler target) {
        if (websocket != null) {
            websocket.stop(target);
        }
        if (ajax != null) {
            ajax.stop(target);
        }
    }

    @Override
    public void onRemove(final Component component) {}

    @Override
    public void unbind(final Component component) {
        onUnbind();
        if (websocket != null) {
            websocket.unbind(component);
        }
        if (ajax != null) {
            ajax.unbind(component);
        }
        super.unbind(component);
    }

    protected void onUnbind() {}

    /**
     * Creates an {@link AWebSocketFallbackTimerBehavior} based on lambda expressions
     *
     * @param interval
     *            the interval the timer
     * @param onTimer
     *            the consumer which accepts the {@link WebSocketRequestHandler}
     * @return the {@link AWebSocketFallbackTimerBehavior}
     */
    public static AWebSocketFallbackTimerBehavior onTimer(final Duration interval,
            final SerializableConsumer<PreactPartialPageRequestHandler> onTimer) {
        Args.notNull(onTimer, "onTimer");

        return new AWebSocketFallbackTimerBehavior(interval) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onTimer(final PreactPartialPageRequestHandler handler) {
                onTimer.accept(handler);
            }

        };
    }
}
