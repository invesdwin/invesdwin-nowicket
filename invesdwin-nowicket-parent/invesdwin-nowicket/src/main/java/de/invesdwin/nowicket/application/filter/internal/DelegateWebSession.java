package de.invesdwin.nowicket.application.filter.internal;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.Request;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class DelegateWebSession extends AWebSession {

    public DelegateWebSession(final Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        final IAuthenticationService authenticationService = Roles.getAuthenticationService();
        if (authenticationService == null) {
            return true;
        } else {
            return authenticationService.authenticate(username, password);
        }
    }

    @Override
    public Roles getRoles() {
        final Roles roles = new Roles();
        final IAuthenticationService authenticationService = Roles.getAuthenticationService();
        if (authenticationService != null && isSignedIn()) {
            final List<String> listRoles = Roles.getAuthenticationService().getRoles();
            if (listRoles != null) {
                for (final String role : listRoles) {
                    roles.add(role);
                }
            }
        }
        return roles;
    }

    @Override
    public void replaceSession() {
        if (isSignedIn()) {
            //validate role prefixes here on new session
            Assertions.assertThat(getRoles()).isNotNull();
        }
        //copy previous savedRequest to new session in order to properly redirect after login
        final IAuthenticationService authenticationService = Roles.getAuthenticationService();
        Object beforeReplaceSession = null;
        if (authenticationService != null) {
            beforeReplaceSession = authenticationService.beforeReplaceSession();
        }
        super.replaceSession();
        if (authenticationService != null) {
            authenticationService.afterReplaceSession(beforeReplaceSession);
        }
    }

    @Override
    protected void signOut() {
        final IAuthenticationService authenticationService = Roles.getAuthenticationService();
        if (authenticationService != null) {
            authenticationService.signOut();
        }
    }

    @Override
    public boolean isSignedIn() {
        return Roles.isAuthenticated();
    }

}
