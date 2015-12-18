package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;

/**
 * HtmlElement that has a wicketId without a ModelElement that does not represent any other known HtmlElement.
 * 
 * E.g. a span that should be bound without a model property.
 * 
 */
@NotThreadSafe
public class UnknownNoModelHtmlElement extends ANoModelHtmlElement implements IUnknownHtmlElement<Void> {

    public UnknownNoModelHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitUnknown(this);
    }

}
