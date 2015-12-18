package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.AbstractDecimalConverter;

import de.invesdwin.nowicket.generated.binding.processor.element.NumberInputHtmlElement;
import de.invesdwin.util.lang.Reflections;

@SuppressWarnings({ "unchecked", "rawtypes" })
@NotThreadSafe
public class ModelNumberTextField extends NumberTextField {

    private final NumberInputHtmlElement element;

    public ModelNumberTextField(final NumberInputHtmlElement element) {
        super(element.getWicketId(), element.getModel(), element.getType());
        this.element = element;
    }

    @Override
    public <C> IConverter<C> getConverter(final Class<C> type) {
        final IConverter<C> converter = super.getConverter(type);
        if (converter instanceof AbstractDecimalConverter<?>) {
            final AbstractDecimalConverter<?> adc = (AbstractDecimalConverter<?>) converter;
            final ConcurrentHashMap<Locale, NumberFormat> numberFormats = Reflections.field("numberFormats")
                    .ofType(ConcurrentHashMap.class)
                    .in(adc)
                    .get();
            for (final Locale l : Locale.getAvailableLocales()) {
                final DecimalFormat format = element.getFormat();
                format.setParseBigDecimal(true);
                numberFormats.put(l, format);
            }
        }
        return converter;
    }

}
