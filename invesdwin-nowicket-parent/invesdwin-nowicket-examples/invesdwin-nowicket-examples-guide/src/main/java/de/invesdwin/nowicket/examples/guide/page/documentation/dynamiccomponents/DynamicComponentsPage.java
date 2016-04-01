package de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("dynamiccomponents")
public class DynamicComponentsPage extends AExampleWebPage {

    public DynamicComponentsPage() {
        this(Model.of(new DynamicComponents()));
    }

    public DynamicComponentsPage(final IModel<?> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
