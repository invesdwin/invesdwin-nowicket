package de.invesdwin.nowicket.examples.isis.page.users;

import javax.annotation.concurrent.NotThreadSafe;

import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import de.invesdwin.norva.beanpath.annotation.ColumnOrder;
import de.invesdwin.norva.beanpath.annotation.ModalOpener;
import de.invesdwin.nowicket.examples.isis.page.users.changepassword.ChangePassword;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Strings;

@ColumnOrder({ UserRowConstants.username, UserRowConstants.email, UserRowConstants.roles,
        UserRowConstants.changePassword })
@NotThreadSafe
public class UserRow extends AValueObject {

    private final String username;
    private final String email;
    private final String roles;

    public UserRow(final ApplicationUser user) {
        this.username = user.getUsername();
        this.email = user.getEmailAddress();

        StringBuilder sb = new StringBuilder();
        for (final ApplicationRole role : user.getRoles()) {
            sb.append(role.getName());
            sb.append(", ");
        }
        sb = Strings.removeEnd(sb, ", ");
        roles = sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRoles() {
        return roles;
    }

    @ModalOpener
    public ChangePassword changePassword() {
        return new ChangePassword(username);
    }

}
