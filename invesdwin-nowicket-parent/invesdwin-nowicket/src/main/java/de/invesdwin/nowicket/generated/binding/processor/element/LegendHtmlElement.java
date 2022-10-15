package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.FieldSetOpenModelElement;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class LegendHtmlElement extends AModelSuffixHtmlElement {

    public static final String LEGEND_WICKET_ID_SUFFIX = FieldSetOpenModelElement.LEGEND_WICKET_ID_SUFFIX;

    public LegendHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitLegend(this);
    }

    @Override
    public String getModelWicketId() {
        return Strings.removeEnd(getWicketId(), LEGEND_WICKET_ID_SUFFIX);
    }

}
