package de.invesdwin.nowicket.examples.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;

public abstract class AExampleWebPage extends AWebPage {

	public AExampleWebPage(IModel<?> model) {
		super(model);
	}

	@Override
	protected Navbar newNavbar(String id) {

		Navbar newNavbar = super.newNavbar(id);
		newNavbar.setBrandName(Model.of("NoWicket Examples"));
		return newNavbar;
	}

}
