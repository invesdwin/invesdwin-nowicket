package de.invesdwin.nowicket.page.auth;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class SignOutPanel extends Panel {

    public SignOutPanel(final String id) {
        this(id, Model.of(new SignOut()));
    }

    public SignOutPanel(final String id, final IModel<SignOut> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
        if (AWebSession.get().isSignedIn()) {
            getSession().invalidate();
        }
    }

}
