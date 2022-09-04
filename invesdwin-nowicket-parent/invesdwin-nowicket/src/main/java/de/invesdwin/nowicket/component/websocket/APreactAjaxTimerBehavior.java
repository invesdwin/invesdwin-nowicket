package de.invesdwin.nowicket.component.websocket;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.util.lang.Args;
import org.danekja.java.util.function.serializable.SerializableConsumer;

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.time.duration.Duration;

@NotThreadSafe
public abstract class APreactAjaxTimerBehavior extends AbstractAjaxTimerBehavior {

    public APreactAjaxTimerBehavior(final Duration updateInterval) {
        super(updateInterval.javaTimeValue());
    }

    @Override
    protected final void onTimer(final AjaxRequestTarget target) {
        final PreactPartialPageRequestHandler handler = PreactPartialPageRequestHandler
                .of(RequestCycles.getRequestCycle(target.getPage()), target);
        onTimer(handler);
        handler.render();
    }

    protected abstract void onTimer(IPartialPageRequestHandler target);

    /**
     * Creates an {@link AbstractAjaxTimerBehavior} based on lambda expressions
     *
     * @param interval
     *            the interval the timer
     * @param onTimer
     *            the consumer which accepts the {@link AjaxRequestTarget}
     * @return the {@link AbstractAjaxTimerBehavior}
     */
    public static APreactAjaxTimerBehavior onTimer(final Duration interval,
            final SerializableConsumer<IPartialPageRequestHandler> onTimer) {
        Args.notNull(onTimer, "onTimer");

        return new APreactAjaxTimerBehavior(interval) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onTimer(final IPartialPageRequestHandler target) {
                onTimer.accept(target);
            }
        };
    }

}
