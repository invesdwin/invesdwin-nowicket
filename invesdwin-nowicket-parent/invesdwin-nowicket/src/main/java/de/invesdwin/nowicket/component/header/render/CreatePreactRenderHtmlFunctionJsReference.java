package de.invesdwin.nowicket.component.header.render;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import de.agilecoders.wicket.core.util.Dependencies;
import de.invesdwin.nowicket.component.header.render.preact.PreactJsReference;

@Immutable
public final class CreatePreactRenderHtmlFunctionJsReference extends JavaScriptResourceReference
        implements IHeaderContributor {

    public static final CreatePreactRenderHtmlFunctionJsReference INSTANCE = new CreatePreactRenderHtmlFunctionJsReference();
    private static final String FUNCTION_NAME = "createPreactRenderHtmlFunction";

    private CreatePreactRenderHtmlFunctionJsReference() {
        super(CreatePreactRenderHtmlFunctionJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return Dependencies.combine(super.getDependencies(),
                JavaScriptHeaderItem.forReference(PreactJsReference.INSTANCE));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnLoadHeaderItem.forScript(FUNCTION_NAME + "();"));
    }
}