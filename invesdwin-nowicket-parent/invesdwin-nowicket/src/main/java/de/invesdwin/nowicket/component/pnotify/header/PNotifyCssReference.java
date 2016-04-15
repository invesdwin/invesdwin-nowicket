package de.invesdwin.nowicket.component.pnotify.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

@Immutable
public final class PNotifyCssReference extends WebjarsCssResourceReference implements IHeaderContributor {

    public static final PNotifyCssReference INSTANCE = new PNotifyCssReference();

    private PNotifyCssReference() {
        super("/pnotify/current/pnotify.core.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}
