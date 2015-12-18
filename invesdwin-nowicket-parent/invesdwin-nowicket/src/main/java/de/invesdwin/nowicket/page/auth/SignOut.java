package de.invesdwin.nowicket.page.auth;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.nowicket.page.home.HomeRedirect;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class SignOut extends AValueObject {

    private final String message;

    public SignOut() {
        this("sign.out.message");
    }

    public SignOut(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HomeRedirect home() {
        return new HomeRedirect();
    }

}
