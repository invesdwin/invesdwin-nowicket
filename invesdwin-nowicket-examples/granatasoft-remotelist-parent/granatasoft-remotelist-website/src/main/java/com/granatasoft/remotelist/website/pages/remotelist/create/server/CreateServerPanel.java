package com.granatasoft.remotelist.website.pages.remotelist.create.server;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateServerPanel extends Panel {
    public CreateServerPanel(final String id, final IModel<CreateServer> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
