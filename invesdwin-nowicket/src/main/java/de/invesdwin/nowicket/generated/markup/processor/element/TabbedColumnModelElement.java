package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.TabbedColumnBeanPathElement;

@NotThreadSafe
public class TabbedColumnModelElement extends AModelElement<TabbedColumnBeanPathElement> {

    public TabbedColumnModelElement(final AModelContext context, final TabbedColumnBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    protected void innerAccept(final IModelVisitor visitor) {
        throw new UnsupportedOperationException();
    }

}
