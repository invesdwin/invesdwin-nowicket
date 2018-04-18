package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class AjaxTimerTabPanel extends Panel {

    public AjaxTimerTabPanel(final String id, final IModel<AjaxTimerTab> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
