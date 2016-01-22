package de.invesdwin.nowicket.examples.pages.auth;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.examples.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.auth.SignInPanel;

@NotThreadSafe
public class ExampleSignInPage extends AExampleWebPage {

    public static final String MOUNT_PATH = "/signin";

    public ExampleSignInPage() {
        super(null);
        add(new SignInPanel("panel"));
        setTitleModel(new I18nDelegateModel(this, Model.of("sign.in.title")));
    }

}
