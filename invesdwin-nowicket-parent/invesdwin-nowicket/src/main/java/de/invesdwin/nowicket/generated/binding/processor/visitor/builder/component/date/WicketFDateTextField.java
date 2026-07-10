package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.date;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.date.format.FDateTimeFormatter;

/**
 * Adapted from: org.apache.wicket.extensions.markup.html.form.DateTextField
 */
@NotThreadSafe
public class WicketFDateTextField extends TextField<FDate> implements ITextFormatProvider {

    private static final long serialVersionUID = 1L;

    /**
     * The date pattern of the text field
     */
    private String datePattern = null;

    /**
     * The converter for the TextField
     */
    private final IConverter<FDate> converter;

    /**
     * Creates a new DateTextField, without a specified pattern. This is the same as calling
     * <code>new TextField(id, Date.class)</code>
     * 
     * @param id
     *            The id of the text field
     * 
     * @see org.apache.wicket.markup.html.form.TextField
     */
    public WicketFDateTextField(final String id) {
        this(id, null, defaultDatePattern());
    }

    /**
     * Creates a new DateTextField, without a specified pattern. This is the same as calling
     * <code>new TextField(id, object, Date.class)</code>
     * 
     * @param id
     *            The id of the text field
     * @param model
     *            The model
     * 
     * @see org.apache.wicket.markup.html.form.TextField
     */
    public WicketFDateTextField(final String id, final IModel<FDate> model) {
        this(id, model, defaultDatePattern());
    }

    /**
     * Creates a new DateTextField bound with a specific <code>SimpleDateFormat</code> pattern.
     * 
     * @param id
     *            The id of the text field
     * @param datePattern
     *            A <code>SimpleDateFormat</code> pattern
     * 
     * @see org.apache.wicket.markup.html.form.TextField
     */
    public WicketFDateTextField(final String id, final String datePattern) {
        this(id, null, datePattern);
    }

    /**
     * Creates a new DateTextField bound with a specific <code>SimpleDateFormat</code> pattern.
     * 
     * @param id
     *            The id of the text field
     * @param model
     *            The model
     * @param datePattern
     *            A <code>SimpleDateFormat</code> pattern
     * 
     * @see org.apache.wicket.markup.html.form.TextField
     */
    public WicketFDateTextField(final String id, final IModel<FDate> model, final String datePattern) {
        super(id, model, FDate.class);
        this.datePattern = datePattern;
        converter = new FDateConverter() {
            private static final long serialVersionUID = 1L;

            /**
             * @see org.apache.wicket.util.convert.converter.DateConverter#getDateFormat(java.util.Locale)
             */
            @Override
            public FDateTimeFormatter getDateFormat(final Locale pLocale) {
                final Locale locale;
                if (pLocale == null) {
                    locale = Locale.getDefault(Locale.Category.FORMAT);
                } else {
                    locale = pLocale;
                }
                return FDateTimeFormatter.forPattern(WicketFDateTextField.this.datePattern).withLocale(locale);
            }
        };
    }

    /**
     * Returns the default converter if created without pattern; otherwise it returns a pattern-specific converter.
     * 
     * @param type
     *            The type for which the convertor should work
     * 
     * @return A pattern-specific converter
     * 
     * @see org.apache.wicket.markup.html.form.TextField
     */
    @Override
    protected IConverter<?> createConverter(final Class<?> type) {
        if (Date.class.isAssignableFrom(type)) {
            return converter;
        }
        return null;
    }

    /**
     * Returns the date pattern.
     * 
     * @see org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider#getTextFormat()
     */
    @Override
    public String getTextFormat() {
        return datePattern;
    }

    /**
     * Try to get datePattern from user session locale. If it is not possible, it will return {@link #DEFAULT_PATTERN}
     * 
     * @return date pattern
     */
    private static String defaultDatePattern() {
        // It is possible to retrieve from session?
        final Locale locale = org.apache.wicket.Session.get().getLocale();
        if (locale != null) {
            final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            if (format instanceof java.text.SimpleDateFormat) {
                return ((java.text.SimpleDateFormat) format).toPattern();
            }
        }
        return FDateConverter.DEFAULT_PATTERN;
    }

    @Override
    protected String[] getInputTypes() {
        return new String[] { "text", "date", "datetime", "datetime-local", "month", "time", "week" };
    }
}
