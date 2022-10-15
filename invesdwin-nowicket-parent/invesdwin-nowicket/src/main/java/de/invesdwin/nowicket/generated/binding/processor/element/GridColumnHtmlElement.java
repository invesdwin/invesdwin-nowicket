package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class GridColumnHtmlElement extends AModelSuffixHtmlElement {

    public static final String GRID_COLUMN_WICKET_ID_SUFFIX = "_gridColumn";

    public GridColumnHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitGridColumn(this);
    }

    @Override
    public String getModelWicketId() {
        return Strings.removeEnd(getWicketId(), GRID_COLUMN_WICKET_ID_SUFFIX);
    }

}
