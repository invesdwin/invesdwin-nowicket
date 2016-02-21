package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@MountPath("wizard")
@NotThreadSafe
public class WizardStartPage extends AExampleWebPage {

    public WizardStartPage() {
        this(Model.of(new WizardStart()));
    }

    public WizardStartPage(final IModel<WizardStart> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
