package de.invesdwin.nowicket.examples.guide.component.cornify;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.CssResourceReference;

@Immutable
public final class CornifyCssReference extends CssResourceReference implements IHeaderContributor {

    public static final CornifyCssReference INSTANCE = new CornifyCssReference();

    private CornifyCssReference() {
        super(CornifyCssReference.class, "cornify.css");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(this));
    }

}
