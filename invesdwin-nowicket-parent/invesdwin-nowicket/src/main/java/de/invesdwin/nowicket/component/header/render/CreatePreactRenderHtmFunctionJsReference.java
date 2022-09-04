package de.invesdwin.nowicket.component.header.render;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import de.invesdwin.nowicket.component.header.render.htm.HtmModuleJsReference;
import de.invesdwin.nowicket.component.header.render.preact.PreactModuleJsReference;

@Immutable
public final class CreatePreactRenderHtmFunctionJsReference implements IHeaderContributor {

    public static final CreatePreactRenderHtmFunctionJsReference INSTANCE = new CreatePreactRenderHtmFunctionJsReference();
    private static final String FUNCTION_NAME = "createPreactRenderHtmFunction";
    private static final Resource JS_RESOURCE = new ClassPathResource(FUNCTION_NAME + ".js",
            CreatePreactRenderHtmFunctionJsReference.class);

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(new JavaScriptModuleHeaderItem(createJavascript(), FUNCTION_NAME));
    }

    private String createJavascript() {
        try {
            final InputStream in = JS_RESOURCE.getInputStream();
            String js = IOUtils.toString(in, Charset.defaultCharset());
            in.close();
            js = js.replace("${PREACT_RESOURCE}", getUrl(PreactModuleJsReference.INSTANCE));
            js = js.replace("${HTM_RESOURCE}", getUrl(HtmModuleJsReference.INSTANCE));
            return js;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUrl(final ResourceReference reference) {
        final IRequestHandler handler = new ResourceReferenceRequestHandler(reference, null);
        return RequestCycle.get().urlFor(handler).toString();
    }
}