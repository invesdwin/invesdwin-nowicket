package de.invesdwin.nowicket.application.auth;

import java.util.List;

public interface IAuthenticationService {

    boolean evaluateExpression(String spel);

    boolean isRememberMe();

    Object getAuthentication();

    boolean isAnonymous();

    boolean isAuthenticated();

    boolean isFullyAuthenticated();

    boolean authenticate(String username, String password);

    List<String> getRoles();

    void signOut();

    ISavedRequest getSavedRequest();

    void setSavedRequest(ISavedRequest savedRequest);

    boolean shouldShowAccessDeniedExceptionMessage(Throwable t);

    void convertUsernamePasswordToRememberMeAuthentication();

    void setAuthentication(Object authentication);

}
