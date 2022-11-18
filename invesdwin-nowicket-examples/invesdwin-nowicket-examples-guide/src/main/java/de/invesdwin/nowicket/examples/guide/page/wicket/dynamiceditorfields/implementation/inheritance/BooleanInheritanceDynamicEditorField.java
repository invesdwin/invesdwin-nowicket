package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.Title;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.DynamicEditorFieldType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import jakarta.validation.constraints.NotNull;

@NotThreadSafe
@GeneratedMarkup
public class BooleanInheritanceDynamicEditorField extends AInheritanceDynamicEditorField {

    @NotNull
    private Boolean boolValue;

    @Title("Value")
    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(final Boolean boolValue) {
        this.boolValue = boolValue;
    }

    @Hidden(skip = true)
    @Override
    public Object getValue() {
        return boolValue;
    }

    @Override
    public DynamicEditorFieldType getType() {
        return DynamicEditorFieldType.Boolean;
    }

}
