package de.invesdwin.nowicket.page.auth.defaultpage;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.AWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.auth.SignInPanel;

@NotThreadSafe
public class DefaultSignInPage extends AWebPage {

    public static final String MOUNT_PATH = "/signin";

    public DefaultSignInPage() {
        super(null);
        add(new SignInPanel("panel"));
        add(new WebMarkupContainer("developmentWarning").setVisible(AWebApplication.get().usesDevelopmentConfig()));
        setTitleModel(new I18nDelegateModel(this, Model.of("sign.in.title")));
    }

}
