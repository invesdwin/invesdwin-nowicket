package de.invesdwin.nowicket.application.auth;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.application.filter.AWebApplication;

@NotThreadSafe
public class Roles extends org.apache.wicket.authroles.authorization.strategies.role.Roles {

    public static Roles get() {
        return AWebSession.get().getRoles();
    }

    /**
     * Evaluates spring security expressions like "hasRole('ADMIN')"
     */
    public static boolean evaluateExpression(final String spel) {
        return getAuthenticationService().evaluateExpression(spel);
    }

    public static IAuthenticationService getAuthenticationService() {
        return AWebApplication.get().getDelegate().getAuthenticationService();
    }

    /**
     * Returns true if the user is not an anonymous or a remember-me user
     * 
     * See: http://stackoverflow.com/questions/10625049/how-to-implement-spring-security-spel-isfullyauthenticated-
     * programmitcally-in
     */
    public static boolean isFullyAuthenticated() {
        return getAuthenticationService().isFullyAuthenticated();
    }

    /**
     * Returns true if the current principal is a remember-me user
     */
    public static boolean isRememberMe() {
        return getAuthenticationService().isRememberMe();
    }

    /**
     * Returns true if the current principal is an anonymous user
     */
    public static boolean isAnonymous() {
        return getAuthenticationService().isAnonymous();
    }

    /**
     * Returns true if the user is not anonymous
     */
    public static boolean isAuthenticated() {
        return getAuthenticationService().isAuthenticated();
    }

}
