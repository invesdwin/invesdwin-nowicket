package de.invesdwin.nowicket.security.spring;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.auth.ISavedRequest;

@NotThreadSafe
public class ShiroAuthenticationService implements IAuthenticationService {

    public ShiroAuthenticationService() {}

    @Override
    public boolean evaluateExpression(final String spel) {
        return false;
    }

    @Override
    public boolean isRememberMe() {
        return false;
    }

    @Override
    public Object getAuthentication() {
        return null;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public boolean isFullyAuthenticated() {
        return false;
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        return false;
    }

    @Override
    public List<String> getRoles() {
        return null;
    }

    @Override
    public void signOut() {}

    @Override
    public ISavedRequest getSavedRequest() {
        return null;
    }

    @Override
    public void setSavedRequest(final ISavedRequest savedRequest) {}

    @Override
    public boolean shouldShowAccessDeniedExceptionMessage(final Throwable t) {
        return false;
    }

    @Override
    public void convertUsernamePasswordToRememberMeAuthentication() {}

    @Override
    public void setAuthentication(final Object authentication) {}

}
