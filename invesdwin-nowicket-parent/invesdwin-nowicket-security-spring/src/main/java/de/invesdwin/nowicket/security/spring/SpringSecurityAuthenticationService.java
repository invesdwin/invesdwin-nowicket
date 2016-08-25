package de.invesdwin.nowicket.security.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.auth.ISavedRequest;
import de.invesdwin.nowicket.security.spring.internal.DelegateSavedRequest;
import de.invesdwin.nowicket.security.spring.internal.SpringSecuritySessionAttributes;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class SpringSecurityAuthenticationService implements IAuthenticationService {

    public static final String DISABLED_ROLE_PREFIX = "";

    private static final org.springframework.expression.ParserContext PARSER_CONTEXT = new org.springframework.expression.ParserContext() {

        @Override
        public boolean isTemplate() {
            return false;
        }

        @Override
        public String getExpressionSuffix() {
            return null;
        }

        @Override
        public String getExpressionPrefix() {
            return null;
        }
    };
    private static final FilterChain FILTER_CHAIN = new FilterChain() {
        @Override
        public void doFilter(final ServletRequest request, final ServletResponse response)
                throws IOException, ServletException {
            throw new UnsupportedOperationException();
        }
    };
    private static final SecurityExpressionHandler<FilterInvocation> SECURITY_EXPRESSION_HANDLER = new DefaultWebSecurityExpressionHandler() {
        {
            setDefaultRolePrefix(DISABLED_ROLE_PREFIX);
        }
    };
    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory
            .getXLogger(SpringSecurityAuthenticationService.class);

    private final AuthenticationManager authenticationManager;

    public SpringSecurityAuthenticationService(final AuthenticationManager authenticationManager) {
        Assertions.assertThat(authenticationManager).isNotNull();
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Override
    public boolean evaluateExpression(final String spel) {
        final org.springframework.expression.ExpressionParser parser = SECURITY_EXPRESSION_HANDLER
                .getExpressionParser();
        final org.springframework.expression.Expression exp = parser.parseExpression(spel, PARSER_CONTEXT);
        final HttpServletRequest request = RequestCycles.getContainerRequest();
        final HttpServletResponse response = RequestCycles.getContainerResponse();
        final Authentication authentication = getAuthentication();
        final FilterInvocation invocation = new FilterInvocation(request, response, FILTER_CHAIN);
        final org.springframework.expression.EvaluationContext ctx = SECURITY_EXPRESSION_HANDLER
                .createEvaluationContext(authentication, invocation);
        return ExpressionUtils.evaluateAsBoolean(exp, ctx);
    }

    @Override
    public boolean isRememberMe() {
        return getAuthentication() instanceof RememberMeAuthenticationToken;
    }

    @Override
    public Authentication getAuthentication() {
        return SpringSecuritySessionAttributes.getAuthentication();
    }

    @Override
    public void setAuthentication(final Object authentication) {
        SpringSecuritySessionAttributes.setAuthentication((Authentication) authentication);
    }

    @Override
    public boolean isAnonymous() {
        return getAuthentication() instanceof AnonymousAuthenticationToken;
    }

    @Override
    public boolean isAuthenticated() {
        return !isAnonymous();
    }

    @Override
    public boolean isFullyAuthenticated() {
        return !isAnonymous() && !isRememberMe();
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        try {
            final Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated()) {
                SpringSecuritySessionAttributes.setAuthentication(authentication);
                SpringSecuritySessionAttributes.updateSpringSecurityContext();
                return true;
            } else {
                return false;
            }
        } catch (final AuthenticationException e) {
            LOG.catching(e);
            return false;
        }
    }

    @Override
    public List<String> getRoles() {
        final List<String> roles = new ArrayList<String>();
        final Authentication authentication = SpringSecuritySessionAttributes.getAuthentication();
        for (final GrantedAuthority authority : authentication.getAuthorities()) {
            final String role = authority.getAuthority();
            roles.add(role);
        }
        return roles;
    }

    @Override
    public void signOut() {
        final HttpServletRequest request = RequestCycles.getContainerRequest();
        try {
            request.logout();
            //            Assertions.assertThat(isSignedIn()).isFalse();
            //            Assertions.assertThat(Roles.isAnonymous()).isTrue();
        } catch (final ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ISavedRequest getSavedRequest() {
        final SavedRequest savedRequest = SpringSecuritySessionAttributes.getSavedRequest();
        if (savedRequest == null) {
            return null;
        } else {
            return new DelegateSavedRequest(savedRequest);
        }
    }

    @Override
    public Object beforeReplaceSession() {
        return getSavedRequest();
    }

    @Override
    public void afterReplaceSession(final Object beforeReplaceSession) {
        final DelegateSavedRequest delegateSavedRequest = (DelegateSavedRequest) beforeReplaceSession;
        if (delegateSavedRequest != null) {
            SpringSecuritySessionAttributes.setSavedRequest(delegateSavedRequest.getDelegate());
        }
        SpringSecuritySessionAttributes.updateSpringSecurityContext();
    }

    @Override
    public boolean shouldShowAccessDeniedExceptionMessage(final Throwable t) {
        return t instanceof AccessDeniedException;
    }

    @Override
    public void convertUsernamePasswordToRememberMeAuthentication() {
        SpringSecuritySessionAttributes.convertUsernamePasswordToRememberMeAuthentication();
    }

    @Override
    public String getUsername() {
        return getAuthentication().getName();
    }

    @Override
    public boolean shouldReplaceSessionAfterSignIn() {
        return true;
    }

}
