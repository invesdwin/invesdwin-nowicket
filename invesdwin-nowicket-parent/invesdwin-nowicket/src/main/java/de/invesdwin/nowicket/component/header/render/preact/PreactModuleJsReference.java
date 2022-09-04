package de.invesdwin.nowicket.component.header.render.preact;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

@Immutable
public final class PreactModuleJsReference extends WebjarsJavaScriptResourceReference {

    public static final PreactModuleJsReference INSTANCE = new PreactModuleJsReference();

    private PreactModuleJsReference() {
        super("/preact/current/dist/preact.module.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem
                .forReference(ABaseWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

}