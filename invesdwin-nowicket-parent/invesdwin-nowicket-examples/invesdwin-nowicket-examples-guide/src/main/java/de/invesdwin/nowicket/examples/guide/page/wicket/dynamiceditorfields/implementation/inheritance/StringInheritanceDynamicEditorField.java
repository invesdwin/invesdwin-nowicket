package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.Title;
import de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.DynamicEditorFieldType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@NotThreadSafe
@GeneratedMarkup
public class StringInheritanceDynamicEditorField extends AInheritanceDynamicEditorField {

    @NotNull
    private String strValue;

    @Title("Value")
    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(final String strValue) {
        this.strValue = strValue;
    }

    @Hidden(skip = true)
    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public DynamicEditorFieldType getType() {
        return DynamicEditorFieldType.String;
    }

}
