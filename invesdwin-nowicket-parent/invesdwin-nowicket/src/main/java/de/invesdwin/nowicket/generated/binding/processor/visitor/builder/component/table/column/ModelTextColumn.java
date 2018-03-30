package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;

@NotThreadSafe
public class ModelTextColumn extends PropertyColumn<Object, String> {

    public ModelTextColumn(final ATableColumnHtmlElement<?, ?> e) {
        super(e.getTitleModel(null), e.getColumnId(), e.getColumnId());
    }

    protected ModelTextColumn(final IModel<String> displayModel, final String sortProperty,
            final String propertyExpression) {
        super(displayModel, sortProperty, propertyExpression);
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        item.add(newLabel(componentId, rowModel).setEscapeModelStrings(false));
    }

    protected Label newLabel(final String componentId, final IModel<Object> rowModel) {
        return new Label(componentId, getDataModel(rowModel));
    }

}
