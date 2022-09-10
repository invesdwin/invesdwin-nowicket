package de.invesdwin.nowicket.component.palette.theme;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

@NotThreadSafe
public class BootstrapPaletteTheme extends Behavior {

    private static final long serialVersionUID = 1L;

    /** reference to the palette's css resource */
    private static final ResourceReference CSS = new CssResourceReference(BootstrapPaletteTheme.class,
            BootstrapPaletteTheme.class.getSimpleName() + ".css");

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        tag.append("class", "palette-theme-bootstrap", " ");
    }

    /**
     * Renders header contributions
     * 
     * @param component
     * @param response
     */
    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(CSS));
    }
}
