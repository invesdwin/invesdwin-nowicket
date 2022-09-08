package de.invesdwin.nowicket.component.toastr.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@Immutable
public final class ToastrCssReference extends CssResourceReference implements IHeaderContributor {

    public static final ToastrCssReference INSTANCE = new ToastrCssReference();

    private ToastrCssReference() {
        super(ToastrCssReference.class, "toastr.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}