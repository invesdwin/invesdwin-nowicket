package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class AjaxTimerTablePanel extends Panel {

    public AjaxTimerTablePanel(final String id, final IModel<AjaxTimerTable> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
