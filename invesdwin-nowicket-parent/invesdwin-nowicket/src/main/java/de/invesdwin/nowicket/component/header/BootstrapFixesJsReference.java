package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@Immutable
public final class BootstrapFixesJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final BootstrapFixesJsReference INSTANCE = new BootstrapFixesJsReference();

    private BootstrapFixesJsReference() {
        super(BootstrapFixesJsReference.class, "bootstrapFixes.js");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }

}