package de.invesdwin.nowicket.examples.pages.home;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.examples.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class HomePage extends AExampleWebPage {

    public HomePage() {
        this(Model.of(new Home()));
    }

    public HomePage(final IModel<Home> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

}
