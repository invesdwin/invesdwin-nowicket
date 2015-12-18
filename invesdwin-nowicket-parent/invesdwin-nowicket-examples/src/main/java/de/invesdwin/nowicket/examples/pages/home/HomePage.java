package de.invesdwin.nowicket.examples.pages.home;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;

public class HomePage extends AWebPage {

	public HomePage() {
		super(null);
	}

	public HomePage(IModel<?> model) {
		super(model);
	}

	@Override
	protected Navbar newNavbar(String id) {

		Navbar newNavbar = super.newNavbar(id);
		newNavbar.setBrandName(Model.of("NoWicket Examples"));
		return newNavbar;
	}

}
