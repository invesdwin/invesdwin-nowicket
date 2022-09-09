package de.invesdwin.nowicket.util;

import java.util.Enumeration;
import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;

import de.invesdwin.nowicket.application.filter.AWebApplication;
import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;

@NotThreadSafe
public final class RequestCycles {

    private static final int KOPERNIO_EXCEPTION_STATUS_CODE = HttpServletResponse.SC_FORBIDDEN;
    private static final String KOPERNIO_EXCEPTION_MESSAGE = "Kopernio/EndNode Click is disallowed during testing because it messes up rendering requests on localhost";

    private static final MetaDataKey<Page> PAGE_KEY = new MetaDataKey<Page>() {
    };
    private static final MetaDataKey<IPartialPageRequestHandler> PARTIAL_PAGE_REQUEST_HANDLER_KEY = new MetaDataKey<IPartialPageRequestHandler>() {
    };

    private RequestCycles() {}

    public static HttpServletRequest getContainerRequest() {
        final RequestCycle requestCycle = RequestCycle.get();
        return getContainerRequest(requestCycle);
    }

    public static HttpServletRequest getContainerRequest(final RequestCycle requestCycle) {
        if (requestCycle == null) {
            return null;
        }
        final Request request = requestCycle.getRequest();
        if (request == null) {
            return null;
        }
        return (HttpServletRequest) request.getContainerRequest();
    }

    public static HttpServletResponse getContainerResponse() {
        final RequestCycle requestCycle = RequestCycle.get();
        return getContainerResponse(requestCycle);
    }

    public static HttpServletResponse getContainerResponse(final RequestCycle requestCycle) {
        if (requestCycle == null) {
            return null;
        }
        final Response response = requestCycle.getResponse();
        if (response == null) {
            return null;
        }
        return (HttpServletResponse) response.getContainerResponse();
    }

    public static boolean isOnePassRender() {
        final RequestCycle requestCycle = RequestCycle.get();
        return isOnePassRender(requestCycle);
    }

    public static boolean isOnePassRender(final RequestCycle requestCycle) {
        final IRequestParameters params = requestCycle.getRequest().getQueryParameters();
        for (final String param : new String[] { "onePassRender", "robot", "bot" }) {
            final String onePassRender = String.valueOf(params.getParameterValue(param));
            if (BooleanUtils.toBoolean(onePassRender)) {
                return true;
            }
        }
        if (detectKopernio(requestCycle)) {
            throw new AbortWithHttpErrorCodeException(KOPERNIO_EXCEPTION_STATUS_CODE, KOPERNIO_EXCEPTION_MESSAGE);
        }
        return false;
    }

    private static boolean detectKopernio(final RequestCycle requestCycle) {
        //see https://github.com/invesdwin/invesdwin-nowicket/issues/14
        if (!AWebApplication.get().usesDevelopmentConfig()) {
            return false;
        }
        final String host = requestCycle.getRequest().getOriginalUrl().getHost();
        if (!("localhost".equals(host) || "127.0.0.1".equals(host))) {
            return false;
        }
        final HttpServletRequest request = getContainerRequest(requestCycle);
        if (!"*/*".equals(request.getHeader("accept"))) {
            return false;
        }
        if (!"empty".equals(request.getHeader("sec-fetch-dest"))) {
            return false;
        }
        if (!"same-origin".equals(request.getHeader("sec-fetch-site"))) {
            return false;
        }
        if (!"cors".equals(request.getHeader("sec-fetch-mode"))) {
            return false;
        }
        if (request.getHeader("sec-fetch-user") != null) {
            return false;
        }
        return true;
    }

    public static boolean shouldSwallowException(final Integer statusCode, final String message) {
        if (statusCode.intValue() == KOPERNIO_EXCEPTION_STATUS_CODE && KOPERNIO_EXCEPTION_MESSAGE.equals(message)) {
            return true;
        }
        return false;
    }

    public static Page getPage() {
        final RequestCycle requestCycle = RequestCycle.get();
        return getPage(requestCycle);
    }

