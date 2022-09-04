package de.invesdwin.nowicket.component.header.render.preact;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.parser.filter.HtmlHeaderSectionHandler;
import org.apache.wicket.page.PartialPageUpdate;

@NotThreadSafe
public class PartialHtmlHeaderContainer extends org.apache.wicket.markup.html.internal.HtmlHeaderContainer {
    private static final long serialVersionUID = 1L;

    /**
     * Keep transiently, in case the containing page gets serialized before this container is removed again. This
     * happens when DebugBar determines the page size by serializing/deserializing it.
     */
    private transient PartialPageUpdate pageUpdate;

    /**
     * Constructor.
     *
     * @param pageUpdate
     *            the partial page update
     */
    public PartialHtmlHeaderContainer(final PartialPageUpdate pageUpdate) {
        super(HtmlHeaderSectionHandler.HEADER_ID);

        this.pageUpdate = pageUpdate;
    }

    /**
     *
     * @see org.apache.wicket.markup.html.internal.HtmlHeaderContainer#newHeaderResponse()
     */
    @Override
    protected IHeaderResponse newHeaderResponse() {
        if (pageUpdate == null) {
            throw new IllegalStateException("disconnected from pageUpdate after serialization");
        }

        return pageUpdate.getHeaderResponse();
    }
}