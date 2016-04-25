package de.invesdwin.nowicket.examples.isis.integration;

import javax.annotation.concurrent.Immutable;

import org.apache.isis.core.commons.authentication.AnonymousSession;
import org.apache.isis.core.commons.authentication.AuthenticationSession;
import org.apache.isis.core.runtime.authentication.standard.SimpleSession;
import org.apache.isis.core.runtime.system.context.IsisContext;

import de.invesdwin.nowicket.application.auth.Roles;

@Immutable
public final class IsisInjector {

    private IsisInjector() {}

    public static void inject(final Object obj) {
        final boolean needsSession = !IsisContext.inSession();
        if (needsSession) {
            openSession();
        }
        IsisContext.getPersistenceSession().getServicesInjector().injectServicesInto(obj);
        if (needsSession) {
            closeSession();
        }
    }

    public static void openSession() {
        final AuthenticationSession authenticationSession;
        if (Roles.getAuthenticationService().isAuthenticated()) {
            authenticationSession = new SimpleSession(Roles.getAuthenticationService().getUsername(),
                    Roles.getAuthenticationService().getRoles());
        } else {
            authenticationSession = new AnonymousSession();
        }
        IsisContext.openSession(authenticationSession);
        if (IsisContext.inSession()) {
            IsisContext.getTransactionManager().startTransaction();
        }
    }

    public static void closeSession() {
        if (IsisContext.inSession()) {
            IsisContext.getTransactionManager().endTransaction();
            IsisContext.closeSession();
        }
    }

}
