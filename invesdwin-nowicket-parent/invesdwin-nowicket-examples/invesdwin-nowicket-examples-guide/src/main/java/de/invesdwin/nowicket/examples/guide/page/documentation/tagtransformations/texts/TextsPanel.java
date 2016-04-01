package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.texts;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class TextsPanel extends Panel {

    public TextsPanel(final String id, final IModel<Texts> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
