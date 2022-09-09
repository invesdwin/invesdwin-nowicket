package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallback;
import de.invesdwin.nowicket.generated.markup.processor.element.TableSubmitButtonColumnModelElement;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class TableSubmitButtonColumnHtmlElement
        extends ATableColumnHtmlElement<TableSubmitButtonColumnModelElement, Object>
        implements ITableButtonColumn<TableSubmitButtonColumnModelElement, Object> {

    public TableSubmitButtonColumnHtmlElement(final HtmlContext context,
            final TableSubmitButtonColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Deprecated
    @Override
    public IModel<Object> getModel() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean isRowContainer() {
        return true;
    }

    @Override
    public ISubmitButtonCallback getButtonCallback(final IModel<Object> targetObjectModel) {
        return getContext().getSubmitButtonCallbackFactory()
                .createSubmitButtonCallback(this, targetObjectModel, () -> Objects.EMPTY_ARRAY,
                        getModelElement().getBeanPathElement().getInvoker());
    }

    @Override
    public IModel<String> getIconCssClassModel() {
        return new ResourceModel(getModelElement().getIconCssClassPropertyName(), "")
                .wrapOnAssignment(getContext().getMarkupContainer());
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        return bindingBuilder.createSubmitButtonColumn(this);
    }

}
