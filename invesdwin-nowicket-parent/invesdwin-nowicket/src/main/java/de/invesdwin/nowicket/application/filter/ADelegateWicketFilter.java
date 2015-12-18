package de.invesdwin.nowicket.application.filter;

import java.util.List;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.protocol.http.WicketFilter;

import de.invesdwin.nowicket.application.IWebApplication;
import de.invesdwin.util.lang.Reflections;

@NotThreadSafe
public abstract class ADelegateWicketFilter extends WicketFilter {

    {
        final Set<String> ignorePaths = Reflections.field("ignorePaths").ofType(Set.class).in(this).get();
        final List<String> filter = resolveDelegate().getWicketFilterIgnorePaths();
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

    protected abstract IWebApplication resolveDelegate();

}
