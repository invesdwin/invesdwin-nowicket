package de.invesdwin.nowicket.examples.guide.page.wicket.authentication;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.nowicket.application.auth.Roles;

@Immutable
public final class ExampleRoles {

    private ExampleRoles() {}

    public static boolean isAdmin() {
        return Roles.evaluateExpression("hasRole('" + Roles.ADMIN + "')");
    }

}
