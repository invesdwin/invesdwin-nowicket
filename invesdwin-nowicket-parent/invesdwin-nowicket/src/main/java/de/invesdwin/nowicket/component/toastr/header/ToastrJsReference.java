package de.invesdwin.nowicket.component.toastr.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@Immutable
public final class ToastrJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final ToastrJsReference INSTANCE = new ToastrJsReference();

    private ToastrJsReference() {
        super(ToastrJsReference.class, "toastr.js");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}