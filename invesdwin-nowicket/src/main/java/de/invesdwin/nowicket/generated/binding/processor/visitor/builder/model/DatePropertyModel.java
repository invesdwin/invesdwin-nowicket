package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IPropertyReflectionAwareModel;

import de.invesdwin.util.time.fdate.FDate;

@NotThreadSafe
public class DatePropertyModel implements IPropertyReflectionAwareModel<Date> {

    private final IPropertyReflectionAwareModel<Object> delegate;

    public DatePropertyModel(final IPropertyReflectionAwareModel<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Date getObject() {
        final Object obj = delegate.getObject();
        if (obj == null) {
            return null;
        } else if (obj instanceof Date) {
            return (Date) obj;
        } else if (obj instanceof Calendar) {
            final Calendar cObj = (Calendar) obj;
            return cObj.getTime();
        } else if (obj instanceof FDate) {
            final FDate cObj = (FDate) obj;
            return cObj.dateValue();
        } else {
            throw new IllegalArgumentException("Unknown type: " + obj.getClass().getName());
        }
    }

    @Override
    public void setObject(final Date object) {
        final Class<?> type = delegate.getPropertyGetter().getReturnType();
        if (object == null) {
            delegate.setObject(null);
        } else if (Date.class.isAssignableFrom(type)) {
            delegate.setObject(object);
        } else if (Calendar.class.isAssignableFrom(type)) {
            final Calendar cal = FDate.valueOf(object).calendarValue();
            delegate.setObject(cal);
        } else if (FDate.class.isAssignableFrom(type)) {
            final FDate fdate = FDate.valueOf(object);
            delegate.setObject(fdate);
        } else {
            throw new IllegalArgumentException("Unknown type: " + type.getName());
        }
    }

    @Override
    public void detach() {
        delegate.detach();
    }

    @Override
    public Field getPropertyField() {
        return delegate.getPropertyField();
    }

    @Override
    public Method getPropertyGetter() {
        return delegate.getPropertyGetter();
    }

    @Override
    public Method getPropertySetter() {
        return delegate.getPropertySetter();
    }

}
