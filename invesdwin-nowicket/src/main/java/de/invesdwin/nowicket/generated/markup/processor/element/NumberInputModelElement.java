package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.TextFieldBeanPathElement;

@NotThreadSafe
public class NumberInputModelElement extends AModelElement<TextFieldBeanPathElement> {

    public NumberInputModelElement(final AModelContext context, final TextFieldBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitNumberInput(this);
    }

}
