package de.invesdwin.nowicket.examples.isis.page.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.isisaddons.module.security.dom.user.ApplicationUser;

import de.invesdwin.norva.beanpath.annotation.ModalOpener;
import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.examples.isis.integration.IsisInjector;
import de.invesdwin.nowicket.examples.isis.page.users.createuser.CreateUser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class Users extends AValueObject {

    @javax.inject.Inject
    private transient AppUserRegistrationService appUserRegistrationService;

    public Users() {
        IsisInjector.inject(this);
    }

    public List<UserRow> getUsers() {
        final List<ApplicationUser> users = appUserRegistrationService.findAllUsers();
        final List<UserRow> rows = new ArrayList<UserRow>();
        for (final ApplicationUser user : users) {
            rows.add(new UserRow(user));
        }
        return rows;
    }

    @ModalOpener
    public CreateUser createUser() {
        return new CreateUser();
    }

    private void readObject(final java.io.ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        IsisInjector.inject(this);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Users;
    }

}
