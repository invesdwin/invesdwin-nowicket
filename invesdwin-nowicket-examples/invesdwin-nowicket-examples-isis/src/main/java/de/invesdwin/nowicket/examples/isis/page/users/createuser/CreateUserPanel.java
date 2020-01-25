package de.invesdwin.nowicket.examples.isis.page.users.createuser;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateUserPanel extends Panel {

    public CreateUserPanel(final String id, final IModel<CreateUser> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
