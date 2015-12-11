package de.invesdwin.nowicket.component.header.offline;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.invesdwin.nowicket.component.header.offline.release.OfflineJsReference;
import de.invesdwin.nowicket.component.header.offline.release.OfflineLanguageEnglishCssReference;
import de.invesdwin.nowicket.component.header.offline.release.OfflineThemeChromeCssReference;

@Immutable
public final class OfflineHeaderContributor implements IHeaderContributor {

    public static final OfflineHeaderContributor INSTANCE = new OfflineHeaderContributor();

    private OfflineHeaderContributor() {}

    @Override
    public void renderHead(final IHeaderResponse response) {
        OfflineThemeChromeCssReference.INSTANCE.renderHead(response);
        OfflineLanguageEnglishCssReference.INSTANCE.renderHead(response);
        OfflineJsReference.INSTANCE.renderHead(response);
        OfflineReloadPageOnUpJsReference.INSTANCE.renderHead(response);
    }

}
