package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.Title;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.DynamicEditorFieldType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@NotThreadSafe
@GeneratedMarkup
public class IntegerInheritanceDynamicEditorField extends AInheritanceDynamicEditorField {

    @NotNull
    private Integer intValue;

    @Title("Value")
    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(final Integer intValue) {
        this.intValue = intValue;
    }

    @Hidden(skip = true)
    @Override
    public Object getValue() {
        return intValue;
    }

    @Override
    public DynamicEditorFieldType getType() {
        return DynamicEditorFieldType.Integer;
    }

}
