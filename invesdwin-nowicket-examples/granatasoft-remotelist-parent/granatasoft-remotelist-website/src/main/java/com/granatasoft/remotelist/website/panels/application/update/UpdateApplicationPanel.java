package com.granatasoft.remotelist.website.panels.application.update;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class UpdateApplicationPanel extends Panel {

    public UpdateApplicationPanel(final String id, final IModel<UpdateApplication> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
