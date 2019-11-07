package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.binding.processor.element.TableNumberColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;

@NotThreadSafe
public class ModelNumberColumn extends ModelTextColumn {

    private final TableNumberColumnHtmlElement element;

    public ModelNumberColumn(final TableNumberColumnHtmlElement element) {
        super(element.getTitleModel(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        final IModel<Number> propertyModel = new BeanPathModel<Number>(rowModel, getPropertyExpression());
        return new IModel<Object>() {

            @Override
            public void detach() {
                propertyModel.detach();
            }

            @Override
            public Object getObject() {
                final Number number = propertyModel.getObject();
                if (number != null) {
                    return element.getFormat(AWebSession.get().getLocale()).format(number);
                } else {
                    return null;
                }
            }

        };
    }

}
