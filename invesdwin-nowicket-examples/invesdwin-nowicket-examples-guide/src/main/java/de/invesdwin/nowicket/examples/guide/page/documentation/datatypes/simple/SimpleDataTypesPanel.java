package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.simple;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class SimpleDataTypesPanel extends Panel {

    public SimpleDataTypesPanel(final String id, final IModel<SimpleDataTypes> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
