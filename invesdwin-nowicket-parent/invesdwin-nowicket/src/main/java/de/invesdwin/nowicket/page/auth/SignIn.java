package de.invesdwin.nowicket.page.auth;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.request.flow.RedirectToUrlException;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.auth.ISavedRequest;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import jakarta.validation.constraints.NotNull;

@GeneratedMarkup
@NotThreadSafe
public class SignIn extends AValueObject {

    @NotNull
    private String username;
    @NotNull
    private String password;

    private boolean rememberMe;
    private Component component;

    @Hidden(skip = true)
    public void setComponent(final Component component) {
        this.component = component;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(final boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void signIn() {
        final IAuthenticationStrategy strategy = ABaseWebApplication.get()
                .getSecuritySettings()
                .getAuthenticationStrategy();

        final AWebSession session = AWebSession.get();
        if (session.signIn(getUsername(), getPassword())) {
            if (rememberMe) {
                strategy.save(username, password);
            } else {
                strategy.remove();
            }

            setUsername(null);
            setPassword(null);

            onSignInSucceeded();
        } else {
            onSignInFailed();
            strategy.remove();
        }
    }

    @Hidden
    public void onSignInFailed() {
        component.error(component.getLocalizer().getString("sign.in.failed", component));
    }

    @Hidden
    public void onSignInSucceeded() {
        final IAuthenticationService authenticationService = Roles.getAuthenticationService();
        if (authenticationService != null) {
            // If login has been called because the user was not yet logged in, then continue to the
            // original destination, otherwise to the Home page
            final ISavedRequest savedRequest = authenticationService.getSavedRequest();
            //saved request might be null from spring-security, since redirect to login might have been handled by wicket instead
            if (savedRequest != null && savedRequest.getRedirectUrl() != null) {
                throw new RedirectToUrlException(savedRequest.getRedirectUrl());
            }
        }
        component.continueToOriginalDestination();
        throw new RestartResponseException(ABaseWebApplication.get().getHomePage());
    }

    @Hidden
    public void checkRememberMe() {
        final AWebSession session = AWebSession.get();
        if (!session.isSignedIn()) {
            final IAuthenticationStrategy authenticationStrategy = ABaseWebApplication.get()
                    .getSecuritySettings()
                    .getAuthenticationStrategy();
            // get username and password from persistence store
            final String[] data = authenticationStrategy.load();

            if ((data != null) && (data.length > 1)) {
                // try to sign in the user
                final String username = data[0];
                final String password = data[1];
                if (session.signIn(username, password)) {
                    final IAuthenticationService authenticationService = Roles.getAuthenticationService();
                    if (authenticationService != null) {
                        authenticationService.convertUsernamePasswordToRememberMeAuthentication();
                    }
                    onSignInSucceeded();
                } else {
                    // the loaded credentials are wrong. erase them.
                    authenticationStrategy.remove();
                }
            }
        }
    }

}
