package com.granatasoft.remotelist.website.pages.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.http.WebResponse;

import com.granatasoft.remotelist.website.pages.ARemotelistPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.InternalErrorPanel;
import de.invesdwin.nowicket.util.RequestCycles;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@NotThreadSafe
public class InternalErrorPage extends ARemotelistPage {

    public static final String MOUNT_PATH = "/internalerror";

    public InternalErrorPage() {
        super(null);
        add(new InternalErrorPanel("panel"));
        setTitleModel(new I18nModel(this, "internal.error.title"));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        final HttpServletRequest request = RequestCycles.getContainerRequest();
        final Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null) {
            response.setStatus(statusCode);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
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