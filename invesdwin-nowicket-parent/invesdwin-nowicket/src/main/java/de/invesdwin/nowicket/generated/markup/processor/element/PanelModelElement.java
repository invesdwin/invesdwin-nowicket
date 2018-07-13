package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.TextFieldBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;

@NotThreadSafe
public class PanelModelElement extends AModelElement<TextFieldBeanPathElement> {

    public PanelModelElement(final AModelContext context, final TextFieldBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    protected void innerAccept(final IModelVisitor visitor) {
        visitor.visitPanel(this);
    }

}
