package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.TableModelElement;

@NotThreadSafe
public class UnorderedListHtmlElement extends AChoiceHtmlElement<TableModelElement> {

    public UnorderedListHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitUnorderedList(this);
    }

}
