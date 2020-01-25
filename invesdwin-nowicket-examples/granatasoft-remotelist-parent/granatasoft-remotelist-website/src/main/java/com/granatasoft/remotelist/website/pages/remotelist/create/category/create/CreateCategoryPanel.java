package com.granatasoft.remotelist.website.pages.remotelist.create.category.create;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateCategoryPanel extends Panel {

    public CreateCategoryPanel(final String id, final IModel<CreateCategory> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
