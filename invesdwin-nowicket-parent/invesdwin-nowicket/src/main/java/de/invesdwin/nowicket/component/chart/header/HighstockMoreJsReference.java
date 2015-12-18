package de.invesdwin.nowicket.component.chart.header;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;

import de.agilecoders.wicket.core.util.Dependencies;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

@NotThreadSafe
public final class HighstockMoreJsReference extends WebjarsJavaScriptResourceReference implements IHeaderContributor {

    public static final HighstockMoreJsReference INSTANCE = new HighstockMoreJsReference();

    private HighstockMoreJsReference() {
        super("/highstock/current/highcharts-more.js");
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
