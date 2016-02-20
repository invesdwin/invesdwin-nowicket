package com.bsgcoach.web.error;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;

import com.bsgcoach.web.ABsgCoachWebPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.error.InternalErrorPanel;
import de.invesdwin.nowicket.util.SpringSecuritySessionAttributes;

@NotThreadSafe
public class InternalErrorPage extends ABsgCoachWebPage {

    public static final String MOUNT_PATH = "/internalerror";

    public InternalErrorPage() {
        super(null);
        add(new InternalErrorPanel("panel"));
        setTitleModel(new I18nDelegateModel(this, Model.of("internal.error.title")));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        final HttpServletRequest request = SpringSecuritySessionAttributes.getContainerRequest();
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