package de.invesdwin.nowicket.component.header.offline.release;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@Immutable
public final class OfflineThemeChromeCssReference extends CssResourceReference implements IHeaderContributor {

    public static final OfflineThemeChromeCssReference INSTANCE = new OfflineThemeChromeCssReference();

    private OfflineThemeChromeCssReference() {
        super(OfflineThemeChromeCssReference.class, "offline-theme-chrome.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}
