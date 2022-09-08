package de.invesdwin.nowicket.component.header.render;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.core.util.string.JavaScriptUtils;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.value.AttributeMap;

@NotThreadSafe
public class JavaScriptModuleContentHeaderItem extends JavaScriptContentHeaderItem {

    public JavaScriptModuleContentHeaderItem(final CharSequence javaScript, final String id) {
        super(javaScript, id);
    }

    @Override
    public void render(final Response response) {
        final AttributeMap attributes = new AttributeMap();
        attributes.putAttribute(JavaScriptUtils.ATTR_TYPE, "module");
        attributes.putAttribute(JavaScriptUtils.ATTR_ID, getId());
        attributes.putAttribute(JavaScriptUtils.ATTR_CSP_NONCE, getNonce());
        JavaScriptUtils.writeInlineScript(response, getJavaScript(), attributes);
    }
}