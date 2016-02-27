package de.invesdwin.nowicket.examples.guide.pages.wicket.tabbedpanel.tabs.tab1;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class TabbedPanelTab1Panel extends Panel {

    public TabbedPanelTab1Panel(final String id, final IModel<TabbedPanelTab1> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
