package de.invesdwin.nowicket.security.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.MetaDataKey;

import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.auth.ISavedRequest;
import de.invesdwin.util.lang.Reflections;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ShiroAuthenticationService implements IAuthenticationService {

    private static final MetaDataKey<ArrayList<String>> KEY_ROLES = new MetaDataKey<ArrayList<String>>() {
    };
    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory
            .getXLogger(ShiroAuthenticationService.class);

    @Override
    public boolean evaluateExpression(final String spel) {
        throw new UnsupportedOperationException(
                "Support for the most common expressions, e.g. hasRole is planned, but not yet implemented.");
    }

    @Override
    public boolean isRememberMe() {
        try {
            return getAuthentication().isRemembered();
        } catch (final UnavailableSecurityManagerException e) {
            return false;
        }
    }

    @Override
    public Subject getAuthentication() {
        return SecurityUtils.getSubject();
    }

    @Override
    public boolean isAnonymous() {
        return !isAuthenticated();
    }

    @Override
    public boolean isAuthenticated() {
        try {
            return getAuthentication().isAuthenticated();
        } catch (final UnavailableSecurityManagerException e) {
            return false;
        }
    }

    @Override
    public boolean isFullyAuthenticated() {
        return isAuthenticated() && !isRememberMe();
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
        try {
            getAuthentication().getSession().stop();
            AWebSession.get().replaceSession();
            getAuthentication().login(token);
            rememberRoles(token);
            return true;
        } catch (final AuthenticationException e) {
            LOG.catching(e);
            return false;
        }
    }

    private void rememberRoles(final AuthenticationToken token) {
        final ArrayList<String> roles = new ArrayList<String>();
        final Set<String> realmNames = getAuthentication().getPrincipals().getRealmNames();
        for (final Realm realm : getSecurityManager().getRealms()) {
            if (!realmNames.contains(realm.getName())) {
                continue;
            }
            final AuthenticationInfo authenticationInfo = realm.getAuthenticationInfo(token);
            if (authenticationInfo instanceof AuthorizationInfo) {
                final AuthorizationInfo authorizationInfo = (AuthorizationInfo) authenticationInfo;
                final Collection<String> realmRoles = authorizationInfo.getRoles();
                for (final String role : realmRoles) {
                    roles.add(role);
                }
            }
        }
        AWebSession.get().setMetaData(KEY_ROLES, roles);
    }

    @Override
    public List<String> getRoles() {
        final ArrayList<String> roles = AWebSession.get().getMetaData(KEY_ROLES);
        if (roles == null) {
            return Collections.emptyList();
        } else {
            return roles;
        }
    }

    @Override
    public void signOut() {
        getAuthentication().logout();
        AWebSession.get().setMetaData(KEY_ROLES, null);
    }

    @Override
    public ISavedRequest getSavedRequest() {
        return null;
    }

    @Override
    public boolean shouldShowAccessDeniedExceptionMessage(final Throwable t) {
        return t instanceof AuthorizationException;
    }

    @Override
    public void convertUsernamePasswordToRememberMeAuthentication() {
        Reflections.field("authenticated").ofType(boolean.class).in(getAuthentication()).set(false);
    }

    @Override
    public void setAuthentication(final Object authentication) {
        throw new UnsupportedOperationException();
    }

    public RealmSecurityManager getSecurityManager() {
        return (RealmSecurityManager) SecurityUtils.getSecurityManager();
    }

    @Override
    public String getUsername() {
        return Strings.asString(getAuthentication().getPrincipal());
    }

    /**
     * Return false, since shiro handles this: https://issues.apache.org/jira/browse/SHIRO-170
     */
    @Override
    public boolean shouldReplaceSessionAfterSignIn() {
        return false;
    }

    @Override
    public Object beforeReplaceSession() {
        return null;
    }

    @Override
    public void afterReplaceSession(final Object beforeReplaceSession) {}

}
