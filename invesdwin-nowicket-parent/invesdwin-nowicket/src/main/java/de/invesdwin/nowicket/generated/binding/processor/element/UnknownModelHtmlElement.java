package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.IModelElement;

/**
 * HtmlElement has wicketId and a ModelElement, but does not represent any other known HtmlElement.
 * 
 * E.g. a span that should be bound to a property in the model.
 */
@NotThreadSafe
public class UnknownModelHtmlElement extends AModelHtmlElement<IModelElement<?>, Object>
        implements IUnknownHtmlElement<Object> {

    public UnknownModelHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitUnknown(this);
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

}
