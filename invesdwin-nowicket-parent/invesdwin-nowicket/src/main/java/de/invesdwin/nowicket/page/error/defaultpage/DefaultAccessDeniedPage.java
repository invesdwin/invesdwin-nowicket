package de.invesdwin.nowicket.page.error.defaultpage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.AWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.error.AccessDeniedPanel;

@NotThreadSafe
public class DefaultAccessDeniedPage extends AWebPage {

    public static final String MOUNT_PATH = "/accessdenied";

    public DefaultAccessDeniedPage() {
        super(null);
        add(new AccessDeniedPanel("panel"));
        add(new WebMarkupContainer("developmentWarning").setVisible(AWebApplication.get().usesDevelopmentConfig()));
        setTitleModel(new I18nDelegateModel(this, Model.of("access.denied.title")));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public boolean isErrorPage() {
        return true;
    }

    @Override
    public boolean isVersioned() {
        return false;
    }

}