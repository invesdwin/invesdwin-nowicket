package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.links;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class LinksPanel extends Panel {

    public LinksPanel(final String id, final IModel<Links> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
