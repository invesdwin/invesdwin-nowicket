package de.invesdwin.nowicket.component.pnotify.header.release;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.auth.AWebApplication;

@Immutable
public final class PNotifyJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final PNotifyJsReference INSTANCE = new PNotifyJsReference();

    private PNotifyJsReference() {
        super(PNotifyJsReference.class, "pnotify.custom.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(), JavaScriptHeaderItem
                .forReference(AWebApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }
}