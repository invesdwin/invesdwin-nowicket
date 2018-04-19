package de.invesdwin.nowicket.application.filter.init;

import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

import de.agilecoders.wicket.core.settings.ITheme;

@Immutable
public class ThemeAlias implements ITheme {

    private final String alias;
    private final ITheme delegate;

    public ThemeAlias(final String alias, final ITheme delegate) {
        this.alias = alias;
        this.delegate = delegate;
    }

    @Override
    public String name() {
        return alias;
    }

    @Override
    public List<HeaderItem> getDependencies() {
        return delegate.getDependencies();
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        delegate.renderHead(response);
    }

    @Override
    public Iterable<String> getCdnUrls() {
        return delegate.getCdnUrls();
    }

}
