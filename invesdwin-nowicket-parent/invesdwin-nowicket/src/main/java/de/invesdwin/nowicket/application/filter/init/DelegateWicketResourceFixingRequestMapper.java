package de.invesdwin.nowicket.application.filter.init;

import java.util.Iterator;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.ICompoundRequestMapper;

import de.invesdwin.util.lang.Strings;

@Immutable
public class DelegateWicketResourceFixingRequestMapper implements ICompoundRequestMapper {

    private final ICompoundRequestMapper delegate;

    public DelegateWicketResourceFixingRequestMapper(final ICompoundRequestMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public IRequestHandler mapRequest(final Request request) {
        return delegate.mapRequest(request);
    }

    @Override
    public Url mapHandler(final IRequestHandler requestHandler) {
        final Url url = delegate.mapHandler(requestHandler);
        return maybeFixRelativeUrl(url);
    }

    protected Url maybeFixRelativeUrl(final Url url) {
        final String urlStr = url.toString();
        if (urlStr.contains("wicket/resource/")) {
            //some urls get generated wrong, they get their path added twice...
            final String fixedUrl = "./wicket/resource/" + Strings.substringAfterLast(urlStr, "wicket/resource/");
            return url.parse(fixedUrl);
        } else {
            return url;
        }
    }

    @Override
    public int getCompatibilityScore(final Request request) {
        return delegate.getCompatibilityScore(request);
    }

    @Override
    public Iterator<IRequestMapper> iterator() {
        return delegate.iterator();
    }

    @Override
    public ICompoundRequestMapper add(final IRequestMapper mapper) {
        return delegate.add(mapper);
    }

    @Override
    public ICompoundRequestMapper remove(final IRequestMapper mapper) {
        return delegate.remove(mapper);
    }

}
