package de.invesdwin.nowicket.examples.guide.component.cornify;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.invesdwin.nowicket.application.filter.AWebApplication;

@Immutable
public final class CornifyHeaderContributor implements IHeaderContributor {

    public static final CornifyHeaderContributor INSTANCE = new CornifyHeaderContributor();

    private CornifyHeaderContributor() {
        AWebApplication.get().mountResource("/cornify.css", CornifyCssReference.INSTANCE);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        CornifyJsReference.INSTANCE.renderHead(response);
    }

}
