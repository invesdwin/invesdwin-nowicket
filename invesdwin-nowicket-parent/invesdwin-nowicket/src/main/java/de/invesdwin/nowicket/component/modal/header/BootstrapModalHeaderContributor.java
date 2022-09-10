package de.invesdwin.nowicket.component.modal.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;

@Immutable
public final class BootstrapModalHeaderContributor implements IHeaderContributor {

    public static final BootstrapModalHeaderContributor INSTANCE = new BootstrapModalHeaderContributor();

    private BootstrapModalHeaderContributor() {}

    @Override
    public void renderHead(final IHeaderResponse response) {
        //        BootstrapModalBs3PatchCssReference.INSTANCE.renderHead(response);
        //        BootstrapModalCssReference.INSTANCE.renderHead(response);
        //        BootstrapModalmanagerJsReference.INSTANCE.renderHead(response);
        //        BootstrapModalJsReference.INSTANCE.renderHead(response);
        //        BootstrapMultiModalJsReference.INSTANCE.renderHead(response);
        //        JQueryDisablescrollJsReference.INSTANCE.renderHead(response);
        //        BootstrapModalConfigJsReference.INSTANCE.renderHead(response);
    }

}
