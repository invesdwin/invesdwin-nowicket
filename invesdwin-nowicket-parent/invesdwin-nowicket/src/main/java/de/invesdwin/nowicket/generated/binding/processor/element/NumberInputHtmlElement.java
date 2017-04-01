package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.DecimalFormat;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.spi.element.APropertyBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.AModelElement;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class NumberInputHtmlElement extends AModelHtmlElement<AModelElement<? extends APropertyBeanPathElement>, Number>
        implements ITextInputHtmlElement<Number> {

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

    @Override
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
            propertyFormat = Decimal.newDecimalFormatInstance(format, locale);
        }
        return propertyFormat;
    }

}
