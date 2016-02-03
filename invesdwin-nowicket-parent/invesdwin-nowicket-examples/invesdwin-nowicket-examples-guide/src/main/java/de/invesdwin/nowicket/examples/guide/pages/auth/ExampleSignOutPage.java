package de.invesdwin.nowicket.examples.guide.pages.auth;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.auth.SignOutPanel;

@NotThreadSafe
public class ExampleSignOutPage extends AExampleWebPage {

    public static final String MOUNT_PATH = "/signout";

    public ExampleSignOutPage() {
        super(null);
        add(new SignOutPanel("panel"));
        setTitleModel(new I18nDelegateModel(this, Model.of("sign.out.title")));
    }

}
