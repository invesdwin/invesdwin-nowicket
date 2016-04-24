package de.invesdwin.nowicket.application.filter.internal;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.Request;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.auth.ISavedRequest;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class DelegateWebSession extends AWebSession {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(DelegateWebSession.class);

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
            for (final String role : Roles.getAuthenticationService().getRoles()) {
                roles.add(role);
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
        final ISavedRequest savedRequest;
        if (authenticationService != null) {
            savedRequest = authenticationService.getSavedRequest();
        } else {
            savedRequest = null;
        }
        super.replaceSession();
        if (authenticationService != null) {
            authenticationService.setSavedRequest(savedRequest);
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
