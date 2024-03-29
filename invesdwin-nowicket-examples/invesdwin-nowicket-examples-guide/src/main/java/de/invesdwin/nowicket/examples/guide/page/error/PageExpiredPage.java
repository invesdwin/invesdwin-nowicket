package de.invesdwin.nowicket.examples.guide.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.http.WebResponse;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.PageExpiredPanel;
import jakarta.servlet.http.HttpServletResponse;

@NotThreadSafe
public class PageExpiredPage extends AExampleWebPage {

    public static final String MOUNT_PATH = "/pageexpired";

    public PageExpiredPage() {
        super(null);
        add(new PageExpiredPanel("panel"));
        setTitleModel(new I18nModel(this, "page.expired.title"));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        response.setStatus(HttpServletResponse.SC_GONE);
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