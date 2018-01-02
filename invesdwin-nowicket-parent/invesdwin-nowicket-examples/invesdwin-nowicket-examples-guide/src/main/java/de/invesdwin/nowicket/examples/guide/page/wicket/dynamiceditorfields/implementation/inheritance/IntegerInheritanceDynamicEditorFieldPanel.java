package de.invesdwin.nowicket.examples.guide.page.wicket.dynamiceditorfields.implementation.inheritance;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class IntegerInheritanceDynamicEditorFieldPanel extends AInheritanceDynamicEditorFieldPanel {

    public IntegerInheritanceDynamicEditorFieldPanel(final String id,
            final IModel<IntegerInheritanceDynamicEditorField> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
