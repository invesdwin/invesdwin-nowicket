package de.invesdwin.nowicket.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.resource.IResourceStream;

import de.invesdwin.nowicket.component.header.DisableComponentsOnAjaxCallJsReference;

/**
 * https://cwiki.apache.org/confluence/display/WICKET/AJAX+update+and+file+download+in+one+blow
 *
 */
@NotThreadSafe
public abstract class AAjaxDownload extends AbstractAjaxBehavior {
    private final boolean addAntiCache;

    public AAjaxDownload() {
        this(true);
    }

    public AAjaxDownload(final boolean addAntiCache) {
        super();
        this.addAntiCache = addAntiCache;
    }

    /**
     * Call this method to initiate the download.
     */
    public void initiate(final AjaxRequestTarget target) {
        String url = getCallbackUrl().toString();

        if (addAntiCache) {
            url = url + (url.contains("?") ? "&" : "?");
            url = url + "antiCache=" + System.currentTimeMillis();
        }

        // the timeout is needed to let Wicket release the channel
        target.appendJavaScript(newInitiateScript(url));
    }

    protected String newInitiateScript(final String url) {
        return DisableComponentsOnAjaxCallJsReference.UNDISABLE_FUNCTION_CALL_SNIPPET
                + " setTimeout(\"window.location.href='" + url + "'\", 100);";
    }

    @Override
    public void onRequest() {
        final ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(getResourceStream(),
                getFileName());
        handler.setContentDisposition(ContentDisposition.ATTACHMENT);
        getComponent().getRequestCycle().scheduleRequestHandlerAfterCurrent(handler);
    }

    /**
     * Override this method for a file name which will let the browser prompt with a save/open dialog.
     * 
     * @see ResourceStreamRequestTarget#getFileName()
     */
    protected abstract String getFileName();

    /**
     * Hook method providing the actual resource stream.
     */
    protected abstract IResourceStream getResourceStream();
}