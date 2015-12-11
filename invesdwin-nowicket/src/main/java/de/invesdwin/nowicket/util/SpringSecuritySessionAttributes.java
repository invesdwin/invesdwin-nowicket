package de.invesdwin.nowicket.util;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.ExtendedHttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@NotThreadSafe
public final class SpringSecuritySessionAttributes {

    private static final class ExtendedAnonymousAuthenticationFilter extends AnonymousAuthenticationFilter {
        public static final String KEY = "dummy";

        private ExtendedAnonymousAuthenticationFilter() {
            super(KEY);
        }

        @Override
        public Authentication createAuthentication(final HttpServletRequest request) {
            return super.createAuthentication(request);
        }
    }

    private static final ExtendedHttpSessionRequestCache HTTP_SESSION_REQUEST_CACHE = new ExtendedHttpSessionRequestCache();

    private SpringSecuritySessionAttributes() {}

    public static SavedRequest getSavedRequest() {
        final HttpServletRequest request = getContainerRequest();
        final HttpServletResponse response = getContainerResponse();
        final SavedRequest savedRequest = HTTP_SESSION_REQUEST_CACHE.getRequest(request, response);
        return savedRequest;
    }

    public static void setSavedRequest(final SavedRequest savedRequest) {
        final HttpServletRequest request = getContainerRequest();
        HTTP_SESSION_REQUEST_CACHE.copyRequest(request, savedRequest);
    }

    public static void updateSpringSecurityContext() {
        final HttpServletRequest request = getContainerRequest();
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }

    public static void setAuthentication(final Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && getContainerRequest() != null) {
            //this is needed for test cases where no filter created whe anonymous authentication
            final ExtendedAnonymousAuthenticationFilter anonFilter = new ExtendedAnonymousAuthenticationFilter();
            final HttpServletRequest request = getContainerRequest();
            authentication = anonFilter.createAuthentication(request);
            authentication = new AnonymousAuthenticationToken(ExtendedAnonymousAuthenticationFilter.KEY,
                    anonFilter.getPrincipal(), anonFilter.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return authentication;
    }

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

    /**
     * This is needed so that expressions in spring-security SPEL work properly.
     */
    public static void convertUsernamePasswordToRememberMeAuthentication() {
        final UsernamePasswordAuthenticationToken usernamePassword = (UsernamePasswordAuthenticationToken) getAuthentication();
        final RememberMeAuthenticationToken rememberMe = new RememberMeAuthenticationToken(
                ExtendedAnonymousAuthenticationFilter.KEY, usernamePassword.getPrincipal(),
                usernamePassword.getAuthorities());
        setAuthentication(rememberMe);
    }
}
