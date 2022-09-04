package de.invesdwin.nowicket.util;

import java.util.Optional;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.RequestDispatcher;
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

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;

@NotThreadSafe
public final class RequestCycles {

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

}