    public static Page getPage(final RequestCycle requestCycle) {
        Page page = requestCycle.getMetaData(PAGE_KEY);
        if (page != null) {
            return page;
        }
        final IPageRequestHandler lastHandler = PageRequestHandlerTracker.getLastHandler(requestCycle);
        if (lastHandler == null) {
            return null;
        }
        page = (Page) lastHandler.getPage();
        setPage(page);
        return page;
    }

    public static void setPage(final Page page) {
        final Page existingPage = RequestCycle.get().getMetaData(PAGE_KEY);
        if (existingPage != null && existingPage != page) {
            GuiTasksHolder.get(page).setGuiTasks(GuiTasksHolder.get(existingPage).getGuiTasks());
        }
        RequestCycle.get().setMetaData(PAGE_KEY, page);
    }

    public static String getFullURL(final HttpServletRequest request) {
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = request.getRequestURI();
        }
        final StringBuilder requestURL = new StringBuilder();
        requestURL.append(requestUri);
        final String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    public static void setPartialPageRequestHandler(final Component component,
            final IPartialPageRequestHandler handler) {
        final RequestCycle requestCycle = getRequestCycle(component);
        requestCycle.setMetaData(PARTIAL_PAGE_REQUEST_HANDLER_KEY, handler);
        if (component != null) {
            setPage(component.getPage());
        }
    }

    public static PreactPartialPageRequestHandler getPartialPageRequestHandler(final Component component) {
        final RequestCycle requestCycle = getRequestCycle(component);
        final Optional<IPartialPageRequestHandler> handler = requestCycle.find(IPartialPageRequestHandler.class);
        if (handler.isPresent()) {
            return PreactPartialPageRequestHandler.of(requestCycle, handler.get());
        } else {
            return PreactPartialPageRequestHandler.of(requestCycle,
                    requestCycle.getMetaData(PARTIAL_PAGE_REQUEST_HANDLER_KEY));
        }
    }

    public static RequestCycle getRequestCycle(final Component component) {
        if (component == null) {
            return RequestCycle.get();
        } else {
            return component.getRequestCycle();
        }
    }

    public static String requestToString(final HttpServletRequest request) {
        final StringBuilder sb = new StringBuilder();

        sb.append("Request Method = [" + request.getMethod() + "]");
        sb.append("\n");
        sb.append("Request URL Path = [" + request.getRequestURL() + "]");

        int headersCount = 0;
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            sb.append("\n");
            headersCount++;
            sb.append(headersCount);
            sb.append(". Request Header [");
            sb.append(headerName);
            sb.append("] = ");
            final Enumeration<String> headers = request.getHeaders(headerName);
            boolean firstHeader = true;
            while (headers.hasMoreElements()) {
                if (firstHeader) {
                    firstHeader = false;
                } else {
                    sb.append(", ");
                }
                final String header = headers.nextElement();
                sb.append(header);
            }
        }
        if (headersCount == 0) {
            sb.append("\n");
            sb.append("Request Headers: NONE");
        }

        int parametersCount = 0;
        final Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            final String parameterName = parameterNames.nextElement();
            sb.append("\n");
            parametersCount++;
            sb.append(parametersCount);
            sb.append(". Request Parameter [");
            sb.append(parameterName);
            sb.append("] = ");
            final String[] parameterValues = request.getParameterValues(parameterName);
            boolean firstParameterValue = true;
            for (int i = 0; i < parameterValues.length; i++) {
                if (firstParameterValue) {
                    firstParameterValue = false;
                } else {
                    sb.append(", ");
                }
                final String parameterValue = parameterValues[i];
                sb.append(parameterValue);
            }
        }

        int attributesCount = 0;
        final Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final String attributeName = attributeNames.nextElement();
            sb.append("\n");
            attributesCount++;
            sb.append(attributesCount);
            sb.append(". Request Attribute [");
            sb.append(attributeName);
            sb.append("] = ");
            final Object attribute = request.getAttribute(attributeName);
            sb.append(attribute);
        }

        final Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            final Cookie cookie = cookies[i];
            sb.append("\n");
            sb.append(i + 1);
            sb.append(". Cookie [");
            sb.append(cookie.getName());
            sb.append("] = ");
            sb.append(cookie.getValue());
        }

        return sb.toString();
    }

}
