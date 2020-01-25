package de.invesdwin.nowicket.examples.guide.component.prettify;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

@Immutable
public final class PrettifyCssReference extends WebjarsCssResourceReference implements IHeaderContributor {

    public static final PrettifyCssReference INSTANCE = new PrettifyCssReference();

    private PrettifyCssReference() {
        super("/prettify/current/prettify.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}
