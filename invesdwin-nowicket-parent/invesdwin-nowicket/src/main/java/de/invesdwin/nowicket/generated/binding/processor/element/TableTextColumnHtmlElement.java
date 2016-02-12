package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.TableTextColumnModelElement;

@NotThreadSafe
public class TableTextColumnHtmlElement extends ATableColumnHtmlElement<TableTextColumnModelElement, Object> {

    public TableTextColumnHtmlElement(final HtmlContext context, final TableTextColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        return bindingBuilder.createTextColumn(this);
    }

}
