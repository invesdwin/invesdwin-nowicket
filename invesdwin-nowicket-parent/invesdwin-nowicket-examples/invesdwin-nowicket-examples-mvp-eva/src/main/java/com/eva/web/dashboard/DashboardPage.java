package com.eva.web.dashboard;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("dashboard")
@NotThreadSafe
public class DashboardPage extends AEvaWebPage {

    public DashboardPage() {
        this(Model.of(new Dashboard()));
    }

    public DashboardPage(final IModel<Dashboard> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
