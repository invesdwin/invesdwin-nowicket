package de.invesdwin.nowicket.component.navbarold;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
import de.agilecoders.wicket.core.util.ListItemCssClassHelper;

/**
 * A simple header for {@link DropDownButton} and {@link SplitButton} submenus.
 *
 * @author miha
 */
public class MenuHeader extends AbstractLink {

    /**
     * Construct.
     *
     * @param label
     *            Header label
     */
    public MenuHeader(final IModel<String> label) {
        super(ButtonList.getButtonMarkupId());

        setRenderBodyOnly(true);
        setBody(label);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        ListItemCssClassHelper.onInitialize(this, "dropdown-header");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        ListItemCssClassHelper.onConfigure(this, "dropdown-header");
    }
}
