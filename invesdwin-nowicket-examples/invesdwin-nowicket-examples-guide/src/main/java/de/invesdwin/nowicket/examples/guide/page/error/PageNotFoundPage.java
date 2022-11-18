package de.invesdwin.nowicket.examples.guide.page.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.http.WebResponse;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.PageNotFoundPanel;
import jakarta.servlet.http.HttpServletResponse;

@NotThreadSafe
public class PageNotFoundPage extends AExampleWebPage {

    public static final String MOUNT_PATH = "/pagenotfound";

    public PageNotFoundPage() {
        super(null);
        add(new PageNotFoundPanel("panel"));
        setTitleModel(new I18nModel(this, "page.not.found.title"));
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