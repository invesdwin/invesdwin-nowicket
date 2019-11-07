package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

@Immutable
public final class ModernizrMinJavaScriptReference extends WebjarsJavaScriptResourceReference {
    private static final long serialVersionUID = 1L;

    private ModernizrMinJavaScriptReference() {
        super("/modernizr/current/modernizr.min.js");
    }

    private static final class Holder {
        private static final ModernizrMinJavaScriptReference INSTANCE = new ModernizrMinJavaScriptReference();
    }

    public static ModernizrMinJavaScriptReference instance() {
        return Holder.INSTANCE;
    }

}