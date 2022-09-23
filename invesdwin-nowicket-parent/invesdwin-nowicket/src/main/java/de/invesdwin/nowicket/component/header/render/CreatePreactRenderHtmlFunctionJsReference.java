package de.invesdwin.nowicket.component.header.render;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@Immutable
public final class CreatePreactRenderHtmlFunctionJsReference extends JavaScriptResourceReference
        implements IHeaderContributor {

    public static final CreatePreactRenderHtmlFunctionJsReference INSTANCE = new CreatePreactRenderHtmlFunctionJsReference();
    private static final String FUNCTION_NAME = "createPreactRenderHtmlFunction";

    private CreatePreactRenderHtmlFunctionJsReference() {
        super(CreatePreactRenderHtmlFunctionJsReference.class, FUNCTION_NAME + ".js");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(this));
        response.render(OnLoadHeaderItem.forScript(FUNCTION_NAME + "();"));
    }
}