package com.granatasoft.remotelist.website.panels.server.edit;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class EditServerPanel extends Panel {
    public EditServerPanel(final String id, final IModel<EditServer> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
