package org.springframework.security.web.savedrequest;

import javax.annotation.concurrent.NotThreadSafe;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Hacked implementation to be able to copy the saved request to a new instance.
 */
@NotThreadSafe
public class ExtendedHttpSessionRequestCache extends HttpSessionRequestCache {

    public void copyRequest(final HttpServletRequest request, final SavedRequest savedRequest) {
        request.getSession().setAttribute(SAVED_REQUEST, savedRequest);
    }
}
