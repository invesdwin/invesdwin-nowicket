package com.granatasoft.remotelist.website.pages.remotelist.create.category.update;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class UpdateCategoryPanel extends Panel {

    public UpdateCategoryPanel(final String id, final IModel<UpdateCategory> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
