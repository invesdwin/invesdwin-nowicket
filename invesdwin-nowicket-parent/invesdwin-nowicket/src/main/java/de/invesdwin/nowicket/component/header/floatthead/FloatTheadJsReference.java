package de.invesdwin.nowicket.component.header.floatthead;

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
 * https://github.com/mkoryak/floatThead
 *
 */
@Immutable
public final class FloatTheadJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final FloatTheadJsReference INSTANCE = new FloatTheadJsReference();

    private FloatTheadJsReference() {
        super(FloatTheadJsReference.class, "jquery.floatThead.js");
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