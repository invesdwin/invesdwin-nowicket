package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.DecimalFormat;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.TableNumberColumnModelElement;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class TableNumberColumnHtmlElement extends ATableColumnHtmlElement<TableNumberColumnModelElement, Number> {

    public static final String DEFAULT_DECIMAL_FORMAT = NumberInputHtmlElement.DEFAULT_DECIMAL_FORMAT;
    private DecimalFormat propertyFormat;

    public TableNumberColumnHtmlElement(final HtmlContext context, final TableNumberColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Override
    protected boolean isRowContainer() {
        return true;
    }

    @Override
    public DecimalFormat getFormat(final Locale locale) {
        if (propertyFormat == null) {
            String format = super.getFormatString();
            if (format == null) {
                format = DEFAULT_DECIMAL_FORMAT;
            }
            propertyFormat = Decimal.newDecimalFormatInstance(format, locale);
        }
        return propertyFormat;
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        return bindingBuilder.createNumberColumn(this);
    }

}