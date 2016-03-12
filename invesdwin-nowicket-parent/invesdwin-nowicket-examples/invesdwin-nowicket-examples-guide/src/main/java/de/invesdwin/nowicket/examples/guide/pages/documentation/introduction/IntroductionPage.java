package de.invesdwin.nowicket.examples.guide.pages.documentation.introduction;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("introduction")
public class IntroductionPage extends AExampleWebPage {

    public IntroductionPage() {
        this(Model.of(new Introduction()));
    }

    public IntroductionPage(final IModel<Introduction> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
