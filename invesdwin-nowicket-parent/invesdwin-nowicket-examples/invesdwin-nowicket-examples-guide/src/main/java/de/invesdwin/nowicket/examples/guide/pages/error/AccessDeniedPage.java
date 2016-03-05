package de.invesdwin.nowicket.examples.guide.pages.error;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.AccessDeniedPanel;

@NotThreadSafe
public class AccessDeniedPage extends AExampleWebPage {

    public static final String MOUNT_PATH = "/accessdenied";

    public AccessDeniedPage() {
        super(null);
        add(new AccessDeniedPanel("panel"));
        setTitleModel(new I18nModel(this, "access.denied.title"));
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