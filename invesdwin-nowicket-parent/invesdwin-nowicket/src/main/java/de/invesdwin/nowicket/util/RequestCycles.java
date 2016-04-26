package de.invesdwin.nowicket.util;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;

@NotThreadSafe
public final class RequestCycles {

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

}
