package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.binding.processor.element.TableDateColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.DatePropertyModel;

@NotThreadSafe
public class ModelDateColumn extends ModelTextColumn {

    private final TableDateColumnHtmlElement element;

    public ModelDateColumn(final TableDateColumnHtmlElement element) {
        super(element, element.getTitleModelFromTarget(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        final DatePropertyModel propertyModel = new DatePropertyModel(
                new BeanPathModel<Object>(rowModel, getPropertyExpression()));
        return new IModel<Object>() {

            @Override
            public void detach() {
                propertyModel.detach();
            }

            @Override
            public Object getObject() {
                final Date date = propertyModel.getObject();
                if (date != null) {
                    return element.getFormat(AWebSession.get().getLocale()).format(date);
                } else {
                    return null;
                }
            }

        };
    }

}
