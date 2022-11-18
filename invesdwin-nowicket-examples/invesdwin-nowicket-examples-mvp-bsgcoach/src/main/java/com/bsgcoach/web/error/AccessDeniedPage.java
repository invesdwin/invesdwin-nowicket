package com.bsgcoach.web.error;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.http.WebResponse;

import com.bsgcoach.web.ABsgCoachWebPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nModel;
import de.invesdwin.nowicket.page.error.AccessDeniedPanel;
import jakarta.servlet.http.HttpServletResponse;

@NotThreadSafe
public class AccessDeniedPage extends ABsgCoachWebPage {

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