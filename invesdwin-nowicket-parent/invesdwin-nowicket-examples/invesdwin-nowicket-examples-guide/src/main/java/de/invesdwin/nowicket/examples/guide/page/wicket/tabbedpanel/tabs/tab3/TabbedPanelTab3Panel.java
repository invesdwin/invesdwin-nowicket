package de.invesdwin.nowicket.examples.guide.page.wicket.tabbedpanel.tabs.tab3;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class TabbedPanelTab3Panel extends Panel {

    public TabbedPanelTab3Panel(final String id, final IModel<TabbedPanelTab3> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
