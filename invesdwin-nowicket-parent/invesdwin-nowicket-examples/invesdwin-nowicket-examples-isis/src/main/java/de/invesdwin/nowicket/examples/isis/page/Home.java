package de.invesdwin.nowicket.examples.isis.page;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.examples.isis.integration.IsisInjector;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class Home extends AValueObject {

    @javax.inject.Inject
    private transient AppUserRegistrationService appUserRegistrationService;

    public Home() {
        IsisInjector.inject(this);
    }

    public String getHello() {
        return "NoWicket! " + (appUserRegistrationService != null);
    }

    private void readObject(final java.io.ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        IsisInjector.inject(this);
    }

}
