package com.granatasoft.remotelist.website.components.confirm;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Confirm extends AValueObject {
    public String title() {
        return "Are you sure you want to delete this?";
    }

    @ModalCloser
    public void yes() {
    }

    @ModalCloser
    public void no() {
    }
}
