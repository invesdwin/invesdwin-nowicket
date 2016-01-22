package de.invesdwin.nowicket.examples.pages.secure;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.examples.pages.home.Home;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class WicketSecure extends AValueObject {

    public boolean isAdmin() {
        return Roles.get().hasRole(Roles.ADMIN);
    }

    public Home home() throws Exception {
        return new Home();
    }

}
