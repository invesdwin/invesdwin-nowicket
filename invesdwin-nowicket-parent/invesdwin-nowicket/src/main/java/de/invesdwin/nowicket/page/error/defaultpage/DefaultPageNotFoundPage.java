package de.invesdwin.nowicket.page.error.defaultpage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;

import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.AWebApplication;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.error.PageNotFoundPanel;

@NotThreadSafe
public class DefaultPageNotFoundPage extends AWebPage {

    public static final String MOUNT_PATH = "/pagenotfound";

    public DefaultPageNotFoundPage() {
        super(null);
        add(new PageNotFoundPanel("panel"));
        add(new WebMarkupContainer("developmentWarning").setVisible(AWebApplication.get().usesDevelopmentConfig()));
        setTitleModel(new I18nDelegateModel(this, Model.of("page.not.found.title")));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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