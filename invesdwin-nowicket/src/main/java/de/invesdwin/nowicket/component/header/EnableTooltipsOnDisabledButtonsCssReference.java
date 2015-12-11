package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@Immutable
public final class EnableTooltipsOnDisabledButtonsCssReference extends CssResourceReference implements
        IHeaderContributor {

    public static final EnableTooltipsOnDisabledButtonsCssReference INSTANCE = new EnableTooltipsOnDisabledButtonsCssReference();

    private EnableTooltipsOnDisabledButtonsCssReference() {
        super(EnableTooltipsOnDisabledButtonsCssReference.class, "enableTooltipsOnDisabledButtons.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}