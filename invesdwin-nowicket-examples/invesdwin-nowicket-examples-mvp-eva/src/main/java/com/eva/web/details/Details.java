package com.eva.web.details;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class Details extends AValueObject {

    public Details() {
        GuiService.get().showStatusMessage(
                new StatusMessageConfig().setTitle("Message from EVA")
                .setMessage("Your action is needed, Max needs help!")
                .setType(StatusMessageType.error));
    }

    public String detailsImg() {
        return "details.png";
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj == this;
    }

}
