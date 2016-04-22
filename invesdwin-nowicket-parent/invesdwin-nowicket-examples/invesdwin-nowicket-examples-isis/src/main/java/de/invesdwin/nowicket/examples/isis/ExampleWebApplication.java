package de.invesdwin.nowicket.examples.isis;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.isis.core.commons.authentication.AnonymousSession;
import org.apache.isis.core.runtime.runner.IsisInjectModule;
import org.apache.isis.core.runtime.system.DeploymentType;
import org.apache.isis.core.runtime.system.context.IsisContext;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.filter.AWebApplication;

@ThreadSafe
public class ExampleWebApplication extends AWebApplication {

    public static final Set<String> BASE_PACKAGE = Collections
            .unmodifiableSet(new HashSet<String>(Arrays.asList("de.invesdwin.nowicket.examples.isis")));

    @Override
    protected void init() {
        super.init();
        final IsisInjectModule isisModule = new IsisInjectModule(DeploymentType.SERVER);
        final Injector injector = Guice.createInjector(new Module[] { isisModule });
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector, false));
        injector.injectMembers(this);
        getRequestCycleListeners().add(new AbstractRequestCycleListener() {
            @Override
            public void onBeginRequest(final RequestCycle cycle) {
                IsisContext.openSession(new AnonymousSession());
                IsisContext.getTransactionManager().startTransaction();
            }

            @Override
            public void onEndRequest(final RequestCycle cycle) {
                IsisContext.getTransactionManager().endTransaction();
                IsisContext.closeSession();
            }
        });

    }

    @Override
    protected IWebApplicationConfig newConfig() {
        return new ExampleWebApplicationConfig();
    }

    @Override
    public Set<String> getClasspathBasePackages() {
        return BASE_PACKAGE;
    }

}
