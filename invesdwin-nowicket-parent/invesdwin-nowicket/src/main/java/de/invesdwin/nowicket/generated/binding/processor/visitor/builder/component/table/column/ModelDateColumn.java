package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.binding.processor.element.TableDateColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.FDatePropertyModel;
import de.invesdwin.util.time.date.FDate;

@NotThreadSafe
public class ModelDateColumn extends ModelTextColumn {

    private final TableDateColumnHtmlElement element;

    public ModelDateColumn(final TableDateColumnHtmlElement element) {
        super(element, element.getTitleModelFromTarget(null), element.getColumnId(), element.getColumnId());
        this.element = element;
    }

    @Override
    public IModel<Object> getDataModel(final IModel<Object> rowModel) {
        final FDatePropertyModel propertyModel = new FDatePropertyModel(
                new BeanPathModel<Object>(rowModel, getPropertyExpression()));
        return new IModel<Object>() {

            @Override
            public void detach() {
                propertyModel.detach();
            }

            @Override
            public Object getObject() {
                final FDate date = propertyModel.getObject();
                if (date != null) {
                    return element.getFormat(AWebSession.get().getLocale()).print(date);
                } else {
                    return null;
                }
            }

        };
    }

}
