package de.invesdwin.nowicket.examples.isis.page.users.changepassword;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ChangePasswordPanel extends Panel {

    public ChangePasswordPanel(final String id, final IModel<ChangePassword> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
