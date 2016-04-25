package de.invesdwin.nowicket.examples.isis.page.auth;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.auth.SignInPanel;

@NotThreadSafe
public class ExampleSignInPage extends AWebPage {

    public static final String MOUNT_PATH = "/signin";

    public ExampleSignInPage() {
        super(null);
        add(new SignInPanel("panel"));
        setTitleModel(new I18nModel(this, "sign.in.title"));
    }

}
