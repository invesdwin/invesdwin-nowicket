package de.invesdwin.nowicket.application;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;

@NotThreadSafe
public abstract class AWebPageNavbar extends Navbar {

    public AWebPageNavbar(final String id) {
        super(id);
    }

    @Override
    protected TransparentWebMarkupContainer newContainer(final String componentId) {
        final TransparentWebMarkupContainer container = super.newCollapseContainer(componentId);
        container.add(AttributeModifier.replace("class", getWebPageParent().getContainerClass()));
        return container;
    }

    protected abstract AWebPage getWebPageParent();

    @Override
    protected Class<? extends Page> getHomePage() {
        return getWebPageParent().getNavbarHomePage();
    }

    @Override
    protected Label newBrandLabel(final String markupId) {
        final Label brandLabel = super.newBrandLabel(markupId);
        brandLabel.setEscapeModelStrings(false);
        return brandLabel;
    }

    @Override
    public Navbar setInverted(final boolean invert) {
        if (invert) {
            setBackgroundColor(BackgroundColorBehavior.Color.Dark);
        } else {
            setBackgroundColor(BackgroundColorBehavior.Color.Light);
        }
        return super.setInverted(invert);
    }

}
