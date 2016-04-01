package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("userinterfaceflow")
public class UserInterfaceFlowPage extends AExampleWebPage {

    public UserInterfaceFlowPage() {
        this(Model.of(new UserInterfaceFlow()));
    }

    public UserInterfaceFlowPage(final IModel<UserInterfaceFlow> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
