package com.eva.web.error;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.http.WebResponse;

import com.eva.web.AEvaWebPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.PageNotFoundPanel;

@NotThreadSafe
public class PageNotFoundPage extends AEvaWebPage {

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