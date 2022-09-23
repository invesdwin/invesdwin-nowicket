package de.invesdwin.nowicket.application.filter.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

@NotThreadSafe
public class BundleCollectingHeaderResponse implements IHeaderResponse {

    private static final JavaScriptResourceReference[] EMPTY_JS_ARRAY = new JavaScriptResourceReference[0];
    private static final CssResourceReference[] EMPTY_CSS_ARRAY = new CssResourceReference[0];
    private static final HeaderItem[] EMPTY_OTHER_ARRAY = new HeaderItem[0];
    private final List<JavaScriptResourceReference> javascriptResources = new ArrayList<>();
    private final List<CssResourceReference> cssResources = new ArrayList<>();
    private final List<HeaderItem> otherHeaderItems = new ArrayList<>();

    private final Set<HeaderItem> renderedItems = new HashSet<>();

    @Override
    public void render(final HeaderItem item) {
        if (!renderedItems.add(item)) {
            return;
        }
        if (item instanceof JavaScriptReferenceHeaderItem) {
            final JavaScriptReferenceHeaderItem cItem = (JavaScriptReferenceHeaderItem) item;
            javascriptResources.add((JavaScriptResourceReference) cItem.getReference());
        } else if (item instanceof CssReferenceHeaderItem) {
            final CssReferenceHeaderItem cItem = (CssReferenceHeaderItem) item;
            cssResources.add((CssResourceReference) cItem.getReference());
        } else {
            otherHeaderItems.add(item);
        }

        final List<HeaderItem> dependencies = item.getDependencies();
        for (int i = 0; i < dependencies.size(); i++) {
            final HeaderItem dependency = dependencies.get(i);
            render(dependency);
        }
    }

    public JavaScriptResourceReference[] getJavascriptResources() {
        return javascriptResources.toArray(EMPTY_JS_ARRAY);
    }

    public CssResourceReference[] getCssResources() {
        return cssResources.toArray(EMPTY_CSS_ARRAY);
    }

    public HeaderItem[] getOtherHeaderItems() {
        return otherHeaderItems.toArray(EMPTY_OTHER_ARRAY);
    }

    @Override
    public void markRendered(final Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean wasRendered(final Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response getResponse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isClosed() {
        throw new UnsupportedOperationException();
    }

}
