package de.invesdwin.nowicket.component.pnotify.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

@Immutable
public final class PNotifyHeaderContributor implements IHeaderContributor {

    public static final PNotifyHeaderContributor INSTANCE = new PNotifyHeaderContributor();

    private PNotifyHeaderContributor() {}

    @Override
    public void renderHead(final IHeaderResponse response) {
        PNotifyJsReference.INSTANCE.renderHead(response);
        PNotifySettingsJsReference.INSTANCE.renderHead(response);
        PNotifyCssReference.INSTANCE.renderHead(response);
    }

}
