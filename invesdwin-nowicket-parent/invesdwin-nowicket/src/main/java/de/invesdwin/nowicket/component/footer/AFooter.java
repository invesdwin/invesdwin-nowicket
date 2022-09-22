package de.invesdwin.nowicket.component.footer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.invesdwin.nowicket.application.AWebPage;

/**
 * Implement AFooter like you would implement a Panel.
 */
@NotThreadSafe
public abstract class AFooter extends Panel {

    private final IModel<String> containerClassModel = Model.of(AWebPage.DEFAULT_CONTAINER_CLASS);
    private final IModel<BackgroundColorBehavior.Color> backgroundColor = Model.of(BackgroundColorBehavior.Color.Light);

    public AFooter(final String id) {
        this(id, null);
    }

    public AFooter(final String id, final IModel<?> model) {
        super(id, model);
        add(newContainer("footerContainer"));
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
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        Components.assertTag(this, tag, "footer");
        Attributes.addClass(tag, "footer " + backgroundColor.getObject().cssClassName());
    }

    public AFooter setInverted(final boolean invert) {
        return setBackgroundColor(invert ? BackgroundColorBehavior.Color.Dark : BackgroundColorBehavior.Color.Light);
    }

    public AFooter setBackgroundColor(final BackgroundColorBehavior.Color color) {
        this.backgroundColor.setObject(color);
        return this;
    }

}
