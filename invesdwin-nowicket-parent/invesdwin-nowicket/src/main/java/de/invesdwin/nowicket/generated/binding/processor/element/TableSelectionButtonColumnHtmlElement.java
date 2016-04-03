package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.button.callback.ISubmitButtonCallback;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ChoiceAsTableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableSelectionButtonColumnModelElement;

@NotThreadSafe
public class TableSelectionButtonColumnHtmlElement
        extends ATableColumnHtmlElement<TableSelectionButtonColumnModelElement, Object>
        implements ITableButtonColumn<TableSelectionButtonColumnModelElement, Object> {

    public TableSelectionButtonColumnHtmlElement(final HtmlContext context,
            final TableSelectionButtonColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Override
    public TableSelectionButtonColumnModelElement getModelElement() {
        final AChoiceModelElement<?> choiceModelElement = getContext().getModelObjectContext()
                .getElementRegistry()
                .getElement(getWicketId());
        final ATableModelElement tableModelElement = ChoiceAsTableModelElement.maybeWrap(choiceModelElement);
        return tableModelElement.getSelectionButtonColumn();
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        return bindingBuilder.createSelectionButtonColumn(this);
    }

    @Override
    public ISubmitButtonCallback getButtonCallback(final IModel<Object> targetObjectModel) {
        return getContext().getSubmitButtonCallbackFactory().createSubmitButtonCallback(this, targetObjectModel,
                getModelElement().getBeanPathElement().getSelectionModifier().getToggleSelectionInvoker());
    }

    @Deprecated
    @Override
    public IModel<String> getIconCssClassModel() {
        return null;
    }

}
