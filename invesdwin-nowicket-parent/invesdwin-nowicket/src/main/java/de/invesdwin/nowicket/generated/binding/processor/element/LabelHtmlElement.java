package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.TextInputModelElement;

@NotThreadSafe
public class LabelHtmlElement extends AModelHtmlElement<TextInputModelElement, Object> {

    public LabelHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitLabel(this);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

}
