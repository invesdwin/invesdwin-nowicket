package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.NumberInputModelElement;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class NumberInputHtmlElement extends AModelHtmlElement<NumberInputModelElement, Number> {

    public static final String INPUT_TYPE_NUMBER = "number";

    public static final String DEFAULT_DECIMAL_FORMAT = Decimal.DEFAULT_DECIMAL_FORMAT;
    private DecimalFormat propertyFormat;

    public NumberInputHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitNumberInput(this);
    }

    public Class<?> getType() {
        return getModelElement().getBeanPathElement().getModifier().getAccessor().getRawType().getType();
    }

    @Override
    public DecimalFormat getFormat(final Locale locale) {
        if (propertyFormat == null) {
            String format = super.getFormatString();
            if (format == null) {
                format = DEFAULT_DECIMAL_FORMAT;
            }
            propertyFormat = new DecimalFormat(format, DecimalFormatSymbols.getInstance(locale));
        }
        return propertyFormat;
    }

}
