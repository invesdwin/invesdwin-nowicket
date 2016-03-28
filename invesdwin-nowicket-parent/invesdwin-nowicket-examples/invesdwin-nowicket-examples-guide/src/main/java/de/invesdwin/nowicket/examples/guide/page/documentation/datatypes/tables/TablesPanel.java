package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class TablesPanel extends Panel {

    public TablesPanel(final String id, final IModel<Tables> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
