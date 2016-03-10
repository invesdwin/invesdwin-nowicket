package de.invesdwin.nowicket.examples.guide.pages.documentation.wicketintegration;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("wicketintegration")
public class WicketIntegrationPage extends AExampleWebPage {

    public WicketIntegrationPage() {
        this(Model.of(new WicketIntegration()));
    }

    public WicketIntegrationPage(final IModel<WicketIntegration> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
