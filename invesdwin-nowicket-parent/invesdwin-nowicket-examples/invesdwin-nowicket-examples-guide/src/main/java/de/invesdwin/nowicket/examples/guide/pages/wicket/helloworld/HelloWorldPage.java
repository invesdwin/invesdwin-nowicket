package de.invesdwin.nowicket.examples.guide.pages.wicket.helloworld;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@MountPath("helloworld")
public class HelloWorldPage extends AExampleWebPage {

    public HelloWorldPage() {
        this(Model.of(new HelloWorld()));
    }

    public HelloWorldPage(final IModel<HelloWorld> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
