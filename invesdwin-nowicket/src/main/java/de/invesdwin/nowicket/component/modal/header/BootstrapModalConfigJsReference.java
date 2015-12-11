package de.invesdwin.nowicket.component.modal.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@Immutable
public final class BootstrapModalConfigJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final BootstrapModalConfigJsReference INSTANCE = new BootstrapModalConfigJsReference();

    private BootstrapModalConfigJsReference() {
        super(BootstrapModalConfigJsReference.class, "bootstrapModalConfig.js");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnDomReadyHeaderItem.forScript("updateBodyClassesForBootstrapModal();"));
    }
}