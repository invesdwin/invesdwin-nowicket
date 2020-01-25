package de.invesdwin.nowicket.examples.isis.integration;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;
import org.isisaddons.module.security.userreg.SecurityModuleAppUserRegistrationServiceAbstract;

@NotThreadSafe
@DomainService
public class AppUserRegistrationService extends SecurityModuleAppUserRegistrationServiceAbstract {

    private static final String ROLE_NAME = "isis-module-security-admin";

    @Inject
    private RepositoryService repositoryService;
    @Inject
    private ApplicationRoleRepository applicationRoleRepository;
    @Inject
    private ApplicationUserRepository applicationUserRepository;

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

    public List<ApplicationUser> findAllUsers() {
        return applicationUserRepository.allUsers();
    }

    public ApplicationUser findByUsername(final String username) {
        return applicationUserRepository.findByUsername(username);
    }

    public void saveUser(final ApplicationUser user) {
        repositoryService.persist(user);
    }

}