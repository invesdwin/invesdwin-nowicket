package de.invesdwin.nowicket.examples.isis.page.users;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
@AuthorizeInstantiation("isis-module-security-admin")
@MountPath("users")
public class UsersPage extends AWebPage {

    public UsersPage() {
        this(Model.of(new Users()));
    }

    public UsersPage(final IModel<Users> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

    @Override
    protected Navbar newNavbar(final String id) {
        final Navbar newNavbar = super.newNavbar(id);
        newNavbar.setBrandName(Model.of("NoWicket Isis Integration Example"));
        return newNavbar;
    }

}
