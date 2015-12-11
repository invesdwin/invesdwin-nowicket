package de.invesdwin.nowicket.generated.binding.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import org.jsoup.nodes.Element;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;

@NotThreadSafe
public class ContainerOpenHtmlElement extends ANoModelHtmlElement {

    public ContainerOpenHtmlElement(final HtmlContext context, final Element element) {
        super(context, element);
    }

    @Override
    protected boolean shouldBeAddedToElementRegistry() {
        return false;
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitContainerOpen(this);
    }

}
