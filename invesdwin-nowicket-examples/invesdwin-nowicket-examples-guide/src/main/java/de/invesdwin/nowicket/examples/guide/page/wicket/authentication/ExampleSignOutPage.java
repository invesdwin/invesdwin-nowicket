package de.invesdwin.nowicket.examples.guide.page.wicket.authentication;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.auth.SignOutPanel;

@NotThreadSafe
public class ExampleSignOutPage extends AExampleWebPage {

    public static final String MOUNT_PATH = "/signout";

    public ExampleSignOutPage() {
        super(null);
        add(new SignOutPanel("panel"));
        setTitleModel(new I18nModel(this, "sign.out.title"));
    }

}
