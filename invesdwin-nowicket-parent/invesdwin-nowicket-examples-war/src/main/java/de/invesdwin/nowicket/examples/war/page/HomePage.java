package de.invesdwin.nowicket.examples.war.page;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.invesdwin.nowicket.application.AWebPage;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

public class HomePage extends AWebPage {

	public HomePage() {
		this(Model.of(new Home()));
	}

	public HomePage(IModel<Home> model) {
		super(model);
		new GeneratedBinding(this).bind();
	}

	@Override
	protected Navbar newNavbar(String id) {
		Navbar newNavbar = super.newNavbar(id);
		newNavbar.setBrandName(Model.of("NoWicket War Example"));
		return newNavbar;
	}

}
