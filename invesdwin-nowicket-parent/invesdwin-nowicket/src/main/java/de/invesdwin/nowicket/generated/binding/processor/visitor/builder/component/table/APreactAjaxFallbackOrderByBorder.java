package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.util.RequestCycles;

@NotThreadSafe
public abstract class APreactAjaxFallbackOrderByBorder<S> extends AjaxFallbackOrderByBorder<S> {

    public APreactAjaxFallbackOrderByBorder(final String id, final S sortProperty,
            final ISortStateLocator<S> stateLocator) {
        super(id, sortProperty, stateLocator);
    }

    @Override
    protected final void onAjaxClick(final AjaxRequestTarget target) {
        final PreactPartialPageRequestHandler handler = PreactPartialPageRequestHandler
                .of(RequestCycles.getRequestCycle(target.getPage()), target);
        onAjaxClick(handler);
        handler.render();
    }

    protected abstract void onAjaxClick(PreactPartialPageRequestHandler target);

}
