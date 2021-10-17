package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

/**
 * reference for font awesome css
 */
@Immutable
public final class FontAwesome4CssReference extends WebjarsCssResourceReference {
    private static final long serialVersionUID = 1L;

    /**
     * Private constructor.
     */
    private FontAwesome4CssReference() {
        super("fontawesome/current/css/font-awesome.css");
    }

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final FontAwesome4CssReference INSTANCE = new FontAwesome4CssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static FontAwesome4CssReference instance() {
        return Holder.INSTANCE;
    }

}
