package de.invesdwin.nowicket.application.auth;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import de.invesdwin.nowicket.util.SpringSecuritySessionAttributes;

@NotThreadSafe
public class Roles extends org.apache.wicket.authroles.authorization.strategies.role.Roles {

    private static final String DISABLED_ROLE_PREFIX = "";

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

    public static Roles get() {
        return AWebSession.get().getRoles();
    }

    /**
     * Evaluates spring security expressions like "hasRole('ADMIN')"
     */
    public static boolean evaluateExpression(final String spel) {
        final org.springframework.expression.ExpressionParser parser = SECURITY_EXPRESSION_HANDLER
                .getExpressionParser();
        final org.springframework.expression.Expression exp = parser.parseExpression(spel, PARSER_CONTEXT);
        final HttpServletRequest request = SpringSecuritySessionAttributes.getContainerRequest();
        final HttpServletResponse response = SpringSecuritySessionAttributes.getContainerResponse();
        final Authentication authentication = getAuthentication();
        final FilterInvocation invocation = new FilterInvocation(request, response, FILTER_CHAIN);
        final org.springframework.expression.EvaluationContext ctx = SECURITY_EXPRESSION_HANDLER
                .createEvaluationContext(authentication, invocation);
        return ExpressionUtils.evaluateAsBoolean(exp, ctx);
    }

    /**
     * Returns true if the user is not an anonymous or a remember-me user
     * 
     * See: http://stackoverflow.com/questions/10625049/how-to-implement-spring-security-spel-isfullyauthenticated-
     * programmitcally-in
     */
    public static boolean isFullyAuthenticated() {
        return !isAnonymous() && !isRememberMe();
    }

    /**
     * Returns true if the current principal is a remember-me user
     */
    public static boolean isRememberMe() {
        return getAuthentication() instanceof RememberMeAuthenticationToken;
    }

    public static Authentication getAuthentication() {
        return SpringSecuritySessionAttributes.getAuthentication();
    }

    /**
     * Returns true if the current principal is an anonymous user
     */
    public static boolean isAnonymous() {
        return getAuthentication() instanceof AnonymousAuthenticationToken;
    }

    /**
     * Returns true if the user is not anonymous
     */
    public static boolean isAuthenticated() {
        return !isAnonymous();
    }

}
