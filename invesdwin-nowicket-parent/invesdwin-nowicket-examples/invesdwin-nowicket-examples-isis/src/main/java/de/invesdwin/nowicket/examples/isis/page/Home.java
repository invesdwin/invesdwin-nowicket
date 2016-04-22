package de.invesdwin.nowicket.examples.isis.page;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.injection.Injector;

import com.google.inject.Inject;

import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@GeneratedMarkup
@NotThreadSafe
public class Home implements Serializable {

    @Inject
    private AppUserRegistrationService appUserRegistrationService;

    public Home() {
        Injector.get().inject(this);
    }

    public String getHello() {
        return "NoWicket!" + appUserRegistrationService;
    }

}
