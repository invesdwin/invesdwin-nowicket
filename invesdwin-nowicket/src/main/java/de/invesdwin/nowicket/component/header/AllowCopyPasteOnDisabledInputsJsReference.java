package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.application.auth.AWebApplication;

@Immutable
public final class AllowCopyPasteOnDisabledInputsJsReference extends JavaScriptResourceReference implements
IHeaderContributor {

    public static final AllowCopyPasteOnDisabledInputsJsReference INSTANCE = new AllowCopyPasteOnDisabledInputsJsReference();
    private static final String FUNCTION_NAME = "allowCopyPasteOnDisabledInputs";

    private AllowCopyPasteOnDisabledInputsJsReference() {
        super(AllowCopyPasteOnDisabledInputsJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return Dependencies.combine(
                super.getDependencies(),
                JavaScriptHeaderItem.forReference(AWebApplication.get()
                        .getJavaScriptLibrarySettings()
                        .getJQueryReference()));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnDomReadyHeaderItem.forScript(FUNCTION_NAME + "();"));
    }
}