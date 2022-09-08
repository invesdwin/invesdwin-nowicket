package com.granatasoft.remotelist.website.pages;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;

import com.granatasoft.remotelist.website.pages.remotelist.ShowCategoriesPage;
import com.granatasoft.remotelist.website.pages.servers.ShowServersPage;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.component.navbar.Navbar;

@NotThreadSafe
public abstract class ARemotelistPage extends AWebPage {

    public ARemotelistPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected Navbar newNavbar(final String id) {
        final Navbar navbar = super.newNavbar(id);
        navbar.setBrandName(Model.of("RemoteList"));
        navbar.setBrandImage(new PackageResourceReference(ARemotelistPage.class, "logo.png"), null);

        if (Roles.get().hasRole(Roles.USER)) {
            navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                    new NavbarButton<Void>(ShowCategoriesPage.class, Model.of("Categories "))));
            navbar.addComponents(NavbarComponents.transform(ComponentPosition.LEFT,
                    new NavbarButton<Void>(ShowServersPage.class, Model.of("Servers"))));
        }
        return navbar;
    }
}
