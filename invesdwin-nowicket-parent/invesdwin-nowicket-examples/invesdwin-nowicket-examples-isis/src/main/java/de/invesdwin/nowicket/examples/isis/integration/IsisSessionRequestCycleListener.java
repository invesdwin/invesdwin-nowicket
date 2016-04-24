package de.invesdwin.nowicket.examples.isis.integration;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.isis.core.commons.authentication.AnonymousSession;
import org.apache.isis.core.commons.authentication.AuthenticationSession;
import org.apache.isis.core.runtime.authentication.standard.SimpleSession;
import org.apache.isis.core.runtime.system.context.IsisContext;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.nowicket.application.auth.Roles;

@NotThreadSafe
public class IsisSessionRequestCycleListener extends AbstractRequestCycleListener {

    @Override
    public void onBeginRequest(final RequestCycle cycle) {
        final AuthenticationSession authenticationSession;
        if (Roles.getAuthenticationService().isAuthenticated()) {
            authenticationSession = new SimpleSession(Roles.getAuthenticationService().getUsername(),
                    Roles.getAuthenticationService().getRoles());
        } else {
            authenticationSession = new AnonymousSession();
        }
        IsisContext.openSession(authenticationSession);
        IsisContext.getTransactionManager().startTransaction();
    }

    @Override
    public void onEndRequest(final RequestCycle cycle) {
        IsisContext.getTransactionManager().endTransaction();
        IsisContext.closeSession();
    }

}
