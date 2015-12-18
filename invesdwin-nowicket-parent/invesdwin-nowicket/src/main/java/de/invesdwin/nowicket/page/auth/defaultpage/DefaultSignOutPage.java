package de.invesdwin.nowicket.page.auth.defaultpage;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.auth.SignOutPanel;

@NotThreadSafe
public class DefaultSignOutPage extends AWebPage {

    public static final String MOUNT_PATH = "/signout";

    public DefaultSignOutPage() {
        super(null);
        add(new SignOutPanel("panel"));
        add(new WebMarkupContainer("developmentWarning").setVisible(ABaseWebApplication.get().usesDevelopmentConfig()));
        setTitleModel(new I18nDelegateModel(this, Model.of("sign.out.title")));
    }

}
