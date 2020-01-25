package com.granatasoft.remotelist.website.components.login.button.child.dialog.resource;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class LoginDialogPanel extends Panel {
    public LoginDialogPanel(final String id, final IModel<LoginDialog> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
