package de.invesdwin.nowicket.component.header.module;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.value.AttributeMap;

@NotThreadSafe
public class JavaScriptModuleReferenceHeaderItem extends JavaScriptReferenceHeaderItem {

    public JavaScriptModuleReferenceHeaderItem(final ResourceReference reference, final PageParameters pageParameters,
            final String id) {
        super(reference, pageParameters, id);
    }

    @Override
    public void render(final Response response) {
        final String url = getUrl();
        Args.notEmpty(url, "url");

        final AttributeMap attributes = new AttributeMap();
        attributes.putAttribute(JavaScriptUtils.ATTR_TYPE, "module");
        attributes.putAttribute(JavaScriptUtils.ATTR_ID, getId());
        attributes.putAttribute(JavaScriptUtils.ATTR_SCRIPT_DEFER, isDefer());
        // XXX this attribute is not necessary for modern browsers
        attributes.putAttribute("charset", getCharset());
        attributes.putAttribute(JavaScriptUtils.ATTR_SCRIPT_ASYNC, isAsync());
        attributes.putAttribute(JavaScriptUtils.ATTR_SCRIPT_SRC, url);
        attributes.putAttribute(JavaScriptUtils.ATTR_CSP_NONCE, getNonce());
        attributes.putAttribute(JavaScriptUtils.ATTR_CROSS_ORIGIN,
                getCrossOrigin() == null ? null : getCrossOrigin().getRealName());
        attributes.putAttribute(JavaScriptUtils.ATTR_INTEGRITY, getIntegrity());
        JavaScriptUtils.writeScript(response, attributes);
    }

    private String getUrl() {
        final IRequestHandler handler = new ResourceReferenceRequestHandler(getReference(), getPageParameters());
        return RequestCycle.get().urlFor(handler).toString();
    }

}