package de.invesdwin.nowicket.application;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;

@NotThreadSafe
public abstract class AWebPageNavbar extends Navbar {

    public AWebPageNavbar(final String componentId) {
        super(componentId);
    }

    @Override
    protected TransparentWebMarkupContainer newContainer(final String componentId) {
        final TransparentWebMarkupContainer container = super.newCollapseContainer(componentId);
        container.add(AttributeModifier.replace("class", getWebPageParent().getContainerClass()));
        return container;
    }

    protected abstract AWebPage getWebPageParent();

    @Override
    protected Image newBrandImage(final String markupId) {
        return super.newBrandImage(markupId);
    }

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

}
