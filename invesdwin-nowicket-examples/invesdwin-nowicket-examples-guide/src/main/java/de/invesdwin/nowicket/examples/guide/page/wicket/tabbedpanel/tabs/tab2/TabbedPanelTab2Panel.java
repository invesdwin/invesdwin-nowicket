package de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab2;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class TabbedPanelTab2Panel extends Panel {

    public TabbedPanelTab2Panel(final String id, final IModel<TabbedPanelTab2> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
