package de.invesdwin.nowicket.component.navbar;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

// CHECKSTYLE:OFF
@NotThreadSafe
public abstract class NavbarDropDownButton
        extends de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton {
    //CHECKSTYLE:ON

    public NavbarDropDownButton(final IModel<String> model) {
        super(model);
    }

    public NavbarDropDownButton(final IModel<String> model, final IModel<IconType> iconTypeModel) {
        super(model, iconTypeModel);
    }

    @Override
    protected ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId, newSubMenuButtons(ButtonList.getButtonMarkupId())) {
            @Override
            protected void configureLink(final AbstractLink link) {
                super.configureLink(link);

                if (!(link instanceof MenuDivider) && !(link instanceof MenuHeader)) {
                    link.add(new CssClassNameAppender("dropdown-item"));
                }
            }
        };
        buttonList.setRenderBodyOnly(true);
        buttonList.setOutputMarkupId(false);

        return buttonList;
    }

}
