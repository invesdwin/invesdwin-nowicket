package de.invesdwin.nowicket.examples.isis.integration;

import javax.annotation.concurrent.Immutable;

import org.apache.isis.core.runtime.system.context.IsisContext;

@Immutable
public final class IsisInjector {

    private IsisInjector() {}

    public static void inject(final Object obj) {
        if (IsisContext.inSession()) {
            IsisContext.getPersistenceSession().getServicesInjector().injectServicesInto(obj);
        }

    }

}
