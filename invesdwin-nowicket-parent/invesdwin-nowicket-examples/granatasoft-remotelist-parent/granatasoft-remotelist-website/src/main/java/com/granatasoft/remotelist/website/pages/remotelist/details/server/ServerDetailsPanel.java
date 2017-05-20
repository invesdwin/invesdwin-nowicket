package com.granatasoft.remotelist.website.pages.remotelist.details.server;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ServerDetailsPanel extends Panel {

    public ServerDetailsPanel(final String id, final IModel<ServerDetails> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
