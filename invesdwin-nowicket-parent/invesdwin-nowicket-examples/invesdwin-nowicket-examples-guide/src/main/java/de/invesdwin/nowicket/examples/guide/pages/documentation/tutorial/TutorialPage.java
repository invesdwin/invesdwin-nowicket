package de.invesdwin.nowicket.examples.guide.pages.documentation.tutorial;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("tutorial")
public class TutorialPage extends AExampleWebPage {

    public TutorialPage() {
        this(Model.of(new Tutorial()));
    }

    public TutorialPage(final IModel<Tutorial> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
