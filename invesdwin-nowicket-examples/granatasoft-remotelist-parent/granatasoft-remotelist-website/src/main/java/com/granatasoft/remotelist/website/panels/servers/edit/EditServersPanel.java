package com.granatasoft.remotelist.website.panels.servers.edit;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class EditServersPanel extends Panel {
    public EditServersPanel(final String id, final IModel<EditServers> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
