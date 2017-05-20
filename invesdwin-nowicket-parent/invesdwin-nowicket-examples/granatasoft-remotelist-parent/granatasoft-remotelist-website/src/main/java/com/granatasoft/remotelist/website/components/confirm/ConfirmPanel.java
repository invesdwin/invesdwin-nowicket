package com.granatasoft.remotelist.website.components.confirm;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ConfirmPanel extends Panel {
    public ConfirmPanel(final String id, final IModel<Confirm> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
