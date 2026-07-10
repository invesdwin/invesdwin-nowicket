package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IPropertyReflectionAwareModel;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadablePartial;

import de.invesdwin.util.time.date.FDate;

@NotThreadSafe
public class FDatePropertyModel implements IPropertyReflectionAwareModel<FDate> {

    private final IPropertyReflectionAwareModel<Object> delegate;

    public FDatePropertyModel(final IPropertyReflectionAwareModel<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public FDate getObject() {
        final Object obj = delegate.getObject();
        if (obj == null) {
            return null;
        } else if (obj instanceof FDate) {
            final FDate cObj = (FDate) obj;
            return cObj;
        } else if (obj instanceof Date) {
            final Date cObj = (Date) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof Calendar) {
            final Calendar cObj = (Calendar) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof LocalDateTime) {
            final LocalDateTime cObj = (LocalDateTime) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof ZonedDateTime) {
            final ZonedDateTime cObj = (ZonedDateTime) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof ReadableDateTime) {
            final ReadableDateTime cObj = (ReadableDateTime) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof LocalDate) {
            final LocalDate cObj = (LocalDate) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof ReadablePartial) {
            final ReadablePartial cObj = (ReadablePartial) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof LocalDateTime) {
            final LocalDateTime cObj = (LocalDateTime) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof ZonedDateTime) {
            final ZonedDateTime cObj = (ZonedDateTime) obj;
            return FDate.valueOf(cObj);
        } else if (obj instanceof LocalDate) {
            final LocalDate cObj = (LocalDate) obj;
            return FDate.valueOf(cObj);
        } else {
            throw new IllegalArgumentException("Unknown type: " + obj.getClass().getName());
        }
    }

    @Override
    public void setObject(final FDate object) {
        final Class<?> type;
        if (delegate.getPropertyGetter() != null) {
            type = delegate.getPropertyGetter().getReturnType();
        } else {
            type = delegate.getPropertyField().getType();
        }
        if (object == null) {
            delegate.setObject(null);
        } else if (FDate.class.isAssignableFrom(type)) {
            delegate.setObject(object);
        } else if (Date.class.isAssignableFrom(type)) {
            final Date date = object.dateValue();
            delegate.setObject(date);
        } else if (Calendar.class.isAssignableFrom(type)) {
            final Calendar date = object.calendarValue();
            delegate.setObject(date);
        } else if (LocalDateTime.class.isAssignableFrom(type)) {
            final LocalDateTime date = object.jodaTimeValue();
            delegate.setObject(date);
        } else if (ReadableDateTime.class.isAssignableFrom(type)) {
            final DateTime date = object.jodaTimeValueZoned();
            delegate.setObject(date);
        } else if (ReadablePartial.class.isAssignableFrom(type)) {
            final LocalDate date = object.jodaDateValue();
            delegate.setObject(date);
        } else if (java.time.LocalDateTime.class.isAssignableFrom(type)) {
            final java.time.LocalDateTime date = object.javaTimeValue();
            delegate.setObject(date);
        } else if (java.time.ZonedDateTime.class.isAssignableFrom(type)) {
            final ZonedDateTime date = object.javaTimeValueZoned();
            delegate.setObject(date);
        } else if (java.time.LocalDate.class.isAssignableFrom(type)) {
            final java.time.LocalDate date = object.javaDateValue();
            delegate.setObject(date);
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
