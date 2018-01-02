package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.simple;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class SimpleDynamicEditorFieldPanel extends Panel {

    public SimpleDynamicEditorFieldPanel(final String id, final IModel<SimpleDynamicEditorField> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
