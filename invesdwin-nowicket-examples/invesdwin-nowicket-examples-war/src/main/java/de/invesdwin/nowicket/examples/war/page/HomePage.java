package de.invesdwin.nowicket.examples.war.page;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class HomePage extends AWebPage {

    public HomePage() {
        this(Model.of(new Home()));
    }

    public HomePage(final IModel<Home> model) {
        super(model);
        new GeneratedBinding(this).bind();
    }

    @Override
    protected Navbar newNavbar(final String id) {
        final Navbar newNavbar = super.newNavbar(id);
        newNavbar.setBrandName(Model.of("NoWicket War Example"));
        return newNavbar;
    }

}
