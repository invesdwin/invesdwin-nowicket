package de.invesdwin.nowicket.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.nowicket.page.home.HomeRedirect;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class InternalError extends AValueObject {

    private final String message;

    public InternalError() {
        this("internal.error.message");
    }

    public InternalError(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HomeRedirect home() {
        return new HomeRedirect();
    }

}
