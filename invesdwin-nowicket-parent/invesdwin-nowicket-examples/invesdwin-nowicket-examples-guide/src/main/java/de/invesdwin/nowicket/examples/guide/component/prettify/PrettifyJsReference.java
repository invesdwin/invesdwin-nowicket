package de.invesdwin.nowicket.examples.guide.component.prettify;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

@Immutable
public final class PrettifyJsReference extends WebjarsJavaScriptResourceReference implements IHeaderContributor {

    public static final PrettifyJsReference INSTANCE = new PrettifyJsReference();
    private static final String FUNCTION_NAME = "prettyPrint";

    private PrettifyJsReference() {
        super("/prettify/current/prettify.js");
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
        response.render(OnDomReadyHeaderItem
                .forScript("Wicket.Event.subscribe('/ajax/call/success', function(attributes, jqXHR, settings) { "
                        + FUNCTION_NAME + "(); });"));
    }

}
