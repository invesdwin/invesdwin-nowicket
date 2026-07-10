package de.invesdwin.nowicket.generated.binding.processor.element;

import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.FDatePropertyModel;
import de.invesdwin.nowicket.generated.markup.processor.element.DateInputModelElement;
import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.date.format.FDateTimeFormatter;
import de.invesdwin.util.time.date.format.SerializableFDateTimeFormatterProvider;

@NotThreadSafe
public class DateInputHtmlElement extends AModelHtmlElement<DateInputModelElement, FDate> {

    public static final String INPUT_TYPE_DATE = "date";

    /**
     * Using international standard here.
     */
    public static final String DEFAULT_DATE_FORMAT = FDate.FORMAT_ISO_DATE;

    private SerializableFDateTimeFormatterProvider propertyFormat;

    public DateInputHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    public IModel<FDate> getModel() {
        return new FDatePropertyModel(new BeanPathModel<Object>(this));
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitDateInput(this);
    }

    @Override
    public FDateTimeFormatter getFormat(final Locale locale) {
        if (propertyFormat == null) {
            String format = getFormatString();
            if (format == null) {
                format = DEFAULT_DATE_FORMAT;
            }
            propertyFormat = new SerializableFDateTimeFormatterProvider(
                    FDateTimeFormatter.forPattern(format).withLocale(locale));
        }
        return propertyFormat.asFDateTimeFormatter();
    }

}
