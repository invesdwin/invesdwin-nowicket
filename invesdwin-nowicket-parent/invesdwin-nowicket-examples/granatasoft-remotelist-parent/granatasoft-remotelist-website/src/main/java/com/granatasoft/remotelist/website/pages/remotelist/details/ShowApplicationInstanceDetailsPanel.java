package com.granatasoft.remotelist.website.pages.remotelist.details;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ShowApplicationInstanceDetailsPanel extends Panel {

    public ShowApplicationInstanceDetailsPanel(final String id, final IModel<ShowApplicationInstanceDetails> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
