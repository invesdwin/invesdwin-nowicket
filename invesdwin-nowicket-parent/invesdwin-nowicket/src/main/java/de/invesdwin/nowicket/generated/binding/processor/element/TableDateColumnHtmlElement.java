package de.invesdwin.nowicket.generated.binding.processor.element;

import java.util.Date;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.BeanPathModel;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.DatePropertyModel;
import de.invesdwin.nowicket.generated.markup.processor.element.TableDateColumnModelElement;

@NotThreadSafe
public class TableDateColumnHtmlElement extends ATableColumnHtmlElement<TableDateColumnModelElement, Date> {

    public static final String DEFAULT_DATE_FORMAT = DateInputHtmlElement.DEFAULT_DATE_FORMAT;

    private java.text.SimpleDateFormat propertyFormat;

    public TableDateColumnHtmlElement(final HtmlContext context, final TableDateColumnModelElement modelElement) {
        super(context, modelElement);
    }

    @Override
    public java.text.SimpleDateFormat getFormat(final Locale locale) {
        if (propertyFormat == null) {
            String format = getFormatString();
            if (format == null) {
                format = DEFAULT_DATE_FORMAT;
            }
            propertyFormat = new java.text.SimpleDateFormat(format, locale);
        }
        return propertyFormat;
    }

    @Override
    public IModel<Date> getModel() {
        return new DatePropertyModel(new BeanPathModel<Object>(this));
    }

    @Override
    public IColumn<? extends Object, String> createWicketColumn(final IBindingBuilder bindingBuilder) {
        return bindingBuilder.createDateColumn(this);
    }

}
