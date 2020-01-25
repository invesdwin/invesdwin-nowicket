package de.invesdwin.nowicket.examples.guide.page;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;

@NotThreadSafe
public class IconMenuBookmarkablePageLink<T> extends MenuBookmarkablePageLink<T> {

    public <P extends Page> IconMenuBookmarkablePageLink(final Class<P> pageClass, final IModel<String> label,
            final ResourceReference icon) {
        super(pageClass, new IModel<String>() {
            @Override
            public String getObject() {
                final String withIcon = "<img style=\"margin-top: -3px\" src=\"" + RequestCycle.get().urlFor(icon, null)
                        + "\" />&nbsp;" + label.getObject();
                return withIcon;
            }
        });
    }

    @Override
    protected Component newLabel(final String markupId) {
        final Label label = (Label) super.newLabel(markupId);
        label.setEscapeModelStrings(false);
        return label;
    }

}
