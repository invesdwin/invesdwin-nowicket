package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.date;

import java.util.Locale;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.util.convert.converter.AbstractConverter;

import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.date.format.FDateTimeFormatter;

/**
 * Adapted from AbstractDateConverter
 */
@Immutable
public class FDateConverter extends AbstractConverter<FDate> {

    public static final String DEFAULT_PATTERN = FDate.FORMAT_ISO_DATE;

    private static final long serialVersionUID = 1L;

    @Override
    public FDate convertToObject(final String value, final Locale locale) {
        if (org.apache.wicket.util.string.Strings.isEmpty(value)) {
            return null;
        }

        final FDateTimeFormatter format = getDateFormat(locale);
        final FDate date = parse(format, value, locale);
        return date;
    }

    @Override
    public String convertToString(final FDate value, final Locale locale) {
        if (value == null) {
            return null;
        }

        final FDateTimeFormatter dateFormat = getDateFormat(locale);
        if (dateFormat != null) {
            return dateFormat.format(value);
        }
        return value.toString();
    }

    /**
     * @param locale
     * @return Returns the date format.
     */
    public FDateTimeFormatter getDateFormat(final Locale pLocale) {
        final Locale locale;
        if (pLocale == null) {
            locale = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            locale = pLocale;
        }
        return FDateTimeFormatter.forPattern(FDate.FORMAT_ISO_DATE).withLocale(locale);
    }

    @Override
    protected Class<FDate> getTargetType() {
        return FDate.class;
    }
}
