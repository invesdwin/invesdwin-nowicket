package com.granatasoft.remotelist.website.pages.servers.serverpaneltitle;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CrudPanel extends Panel {
    public CrudPanel(final String id, final IModel<Crud> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
