package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ChoiceAsTableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableContainerColumnModelElement;

@NotThreadSafe
public class TableContainerColumnHtmlElement extends ATableColumnHtmlElement<TableContainerColumnModelElement, Object> {

    public TableContainerColumnHtmlElement(final HtmlContext context,
            final TableContainerColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Override
    protected boolean isRowContainer() {
        return false;
    }

    @Override
    public TableContainerColumnModelElement getModelElement() {
        final AChoiceModelElement<?> choiceModelElement = getContext().getModelObjectContext()
                .getElementRegistry()
                .getElement(getWicketId());
        final ATableModelElement tableModelElement = ChoiceAsTableModelElement.maybeWrap(choiceModelElement);
        return tableModelElement.getContainerColumn();
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        return bindingBuilder.createContainerColumn(this);
    }

}
