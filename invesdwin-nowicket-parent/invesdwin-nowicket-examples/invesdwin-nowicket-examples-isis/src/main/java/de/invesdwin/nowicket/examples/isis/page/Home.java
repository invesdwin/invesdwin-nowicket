package de.invesdwin.nowicket.examples.isis.page;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.examples.isis.integration.IsisInjector;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
@AuthorizeInstantiation
public class Home extends AValueObject {

    @Inject
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

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Home;
    }

}
