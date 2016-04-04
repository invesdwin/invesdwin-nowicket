package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tables.row;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class ExtendedTableRowPanel extends Panel {

    public ExtendedTableRowPanel(final String id, final IModel<ExtendedTableRow> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
