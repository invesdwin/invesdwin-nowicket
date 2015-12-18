package de.invesdwin.nowicket.application.filter.internal;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.request.Request;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.savedrequest.SavedRequest;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.util.SpringSecuritySessionAttributes;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class DelegateWebSession extends AWebSession {

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(DelegateWebSession.class);

    public DelegateWebSession(final Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        final AuthenticationManager authenticationManager = ABaseWebApplication.get()
                .getDelegate()
                .getAuthenticationManager();
        if (authenticationManager == null) {
            return true;
        }
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
    public Roles getRoles() {
        final Roles roles = new Roles();
        final AuthenticationManager authenticationManager = ABaseWebApplication.get()
                .getDelegate()
                .getAuthenticationManager();
        if (authenticationManager != null && isSignedIn()) {
            final Authentication authentication = SpringSecuritySessionAttributes.getAuthentication();
            for (final GrantedAuthority authority : authentication.getAuthorities()) {
                final String role = authority.getAuthority();
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
        final SavedRequest savedRequest = SpringSecuritySessionAttributes.getSavedRequest();
        super.replaceSession();
        SpringSecuritySessionAttributes.setSavedRequest(savedRequest);
        SpringSecuritySessionAttributes.updateSpringSecurityContext();
    }

    @Override
    protected void signOut() {
        final HttpServletRequest request = SpringSecuritySessionAttributes.getContainerRequest();
        try {
            request.logout();
            //            Assertions.assertThat(isSignedIn()).isFalse();
            //            Assertions.assertThat(Roles.isAnonymous()).isTrue();
        } catch (final ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isSignedIn() {
        return Roles.isAuthenticated();
    }

}
