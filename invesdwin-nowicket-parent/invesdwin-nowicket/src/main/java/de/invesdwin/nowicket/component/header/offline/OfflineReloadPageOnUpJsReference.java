package de.invesdwin.nowicket.component.header.offline;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

@Immutable
public final class OfflineReloadPageOnUpJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final OfflineReloadPageOnUpJsReference INSTANCE = new OfflineReloadPageOnUpJsReference();
    private static final String FUNCTION_NAME = "offlineReloadPageOnUp";

    private OfflineReloadPageOnUpJsReference() {
        super(OfflineReloadPageOnUpJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem
                .forReference(ABaseWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnDomReadyHeaderItem.forScript(FUNCTION_NAME + "();"));
    }
}