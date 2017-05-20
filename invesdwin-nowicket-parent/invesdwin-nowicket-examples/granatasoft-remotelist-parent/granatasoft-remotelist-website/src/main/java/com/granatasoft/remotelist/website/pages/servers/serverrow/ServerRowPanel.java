package com.granatasoft.remotelist.website.pages.servers.serverrow;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ServerRowPanel extends Panel {
    public ServerRowPanel(final String id, final IModel<ServerRow> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
