package com.eva.web.dashboard.row;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class DashboardRowPanel extends Panel {

    public DashboardRowPanel(final String id, final IModel<DashboardRow> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }
}
