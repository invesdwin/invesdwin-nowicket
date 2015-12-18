package de.invesdwin.nowicket.component.modal.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@Immutable
public final class JQueryDisablescrollJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final JQueryDisablescrollJsReference INSTANCE = new JQueryDisablescrollJsReference();

    private JQueryDisablescrollJsReference() {
        super(JQueryDisablescrollJsReference.class, "jquery.disablescroll.js");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}