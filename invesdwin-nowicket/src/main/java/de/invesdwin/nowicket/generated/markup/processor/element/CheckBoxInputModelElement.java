package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.CheckBoxBeanPathElement;

@NotThreadSafe
public class CheckBoxInputModelElement extends AModelElement<CheckBoxBeanPathElement> {

    public CheckBoxInputModelElement(final AModelContext context, final CheckBoxBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitCheckBoxInput(this);
    }

}
