package de.invesdwin.nowicket.examples.guide.internal;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.expression.SecurityExpressionHandler;

import de.invesdwin.util.lang.reflection.Reflections;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@NotThreadSafe
@Named
public class DefaultRolePrefixRemover implements InitializingBean {

    public static final String DISABLED_ROLE_PREFIX = "";

    @Inject
    private SecurityExpressionHandler<?>[] securityExpressionHandlers;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (final SecurityExpressionHandler<?> handler : securityExpressionHandlers) {
            Reflections.method("setDefaultRolePrefix")
                    .withParameterTypes(String.class)
                    .in(handler)
                    .invoke(DISABLED_ROLE_PREFIX);
        }
    }

}
