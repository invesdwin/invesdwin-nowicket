package de.invesdwin.nowicket.examples.isis.integration;

import java.util.Collections;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.userreg.SecurityModuleAppUserRegistrationServiceAbstract;

@NotThreadSafe
@DomainService
public class AppUserRegistrationService extends SecurityModuleAppUserRegistrationServiceAbstract {

    private static final String ROLE_NAME = "example-fixture-scripts";

    @Inject
    private ApplicationRoleRepository applicationRoleRepository;

    @Override
    protected ApplicationRole getInitialRole() {
        return findRole(ROLE_NAME);
    }

    @Override
    protected Set<ApplicationRole> getAdditionalInitialRoles() {
        return Collections.singleton(findRole(ROLE_NAME));
    }

    private ApplicationRole findRole(final String roleName) {
        return applicationRoleRepository.findByName(roleName);
    }

}