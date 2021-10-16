package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableContainerColumnHtmlElement;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ModelContainerColumn extends ModelTextColumn {

    public ModelContainerColumn(final TableContainerColumnHtmlElement element) {
        super(element, element.getTitleModelFromTarget(null), /* not sortable */null, element.getColumnId());
    }

    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        return new IModel<Object>() {
            @Override
            public Object getObject() {
                return Strings.asString(rowModel.getObject());
            }
        };
    }

}
