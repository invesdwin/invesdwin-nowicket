package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class BooleanInheritanceDynamicEditorFieldPanel extends AInheritanceDynamicEditorFieldPanel {

    public BooleanInheritanceDynamicEditorFieldPanel(final String id,
            final IModel<BooleanInheritanceDynamicEditorField> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
