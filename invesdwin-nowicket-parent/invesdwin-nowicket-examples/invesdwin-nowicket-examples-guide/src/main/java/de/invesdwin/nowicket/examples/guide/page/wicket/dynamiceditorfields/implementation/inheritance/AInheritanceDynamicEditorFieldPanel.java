package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

@NotThreadSafe
public abstract class AInheritanceDynamicEditorFieldPanel extends Panel {

    public AInheritanceDynamicEditorFieldPanel(final String id,
            final IModel<? extends AInheritanceDynamicEditorField> model) {
        super(id, model);
    }

}
