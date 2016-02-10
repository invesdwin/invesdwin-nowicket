package de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.secure;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.security.access.prepost.PreAuthorize;

import de.invesdwin.nowicket.examples.guide.pages.home.Home;
import de.invesdwin.nowicket.examples.guide.pages.wicket.authentication.ExampleRoles;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class SpringSecure extends AValueObject {

    public boolean isAdmin() {
        return ExampleRoles.isAdmin();
    }

    public Home home() throws Exception {
        return new Home();
    }

    /**
     * results in SPEL exception, since a SPEL-Expression is needed by PreAuthorize
     */
    //    @PreAuthorize("UNKNOWN")
    //    public void preAuthorizeSecurityFailure() {
    //        throw new UnsupportedOperationException("ERROR: An error about permission denied should have been thrown!");
    //    }

    /**
     * @Secured is disabled in favour of @PreAuthroize
     */
    //    @Secured("UNKNOWN")
    //    public void securedSecurityFailure() {
    //        throw new UnsupportedOperationException("ERROR: An error about permission denied should have been thrown!");
    //    }

    /**
     * JSR250 annotations not supported yet in spring-security-aspects
     */
    //    @RolesAllowed("UNKNOWN")
    //    public void rolesAllowedSecurityFailure() {
    //        throw new UnsupportedOperationException("ERROR: An error about permission denied should have been thrown!");
    //    }

    @PreAuthorize("hasRole('UNKNOWN')")
    public void preAuthorizeSecurityFailureSPEL() {
        throw new UnsupportedOperationException("ERROR: An error about permission denied should have been thrown!");
    }

    /**
     * @Secured is disabled in favour of @PreAuthroize
     */
    //    @Secured("hasRole('UNKNOWN')")
    //    public void securedSecurityFailureSPEL() {
    //        throw new UnsupportedOperationException("ERROR: An error about permission denied should have been thrown!");
    //    }

    /**
     * JSR250 annotations not supported yet in spring-security-aspects
     */
    //    @RolesAllowed("hasRole('UNKNOWN')")
    //    public void rolesAllowedSecurityFailureSPEL() {
    //        throw new UnsupportedOperationException("ERROR: An error about permission denied should have been thrown!");
    //    }

}
