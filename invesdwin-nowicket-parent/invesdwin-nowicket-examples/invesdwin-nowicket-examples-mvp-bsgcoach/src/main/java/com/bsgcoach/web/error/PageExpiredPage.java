package com.bsgcoach.web.error;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;

import com.bsgcoach.web.ABsgCoachWebPage;

import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model.I18nDelegateModel;
import de.invesdwin.nowicket.page.error.PageExpiredPanel;

@NotThreadSafe
public class PageExpiredPage extends ABsgCoachWebPage {

    public static final String MOUNT_PATH = "/pageexpired";

    public PageExpiredPage() {
        super(null);
        add(new PageExpiredPanel("panel"));
        setTitleModel(new I18nDelegateModel(this, Model.of("page.expired.title")));
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