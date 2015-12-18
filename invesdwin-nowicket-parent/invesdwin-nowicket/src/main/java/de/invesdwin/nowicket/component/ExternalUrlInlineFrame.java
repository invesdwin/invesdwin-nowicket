package de.invesdwin.nowicket.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

@NotThreadSafe
public class ExternalUrlInlineFrame extends WebMarkupContainer {
    private static final long serialVersionUID = 1L;

    private final IModel<?> src;

    public ExternalUrlInlineFrame(final String id, final IModel<?> src) {
        super(id);
        this.src = src;
    }

    @Override
    protected final void onComponentTag(final ComponentTag tag) {
        checkComponentTag(tag, "iframe");

        final Object url = src.getObject();
        if (url != null) {
            final String urlStr = url.toString();
            tag.put("src", urlStr);
        }

        super.onComponentTag(tag);
    }

}