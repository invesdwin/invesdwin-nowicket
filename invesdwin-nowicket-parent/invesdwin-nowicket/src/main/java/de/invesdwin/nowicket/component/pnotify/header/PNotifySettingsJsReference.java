package de.invesdwin.nowicket.component.pnotify.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@Immutable
public final class PNotifySettingsJsReference extends JavaScriptResourceReference implements IHeaderContributor {

    public static final PNotifySettingsJsReference INSTANCE = new PNotifySettingsJsReference();

    private PNotifySettingsJsReference() {
        super(PNotifySettingsJsReference.class, "pnotify.settings.js");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
    }

}