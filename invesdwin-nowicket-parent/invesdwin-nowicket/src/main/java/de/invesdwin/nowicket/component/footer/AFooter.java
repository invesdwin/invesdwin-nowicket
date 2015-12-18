package de.invesdwin.nowicket.component.footer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.invesdwin.nowicket.application.AWebPage;

/**
 * Implement AFooter like you would implement a Panel.
 */
@NotThreadSafe
public abstract class AFooter extends Panel implements Invertible<AFooter> {

    private boolean inverted;
    private final IModel<String> containerClassModel = Model.of(AWebPage.DEFAULT_CONTAINER_CLASS);

    public AFooter(final String id) {
        this(id, null);
    }

    public AFooter(final String id, final IModel<?> model) {
        super(id, model);
        add(newFooterNavbar("footerNavbar"));
        add(newContainer("footerContainer"));
    }

    private TransparentWebMarkupContainer newFooterNavbar(final String id) {
        final TransparentWebMarkupContainer footerNavbar = new TransparentWebMarkupContainer(id) {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                if (inverted) {
                    Attributes.addClass(tag, "navbar-inverse");
                }
            }
        };
        return footerNavbar;
    }

    private TransparentWebMarkupContainer newContainer(final String id) {
        final TransparentWebMarkupContainer container = new TransparentWebMarkupContainer(id);
        container.add(AttributeModifier.replace("class", containerClassModel));
        return container;
    }

    public final String getContainerClass() {
        return containerClassModel.getObject();
    }

    public final void setContainerClass(final String containerClass) {
        this.containerClassModel.setObject(containerClass);
    }

    @Override
    public AFooter setInverted(final boolean inverted) {
        this.inverted = inverted;
        return this;
    }

    public boolean isInverted() {
        return inverted;
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        Components.assertTag(this, tag, "footer");
        Attributes.addClass(tag, "footer");
    }

}
