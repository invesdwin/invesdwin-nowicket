package de.invesdwin.nowicket.application.filter;

import java.util.List;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.protocol.ws.javax.JavaxWebSocketFilter;

import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.util.lang.Reflections;

@NotThreadSafe
public abstract class AWicketFilter extends JavaxWebSocketFilter {

    {
        final Set<String> ignorePaths = Reflections.field("ignorePaths").ofType(Set.class).in(this).get();
        final List<String> filter = newConfig().getWicketFilterIgnorePaths();
        if (filter != null) {
            for (String path : filter) {
                //remove / from path like wicketFilter does
                path = path.trim();
                if (path.startsWith("/")) {
                    path = path.substring(1);
                }
                ignorePaths.addAll(filter);
            }
        }
    }

    protected abstract IWebApplicationConfig newConfig();

}
