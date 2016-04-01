package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.choices;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ChoicesPanel extends Panel {

    public ChoicesPanel(final String id, final IModel<Choices> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
