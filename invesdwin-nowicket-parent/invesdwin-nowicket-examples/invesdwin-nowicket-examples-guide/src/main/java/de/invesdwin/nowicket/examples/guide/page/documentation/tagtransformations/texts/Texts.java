package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.texts;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class Texts extends AValueObject {

    private String defaultTextInput = "Text Input";
    private String asTextArea = "Text Area\nText Area\nText Area";

    public String getDefaultTextInput() {
        return defaultTextInput;
    }

    public void setDefaultTextInput(final String defaultTextInput) {
        this.defaultTextInput = defaultTextInput;
    }

    public String getAsTextArea() {
        return asTextArea;
    }

    public void setAsTextArea(final String asTextArea) {
        this.asTextArea = asTextArea;
    }

    public String getAsLabel() {
        return "Label";
    }

    public String getAsParagraph() {
        return "Paragraph";
    }

}
