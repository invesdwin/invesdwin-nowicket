package com.granatasoft.remotelist.website.pages.servers.serverpaneltitle;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.IResource;

@NotThreadSafe
public abstract class AServerTitlePanel extends Panel {

    public AServerTitlePanel(final String id, final IResource logoModel, final IModel<String> badgeModel) {
        super(id);
        if (logoModel == null) {
            add(new WebMarkupContainer("logo").setVisible(false));
        } else {
            final Image logo = new Image("logo", logoModel);
            add(logo);
        }

        final Label badge = new Label("badge", badgeModel);
        add(badge);

        final Component link = newLink("link");
        add(link);

        final Component button = newButton("button");
        add(button);

        final Component crud = newCrudPanel("crud");
        add(crud);
    }

    protected Component newButton(final String id) {
        return new WebMarkupContainer(id);
    }

    protected Component newCrudPanel(final String id) {
        return new CrudPanel("crud", Model.of(new Crud(null)));
    }

    protected abstract Component newLink(String id);

}
