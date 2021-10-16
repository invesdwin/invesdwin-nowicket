package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.ModelComponentBehavior;

@NotThreadSafe
public class ModelTextColumn extends PropertyColumn<Object, String> {

    private final ATableColumnHtmlElement<?, ?> element;

    public ModelTextColumn(final ATableColumnHtmlElement<?, ?> element) {
        this(element, element.getTitleModelFromTarget(null), element.getColumnId(), element.getColumnId());
    }

    protected ModelTextColumn(final ATableColumnHtmlElement<?, ?> element, final IModel<String> displayModel,
            final String sortProperty, final String propertyExpression) {
        super(displayModel, sortProperty, propertyExpression);
        this.element = element;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        final Label label = newLabel(componentId, rowModel);
        label.setEscapeModelStrings(false);
        label.add(new ModelComponentBehavior(element, label, rowModel));
        item.add(label);
    }

    protected Label newLabel(final String componentId, final IModel<Object> rowModel) {
        return new Label(componentId, getDataModel(rowModel));
    }

}
