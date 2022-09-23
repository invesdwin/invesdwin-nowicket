package de.invesdwin.nowicket.component.header;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

/**
 * Add this behavior to a page to enable fancy bootstrap javascript tooltips via the following html markup:
 * 
 * <br>
 * <br>
 * &lt;tag data-bs-toggle="tooltip" title="tooltip message" /&gt;
 *
 */
@NotThreadSafe
public final class EnableBootstrapTooltipsHeaderContributor extends JavaScriptResourceReference
        implements IHeaderContributor {

    public static final EnableBootstrapTooltipsHeaderContributor INSTANCE = new EnableBootstrapTooltipsHeaderContributor();
    private static final String FUNCTION_NAME = "enableBootstrapTooltips";

    private EnableBootstrapTooltipsHeaderContributor() {
        super(EnableBootstrapTooltipsHeaderContributor.class, FUNCTION_NAME + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem
                .forReference(ABaseWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnLoadHeaderItem.forScript(FUNCTION_NAME + "();"));
    }
}