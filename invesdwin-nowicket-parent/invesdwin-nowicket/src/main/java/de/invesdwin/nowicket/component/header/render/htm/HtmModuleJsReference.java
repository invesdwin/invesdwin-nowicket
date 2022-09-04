package de.invesdwin.nowicket.component.header.render.htm;

import javax.annotation.concurrent.Immutable;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

@Immutable
public final class HtmModuleJsReference extends WebjarsJavaScriptResourceReference {

    public static final HtmModuleJsReference INSTANCE = new HtmModuleJsReference();

    private HtmModuleJsReference() {
        super("/htm/current/dist/htm.module.js");
    }

}