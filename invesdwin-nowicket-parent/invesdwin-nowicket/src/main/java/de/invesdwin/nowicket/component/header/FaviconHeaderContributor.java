package de.invesdwin.nowicket.component.header;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.util.assertions.Assertions;

/**
 * If the browser does not automatically load the favicon.ico, this class makes it do so.
 * 
 * @author subes
 * 
 */
@Immutable
public class FaviconHeaderContributor implements IHeaderContributor {

    private final String faviconUrl;

    public FaviconHeaderContributor(final String faviconUrl) {
        Assertions.assertThat(faviconUrl).isNotBlank();
        this.faviconUrl = faviconUrl;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        response.render(new StringHeaderItem(getFaviconReference()));
    }

    private CharSequence getFaviconReference() {
        final StringBuilder sb = new StringBuilder();
        sb.append("<link rel=\"shortcut icon\" href=\"");
        final String url = RequestCycle.get().getUrlRenderer().renderContextRelativeUrl(faviconUrl);
        sb.append(url);
        sb.append("\" type=\"image/x-icon\" />\n");
        return sb.toString();
    }

}