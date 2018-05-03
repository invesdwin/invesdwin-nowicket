package de.invesdwin.nowicket.util;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;

@NotThreadSafe
public final class RequestCycles {

    private static final MetaDataKey<Page> PAGE_KEY = new MetaDataKey<Page>() {
    };

    private RequestCycles() {}

    public static HttpServletRequest getContainerRequest() {
        final RequestCycle requestCycle = RequestCycle.get();
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
        final IRequestParameters params = RequestCycle.get().getRequest().getQueryParameters();
        for (final String param : new String[] { "onePassRender", "robot", "bot" }) {
            final String onePassRender = String.valueOf(params.getParameterValue(param));
            if (BooleanUtils.toBoolean(onePassRender)) {
                return true;
            }
        }
        return false;
    }

    public static Page getPage() {
        Page page = RequestCycle.get().getMetaData(PAGE_KEY);
        if (page != null) {
            return page;
        }
        final IPageRequestHandler lastHandler = PageRequestHandlerTracker.getLastHandler(RequestCycle.get());
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

}
