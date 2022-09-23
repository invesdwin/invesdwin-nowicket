package de.invesdwin.nowicket.component.header.render;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.settings.JavaScriptLibrarySettings;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.filter.AWebApplication;

@Immutable
public final class PreventDuplicateAjaxCallbacksJsReference extends JavaScriptResourceReference
        implements IHeaderContributor {

    public static final PreventDuplicateAjaxCallbacksJsReference INSTANCE = new PreventDuplicateAjaxCallbacksJsReference();
    private static final String FUNCTION_NAME = "preventDuplicateAjaxCallbacks";

    private PreventDuplicateAjaxCallbacksJsReference() {
        super(PreventDuplicateAjaxCallbacksJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        final JavaScriptLibrarySettings ajaxSettings = AWebApplication.get().getJavaScriptLibrarySettings();
        final ResourceReference wicketAjaxReference = ajaxSettings.getWicketAjaxReference();
        final ResourceReference jQueryReference = ajaxSettings.getJQueryReference();
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem.forReference(jQueryReference),
                JavaScriptHeaderItem.forReference(wicketAjaxReference));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}