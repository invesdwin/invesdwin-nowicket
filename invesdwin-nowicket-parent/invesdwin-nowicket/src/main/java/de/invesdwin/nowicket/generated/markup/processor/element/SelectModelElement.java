package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.AChoiceBeanPathElement;

@NotThreadSafe
public class SelectModelElement extends AChoiceModelElement<AChoiceBeanPathElement> {

    public SelectModelElement(final AModelContext context, final AChoiceBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitSelect(this);
    }

}
