package de.invesdwin.nowicket.examples.guide.page.documentation.frameworkhistory;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("frameworkhistory")
public class FrameworkHistoryPage extends AExampleWebPage {

    public FrameworkHistoryPage() {
        this(Model.of(new FrameworkHistory()));
    }

    public FrameworkHistoryPage(final IModel<FrameworkHistory> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
