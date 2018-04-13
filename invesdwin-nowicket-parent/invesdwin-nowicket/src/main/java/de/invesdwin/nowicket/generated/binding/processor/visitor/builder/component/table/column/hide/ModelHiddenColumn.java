package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column.hide;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;

/**
 * A marker column that you can return in BindingInterceptor to hide a column without having to edit the model to do so.
 */
@Immutable
public class ModelHiddenColumn implements IColumn<Object, String> {

    private final String columnId;

    public ModelHiddenColumn(final ATableColumnHtmlElement<?, ?> element) {
        this(element.getColumnId());
    }

    public ModelHiddenColumn(final String columnId) {
        this.columnId = columnId;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> cellItem, final String componentId,
            final IModel<Object> rowModel) {
        cellItem.add(new WebMarkupContainer(componentId).setVisible(false));
        cellItem.setVisible(false);
    }

    @Override
    public void detach() {
        //no-op
    }

    @Override
    public Component getHeader(final String componentId) {
        return new WebMarkupContainer(componentId).setVisible(false);
    }

    @Override
    public String getSortProperty() {
        return columnId;
    }

    @Override
    public boolean isSortable() {
        return false;
    }

}
