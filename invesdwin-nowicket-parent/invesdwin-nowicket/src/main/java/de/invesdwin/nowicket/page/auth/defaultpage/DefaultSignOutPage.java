package de.invesdwin.nowicket.page.auth.defaultpage;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebMarkupContainer;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.auth.SignOutPanel;

@NotThreadSafe
public class DefaultSignOutPage extends AWebPage {

    public static final String MOUNT_PATH = "/signout";

    public DefaultSignOutPage() {
        super(null);
        add(new SignOutPanel("panel"));
        add(new WebMarkupContainer("developmentWarning").setVisible(ABaseWebApplication.get().usesDevelopmentConfig()));
        setTitleModel(new I18nModel(this, "sign.out.title"));
    }

}
