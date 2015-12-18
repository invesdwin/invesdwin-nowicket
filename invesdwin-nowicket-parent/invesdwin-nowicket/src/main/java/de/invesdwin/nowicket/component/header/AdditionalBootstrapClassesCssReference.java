package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@NotThreadSafe
public final class AdditionalBootstrapClassesCssReference extends CssResourceReference implements IHeaderContributor {

    public static final AdditionalBootstrapClassesCssReference INSTANCE = new AdditionalBootstrapClassesCssReference();

    private AdditionalBootstrapClassesCssReference() {
        super(AdditionalBootstrapClassesCssReference.class, "additionalBootstrapClasses.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }
}
