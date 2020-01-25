package de.invesdwin.nowicket.examples.guide.component.prettify;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

@Immutable
public final class PrettifyHeaderContributor implements IHeaderContributor {

    public static final PrettifyHeaderContributor INSTANCE = new PrettifyHeaderContributor();

    private PrettifyHeaderContributor() {}

    @Override
    public void renderHead(final IHeaderResponse response) {
        PrettifyJsReference.INSTANCE.renderHead(response);
        PrettifyCssReference.INSTANCE.renderHead(response);
    }

}
