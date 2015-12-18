package de.invesdwin.nowicket.page.home;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.AWebApplication;

@NotThreadSafe
public class HomeRedirectPage extends AWebPage {

    public HomeRedirectPage() {
        this(Model.of(new HomeRedirect()));
    }

    public HomeRedirectPage(final IModel<HomeRedirect> model) {
        super(model);
        throw new RestartResponseException(AWebApplication.get().getHomePage());
    }

}
