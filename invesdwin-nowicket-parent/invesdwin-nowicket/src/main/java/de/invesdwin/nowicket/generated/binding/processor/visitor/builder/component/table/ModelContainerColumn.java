package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableContainerColumnHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelContainerColumn extends PropertyColumn<Object, String> {

    public ModelContainerColumn(final TableContainerColumnHtmlElement element) {
        super(element.getTitleModel(null), /* not sortable */null, element.getColumnId());
    }

    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        return new AbstractReadOnlyModel<Object>() {
            @Override
            public Object getObject() {
                return Strings.asString(rowModel.getObject());
            }
        };
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        item.add(mewLabel(componentId, rowModel).setEscapeModelStrings(false));
    }

    protected Label mewLabel(final String componentId, final IModel<Object> rowModel) {
        return new Label(componentId, getDataModel(rowModel));
    }

}
