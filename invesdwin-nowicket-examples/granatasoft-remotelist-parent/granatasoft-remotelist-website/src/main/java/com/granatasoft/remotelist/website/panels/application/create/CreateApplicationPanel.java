package com.granatasoft.remotelist.website.panels.application.create;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateApplicationPanel extends Panel {

    public CreateApplicationPanel(final String id, final IModel<CreateApplication> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
