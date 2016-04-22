package de.invesdwin.nowicket.examples.isis.page;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.isis.core.runtime.system.context.IsisContext;

import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@GeneratedMarkup
@NotThreadSafe
public class Home implements Serializable {

    @javax.inject.Inject
    private transient AppUserRegistrationService appUserRegistrationService;

    public Home() {
        IsisContext.getPersistenceSession().getServicesInjector().injectServicesInto(this);
    }

    public String getHello() {
        return "NoWicket! " + (appUserRegistrationService != null);
    }

}
