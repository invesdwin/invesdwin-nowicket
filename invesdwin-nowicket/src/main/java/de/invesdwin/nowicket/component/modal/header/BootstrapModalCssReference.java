package de.invesdwin.nowicket.component.modal.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;

@Immutable
public final class BootstrapModalCssReference extends WebjarsCssResourceReference implements IHeaderContributor {

    public static final BootstrapModalCssReference INSTANCE = new BootstrapModalCssReference();

    private BootstrapModalCssReference() {
        super("/bootstrap-modal/current/css/bootstrap-modal.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}
