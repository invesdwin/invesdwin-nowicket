package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class AjaxTimerTab extends AValueObject {

    @NotBlank
    @NotNull
    private String requiredInput;

    public String getRequiredInput() {
        return requiredInput;
    }

    public void setRequiredInput(final String requiredInput) {
        this.requiredInput = requiredInput;
    }

    public void save() {}

    @Override
    public String toString() {
        return requiredInput;
    }

}
