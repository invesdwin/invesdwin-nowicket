package de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab4;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class TabbedPanelTab4Panel extends Panel {

    public TabbedPanelTab4Panel(final String id, final IModel<TabbedPanelTab4> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
