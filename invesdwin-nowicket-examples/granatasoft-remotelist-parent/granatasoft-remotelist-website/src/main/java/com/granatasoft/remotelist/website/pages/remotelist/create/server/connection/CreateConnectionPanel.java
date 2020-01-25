package com.granatasoft.remotelist.website.pages.remotelist.create.server.connection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateConnectionPanel extends Panel {
    public CreateConnectionPanel(final String id, final IModel<CreateConnection> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
