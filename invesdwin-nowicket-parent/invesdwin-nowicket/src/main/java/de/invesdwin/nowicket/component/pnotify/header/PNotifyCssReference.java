package de.invesdwin.nowicket.component.pnotify.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@Immutable
public final class PNotifyCssReference extends CssResourceReference implements IHeaderContributor {

    public static final PNotifyCssReference INSTANCE = new PNotifyCssReference();

    private PNotifyCssReference() {
        super(PNotifyCssReference.class, "pnotify.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }
}
