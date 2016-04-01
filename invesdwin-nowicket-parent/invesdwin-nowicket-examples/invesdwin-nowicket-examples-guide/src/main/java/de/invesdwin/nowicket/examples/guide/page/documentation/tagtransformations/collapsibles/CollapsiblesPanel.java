package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.collapsibles;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CollapsiblesPanel extends Panel {

    public CollapsiblesPanel(final String id, final IModel<Collapsibles> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
