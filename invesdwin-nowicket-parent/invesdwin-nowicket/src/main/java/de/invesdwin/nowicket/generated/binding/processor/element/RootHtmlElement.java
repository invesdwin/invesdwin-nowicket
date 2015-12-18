package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.markup.processor.element.RootModelElement;
import de.invesdwin.norva.beanpath.spi.element.RootBeanPathElement;

@NotThreadSafe
public class RootHtmlElement extends AModelHtmlElement<RootModelElement, Void> {

    public static final String WICKET_ID = RootModelElement.WICKET_ID;

    public RootHtmlElement(final HtmlContext context) {
        super(context, RootBeanPathElement.ROOT_BEAN_PATH);
    }

    @Deprecated
    @Override
    public Format getFormat() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitRoot(this);
    }

}
