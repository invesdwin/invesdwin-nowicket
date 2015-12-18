package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.RootBeanPathElement;

@NotThreadSafe
public class RootModelElement extends AModelElement<RootBeanPathElement> {

    public static final String WICKET_ID = RootBeanPathElement.ROOT_BEAN_PATH;

    public RootModelElement(final AModelContext context, final RootBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    protected void innerAccept(final IModelVisitor visitor) {
        visitor.visitRoot(this);
    }

}
