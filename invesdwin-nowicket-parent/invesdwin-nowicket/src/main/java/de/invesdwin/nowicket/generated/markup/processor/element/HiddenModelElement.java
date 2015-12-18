package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.HiddenBeanPathElement;

@NotThreadSafe
public class HiddenModelElement extends AModelElement<HiddenBeanPathElement> {

    public HiddenModelElement(final AModelContext context, final HiddenBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitHidden(this);
    }

}
