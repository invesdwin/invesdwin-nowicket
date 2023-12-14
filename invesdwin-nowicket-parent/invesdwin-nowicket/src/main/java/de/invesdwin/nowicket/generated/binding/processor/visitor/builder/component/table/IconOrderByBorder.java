package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.html.border.Border;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.util.error.UnknownArgumentException;

@NotThreadSafe
public class IconOrderByBorder<S> extends Border {

    private final Icon icon;
    private final S property;
    private final ISortStateLocator<S> stateLocator;

    public IconOrderByBorder(final String id, final S property, final ISortStateLocator<S> stateLocator,
            final DataTable<?, ?> dataTable) {
        super(id);
        this.property = property;
        this.stateLocator = stateLocator;
        final OrderByBorder<S> orderByBorder = newOrderByBorder("orderByBorder", property, stateLocator, dataTable);
        addToBorder(orderByBorder);
        this.icon = new Icon("icon", (IconType) null);
        orderByBorder.add(icon);
    }

    protected OrderByBorder<S> newOrderByBorder(final String id, final S property,
            final ISortStateLocator<S> stateLocator, final DataTable<?, ?> dataTable) {
        return new APreactAjaxFallbackOrderByBorder<S>(id, property, stateLocator) {
            @Override
            protected void onAjaxClick(final PreactPartialPageRequestHandler target) {
                target.add(dataTable);
            }
        };
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        final SortOrder sortOrder = stateLocator.getSortState().getPropertySortOrder(property);
        final IconType iconType;
        switch (sortOrder) {
        case NONE:
            iconType = FontAwesome6IconType.sort_s;
            break;
        case ASCENDING:
            iconType = FontAwesome6IconType.sort_up_s;
            break;
        case DESCENDING:
            iconType = FontAwesome6IconType.sort_down_s;
            break;
        default:
            throw UnknownArgumentException.newInstance(SortOrder.class, sortOrder);
        }
        icon.setType(iconType);
    }
}
