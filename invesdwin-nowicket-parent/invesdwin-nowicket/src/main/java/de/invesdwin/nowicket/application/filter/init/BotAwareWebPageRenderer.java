package de.invesdwin.nowicket.application.filter.init;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.request.handler.render.WebPageRenderer;

import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.nowicket.util.UserAgents;

/**
 * See:
 * http://stackoverflow.com/questions/14585858/is-renderstrategy-one-pass-render-a-reasonable-way-to-get-rid-of-page-
 * version-pa
 */
@ThreadSafe
public class BotAwareWebPageRenderer extends WebPageRenderer {

    public BotAwareWebPageRenderer(final RenderPageRequestHandler renderPageRequestHandler) {
        super(renderPageRequestHandler);
    }

    @Override
    protected boolean isOnePassRender() {
        // To avoid 302s with Google Bot and have good SEO.
        if (UserAgents.isBot() || RequestCycles.isOnePassRender()) {
            return true;
        } else {
            return super.isOnePassRender();
        }
    }

}
