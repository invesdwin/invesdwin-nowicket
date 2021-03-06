package com.eva.web.css;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@Immutable
public final class CeruleanExtentionsCssReference extends CssResourceReference implements IHeaderContributor {

    public static final CeruleanExtentionsCssReference INSTANCE = new CeruleanExtentionsCssReference();

    private CeruleanExtentionsCssReference() {
        super(CeruleanExtentionsCssReference.class, "ceruleanExtensions.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}