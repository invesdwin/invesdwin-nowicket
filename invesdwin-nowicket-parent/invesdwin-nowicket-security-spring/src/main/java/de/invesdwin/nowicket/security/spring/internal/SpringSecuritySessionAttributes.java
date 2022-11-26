package de.invesdwin.nowicket.security.spring.internal;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.ExtendedHttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import de.invesdwin.nowicket.util.RequestCycles;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@NotThreadSafe
public final class SpringSecuritySessionAttributes {

    private static final class ExtendedAnonymousAuthenticationFilter extends AnonymousAuthenticationFilter {
        public static final String KEY = "dummy";

        private ExtendedAnonymousAuthenticationFilter() {
            super(KEY);
        }

        @Override
        protected Authentication createAuthentication(final HttpServletRequest request) {
            return super.createAuthentication(request);
        }

    }

    private static final ExtendedHttpSessionRequestCache HTTP_SESSION_REQUEST_CACHE = new ExtendedHttpSessionRequestCache();

    private SpringSecuritySessionAttributes() {}

    public static SavedRequest getSavedRequest() {
        final HttpServletRequest request = RequestCycles.getContainerRequest();
        final HttpServletResponse response = RequestCycles.getContainerResponse();
        final SavedRequest savedRequest = HTTP_SESSION_REQUEST_CACHE.getRequest(request, response);
        return savedRequest;
    }

    public static void setSavedRequest(final SavedRequest savedRequest) {
        final HttpServletRequest request = RequestCycles.getContainerRequest();
        HTTP_SESSION_REQUEST_CACHE.copyRequest(request, savedRequest);
    }

    public static void updateSpringSecurityContext() {
        final HttpServletRequest request = RequestCycles.getContainerRequest();
        request.getSession()
                .setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext());
    }

    public static void setAuthentication(final Authentication authentication) {
        final SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        //replace a deferred context
        SecurityContextHolder.setContext(context);
    }

    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && RequestCycles.getContainerRequest() != null) {
            //this is needed for test cases where no filter created whe anonymous authentication
            final ExtendedAnonymousAuthenticationFilter anonFilter = new ExtendedAnonymousAuthenticationFilter();
            final HttpServletRequest request = RequestCycles.getContainerRequest();
            authentication = anonFilter.createAuthentication(request);
            authentication = new AnonymousAuthenticationToken(ExtendedAnonymousAuthenticationFilter.KEY,
                    anonFilter.getPrincipal(), anonFilter.getAuthorities());
            setAuthentication(authentication);
        }
        return authentication;
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
