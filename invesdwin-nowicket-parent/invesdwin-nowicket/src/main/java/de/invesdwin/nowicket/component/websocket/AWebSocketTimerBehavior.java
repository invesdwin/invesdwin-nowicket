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
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.TextMessage;
import org.apache.wicket.util.lang.Args;
import org.danekja.java.util.function.serializable.SerializableConsumer;

import de.invesdwin.util.lang.string.description.TextDescription;

/**
 * A behavior that generates an WebSocket update callback at a regular interval.
 * 
 */
@NotThreadSafe
public abstract class AWebSocketTimerBehavior extends AWebSocketBehavior {
    private static final long serialVersionUID = 1L;

    /** The update interval */
    private java.time.Duration updateInterval;

    private boolean stopped = false;

    /**
     * Id of timer in JavaScript.
     */
    private String timerId;

    /** the component that this handler is bound to. */
    private Component component;

    public AWebSocketTimerBehavior(final java.time.Duration updateInterval) {
        setUpdateInterval(updateInterval);
    }

    protected final void setUpdateInterval(final java.time.Duration updateInterval) {
        if (updateInterval == null || updateInterval.toMillis() <= 0) {
            throw new IllegalArgumentException("Invalid update interval");
        }
        this.updateInterval = updateInterval;
    }

    /**
     * Bind this handler to the given component.
     * 
     * @param hostComponent
     *            the component to bind to
     */
    @Override
    public final void bind(final Component hostComponent) {
        super.bind(hostComponent);
        Args.notNull(hostComponent, "hostComponent");

        if (component != null) {
            throw new IllegalStateException("this kind of handler cannot be attached to "
                    + "multiple components; it is already attached to component " + component + ", but component "
                    + hostComponent + " wants to be attached too");
        }

        component = hostComponent;

        // call the callback
        onBind();
    }

    protected void onBind() {
    }

    /**
     * Returns the update interval
     * 
     * @return The update interval
     */
    public final java.time.Duration getUpdateInterval() {
        return updateInterval;
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        super.renderHead(component, response);

        if (!isStopped()) {
            setTimeout(response);
        }
    }

    @Override
    protected void onMessage(final WebSocketRequestHandler handler, final TextMessage message) {
        if (message.getText().equals(getTimerId())) {
            // timerId is no longer valid after timer has triggered
            timerId = null;

            if (shouldTrigger()) {
                onTimer(handler);

                if (shouldTrigger()) {
                    setTimeout(handler.getHeaderResponse());
                }
            }
        }
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
     * Decides whether the timer behavior should render its JavaScript to re-trigger it after the update interval.
     *
     * @return {@code true} if the behavior is not stopped, it is enabled and still attached to any component in the
     *         page or to the page itself
     */
    protected boolean shouldTrigger() {
        return !isStopped() && isEnabled(getComponent())
                && (getComponent() instanceof Page || getComponent().findParent(Page.class) != null);
    }

    /**
     * Listener method for the websocket timer event.
     * 
     * @param handler
     *            The request handler
     */
    protected abstract void onTimer(WebSocketRequestHandler handler);

    /**
     * @return {@code true} if has been stopped via {@link #stop(IPartialPageRequestHandler)}
     */
    public final boolean isStopped() {
        return stopped;
    }

    /**
     * Restart the timer.
     * 
     * @param target
     *            may be null
     */
    public final void restart(final IPartialPageRequestHandler target) {
        stopped = false;

        if (target != null) {
            setTimeout(target.getHeaderResponse());
        }
    }

    /**
     * Create an identifier for the JavaScript timer.
     * <p>
     * Note: The identifier must not change as long as this behavior is attached to a component!
     * 
     * @return creates an id based on {@link Component#getMarkupId()} and {@link Component#getBehaviorById(int)} by
     *         default
     */
    protected String getTimerId() {
        final Component component = getComponent();

        return component.getMarkupId() + "." + component.getBehaviorId(this);
    }

    /**
     * Set the timeout on the given {@link IHeaderResponse}. Implementation note:
     * <p>
     * {@link #respond(WebSocketRequestHandler)} might set the timer once and
     * {@link #renderHead(Component, IHeaderResponse)} a second time successively, if the attached component is
     * re-rendered on the same {@link WebSocketRequestHandler}.
     * <p>
     * But rendering of the component might <em>not</em> actually happen on the same {@link WebSocketRequestHandler},
     * e.g. when a redirect to a full page-render is scheduled. Thus this method <em>always</em> sets the timeout and in
     * the former case {@link WebSocketRequestHandler} will take care of executing one of the two
     * {@link OnLoadHeaderItem}s only.
     * 
     * @param headerResponse
     */
    private void setTimeout(final IHeaderResponse headerResponse) {
        final CharSequence js = getCallbackScript();

        // remember id to be able to clear it later
        timerId = getTimerId();

        headerResponse.render(OnLoadHeaderItem.forScript(TextDescription
                .format("Wicket.Timer.set('%s', function(){%s}, %s);", timerId, js, updateInterval.toMillis())));
    }

    protected CharSequence getCallbackScript() {
        return "Wicket.WebSocket.send('" + getTimerId() + "');";
    }

    private void clearTimeout(final IHeaderResponse headerResponse) {
        if (timerId != null) {
            headerResponse.render(OnLoadHeaderItem.forScript("Wicket.Timer.clear('" + timerId + "');"));

            timerId = null;
        }
    }

    /**
     * Stops the timer.
     * 
     * @param target
     *            may be null
     */
    public final void stop(final IPartialPageRequestHandler target) {
        if (!stopped) {
            stopped = true;

            if (target != null) {
                clearTimeout(target.getHeaderResponse());
            }
        }
    }

    @Override
    public void onRemove(final Component component) {
        component.getRequestCycle()
                .find(IPartialPageRequestHandler.class)
                .ifPresent(target -> clearTimeout(target.getHeaderResponse()));
    }

    @Override
    public void unbind(final Component component) {
        onUnbind();
        super.unbind(component);
    }

    protected void onUnbind() {
        final Component component = getComponent();

        component.getRequestCycle()
                .find(IPartialPageRequestHandler.class)
                .ifPresent(target -> clearTimeout(target.getHeaderResponse()));
    }

    /**
     * Creates an {@link AWebSocketTimerBehavior} based on lambda expressions
     *
     * @param interval
     *            the interval the timer
     * @param onTimer
     *            the consumer which accepts the {@link WebSocketRequestHandler}
     * @return the {@link AWebSocketTimerBehavior}
     */
    public static AWebSocketTimerBehavior onTimer(final java.time.Duration interval,
            final SerializableConsumer<WebSocketRequestHandler> onTimer) {
        Args.notNull(onTimer, "onTimer");

        return new AWebSocketTimerBehavior(interval) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onTimer(final WebSocketRequestHandler handler) {
                onTimer.accept(handler);
            }

        };
    }
}
