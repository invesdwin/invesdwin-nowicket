package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.orderby;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxOrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;

import de.invesdwin.util.error.UnknownArgumentException;

@NotThreadSafe
public abstract class ACyclingAjaxFallbackOrderByBorder<S> extends AjaxFallbackOrderByBorder<S> {

    public ACyclingAjaxFallbackOrderByBorder(final String id, final S sortProperty,
            final ISortStateLocator<S> stateLocator) {
        super(id, sortProperty, stateLocator);
    }

    @Override
    protected OrderByLink<S> newOrderByLink(final String id, final S property,
            final ISortStateLocator<S> stateLocator) {
        return new AjaxOrderByLink<S>("orderByLink", property, stateLocator) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
                ACyclingAjaxFallbackOrderByBorder.this.updateAjaxAttributes(attributes);
            }

            @Override
            protected void onSortChanged() {
                ACyclingAjaxFallbackOrderByBorder.this.onSortChanged();
            }

            @Override
            public void onClick(final AjaxRequestTarget target) {
                ACyclingAjaxFallbackOrderByBorder.this.onAjaxClick(target);
            }

            @Override
            protected SortOrder nextSortOrder(final SortOrder order) {
                // init / flip order
                if (order == null) {
                    return SortOrder.ASCENDING;
                }

                switch (order) {
                case NONE:
                    return SortOrder.ASCENDING;
                case ASCENDING:
                    return SortOrder.DESCENDING;
                case DESCENDING:
                    return SortOrder.NONE;
                default:
                    throw UnknownArgumentException.newInstance(SortOrder.class, order);
                }
            }
        };
    }

}
