package de.invesdwin.nowicket.page.error;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.util.SpringSecuritySessionAttributes;

@NotThreadSafe
public abstract class AErrorPanel extends Panel {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(AErrorPanel.class);

    public AErrorPanel(final String id, final IModel<?> model) {
        super(id, model);
        showServletException();
    }

    protected void showServletException() {
        final HttpServletRequest request = SpringSecuritySessionAttributes.getContainerRequest();
        final Throwable exception = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        final Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        final String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        final String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        final Class<?> exceptionType = (Class<?>) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
        final String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        final StringBuilder sb = new StringBuilder();
        maybeAppend("RequestUri", requestUri, sb);
        maybeAppend("ServletName", servletName, sb);
        maybeAppend("StatusCode", statusCode, sb);
        maybeAppend("ExceptionType", (exceptionType == null ? null : exceptionType.getName()), sb);
        maybeAppend("Message", message, sb);
        if (exception != null || sb.length() > 0) {
            final RuntimeException loggedExc = new RuntimeException(new ServletException(sb.toString(), exception));
            if (ABaseWebApplication.get().usesDevelopmentConfig()) {
                //show development mode internal error page in wicket
                throw loggedExc;
            } else {
                LOG.catching(loggedExc);
            }
        }
    }

    protected void maybeAppend(final String title, final Object value, final StringBuilder sb) {
        if (value != null) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(title);
            sb.append(": ");
            sb.append(value);
        }
    }

}
