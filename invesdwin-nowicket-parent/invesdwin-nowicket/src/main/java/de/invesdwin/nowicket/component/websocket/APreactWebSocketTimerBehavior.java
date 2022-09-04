package de.invesdwin.nowicket.component.websocket;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.util.lang.Args;
import org.danekja.java.util.function.serializable.SerializableConsumer;

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.time.duration.Duration;

@NotThreadSafe
public abstract class APreactWebSocketTimerBehavior extends AWebSocketTimerBehavior {

    public APreactWebSocketTimerBehavior(final Duration updateInterval) {
        super(updateInterval.javaTimeValue());
    }

    @Override
    protected final void onTimer(final WebSocketRequestHandler target) {
        final PreactPartialPageRequestHandler handler = PreactPartialPageRequestHandler
                .of(RequestCycles.getRequestCycle(getComponent()), target);
        onTimer(handler);
        handler.render();
    }

    protected abstract void onTimer(IPartialPageRequestHandler target);

    /**
     * Creates an {@link AWebSocketTimerBehavior} based on lambda expressions
     *
     * @param interval
     *            the interval the timer
     * @param onTimer
     *            the consumer which accepts the {@link WebSocketRequestHandler}
     * @return the {@link AWebSocketTimerBehavior}
     */
    public static APreactWebSocketTimerBehavior onTimer(final Duration interval,
            final SerializableConsumer<IPartialPageRequestHandler> onTimer) {
        Args.notNull(onTimer, "onTimer");

        return new APreactWebSocketTimerBehavior(interval) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onTimer(final IPartialPageRequestHandler handler) {
                onTimer.accept(handler);
            }

        };
    }

}
