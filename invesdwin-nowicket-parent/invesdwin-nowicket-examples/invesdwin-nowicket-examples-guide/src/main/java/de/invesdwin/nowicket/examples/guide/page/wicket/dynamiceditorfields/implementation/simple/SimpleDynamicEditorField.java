package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.simple;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.Title;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.DynamicEditorFieldType;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.IDynamicEditorField;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.DynamicEditorFieldImplementation;
import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.error.UnknownArgumentException;

@GeneratedMarkup
@NotThreadSafe
public class SimpleDynamicEditorField implements IDynamicEditorField, Serializable {

    private DynamicEditorFieldType type;
    private String strValue;
    private Integer intValue;
    private Boolean boolValue;

    public SimpleDynamicEditorField(final DynamicEditorFieldType type) {
        this.type = type;
    }

    @Title("Value")
    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(final String strValue) {
        this.strValue = strValue;
    }

    public boolean hideStrValue() {
        return type != DynamicEditorFieldType.String;
    }

    @Title("Value")
    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(final Integer intValue) {
        this.intValue = intValue;
    }

    public boolean hideIntValue() {
        return type != DynamicEditorFieldType.Integer;
    }

    @Title("Value")
    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(final Boolean boolValue) {
        this.boolValue = boolValue;
    }

    public boolean hideBoolValue() {
        return type != DynamicEditorFieldType.Boolean;
    }

    @Hidden(skip = true)
    @Override
    public Object getValue() {
        if (type == null) {
            return null;
        }
        switch (type) {
        case String:
            return strValue;
        case Integer:
            return intValue;
        case Boolean:
            return boolValue;
        default:
            throw UnknownArgumentException.newInstance(DynamicEditorFieldType.class, type);
        }
    }

    @Override
    public DynamicEditorFieldType getType() {
        return type;
    }

    @Eager
    public void setType(final DynamicEditorFieldType type) {
        this.type = type;
    }

    @Hidden(skip = true)
    @Override
    public DynamicEditorFieldImplementation getImplementation() {
        return DynamicEditorFieldImplementation.Simple;
    }

}
