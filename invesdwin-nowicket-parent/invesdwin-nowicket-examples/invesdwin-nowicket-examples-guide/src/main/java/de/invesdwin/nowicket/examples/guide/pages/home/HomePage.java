package de.invesdwin.nowicket.examples.guide.pages.home;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("introduction")
public class HomePage extends AExampleWebPage {

    public HomePage() {
        this(Model.of(new Home()));
    }

    public HomePage(final IModel<Home> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
