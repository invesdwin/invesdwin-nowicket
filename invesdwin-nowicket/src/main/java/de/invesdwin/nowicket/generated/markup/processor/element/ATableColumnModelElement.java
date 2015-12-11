package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;

@NotThreadSafe
public abstract class ATableColumnModelElement<E extends IBeanPathElement> extends AModelElement<E> {

    public ATableColumnModelElement(final AModelContext context, final E beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public final void innerAccept(final IModelVisitor visitor) {
        throw new UnsupportedOperationException();
    }

}
