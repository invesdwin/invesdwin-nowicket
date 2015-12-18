package de.invesdwin.nowicket.page.auth;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class SignInPanel extends Panel {

    public SignInPanel(final String id) {
        this(id, Model.of(new SignIn()));
    }

    public SignInPanel(final String id, final IModel<SignIn> model) {
        super(id, model);
        model.getObject().setComponent(this);
        new GeneratedBinding(this).bind();
    }

}
