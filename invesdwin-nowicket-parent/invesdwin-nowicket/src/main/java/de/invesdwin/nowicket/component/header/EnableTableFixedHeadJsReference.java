package de.invesdwin.nowicket.component.header;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.component.header.floatthead.FloatTheadJsReference;

@Immutable
public final class EnableTableFixedHeadJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final EnableTableFixedHeadJsReference INSTANCE = new EnableTableFixedHeadJsReference();
    private static final String FUNCTION_NAME = "enableTableFixedHead";

    private EnableTableFixedHeadJsReference() {
        super(EnableTableFixedHeadJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                JavaScriptHeaderItem
                        .forReference(ABaseWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()),
                JavaScriptHeaderItem.forReference(FloatTheadJsReference.INSTANCE));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnLoadHeaderItem.forScript(FUNCTION_NAME + "();"));
    }
}