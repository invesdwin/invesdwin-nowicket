package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableTextColumnHtmlElement;

@NotThreadSafe
public class ModelTextColumn extends PropertyColumn<Object, String> {

    public ModelTextColumn(final TableTextColumnHtmlElement e) {
        super(e.getTitleModel(null), e.getColumnId(), e.getColumnId());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        item.add(mewLabel(componentId, rowModel).setEscapeModelStrings(false));
    }

    protected Label mewLabel(final String componentId, final IModel<Object> rowModel) {
        return new Label(componentId, createLabelModel(rowModel));
    }

}
