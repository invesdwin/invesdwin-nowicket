package de.invesdwin.nowicket.component.header.optimalselect;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

/**
 * https://github.com/autarc/optimal-select
 *
 */
@Immutable
public final class OptimalSelectJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final OptimalSelectJsReference INSTANCE = new OptimalSelectJsReference();

    private OptimalSelectJsReference() {
        super(OptimalSelectJsReference.class, "optimal-select.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem
                .forReference(ABaseWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}