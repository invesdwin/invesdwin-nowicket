package de.invesdwin.nowicket.examples.guide.pages.wicket.forminput;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Line extends AValueObject {

    private String value;

    public Line(final String value) {
        this.value = value;
    }

    public String getValue() {
        return GuiService.i18n(LinePanel.class, value);
    }

    public void setValue(final String value) {
        this.value = value;
    }

}
