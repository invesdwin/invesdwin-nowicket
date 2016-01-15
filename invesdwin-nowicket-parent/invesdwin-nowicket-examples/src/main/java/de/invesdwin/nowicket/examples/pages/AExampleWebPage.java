package de.invesdwin.nowicket.examples.pages;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;

@NotThreadSafe
public abstract class AExampleWebPage extends AWebPage {

    public AExampleWebPage(final IModel<?> model) {
        super(model);
    }

    @Override
    protected Navbar newNavbar(final String id) {

        final Navbar newNavbar = super.newNavbar(id);
        newNavbar.setBrandName(Model.of("NoWicket Examples"));
        return newNavbar;
    }

}
