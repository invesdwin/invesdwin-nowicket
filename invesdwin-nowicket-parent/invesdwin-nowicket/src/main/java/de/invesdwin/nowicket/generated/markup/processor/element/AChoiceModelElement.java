package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.norva.beanpath.spi.element.AChoiceBeanPathElement;

@NotThreadSafe
public abstract class AChoiceModelElement<E extends AChoiceBeanPathElement> extends AModelElement<E> {

    public AChoiceModelElement(final AModelContext context, final E beanPathElement) {
        super(context, beanPathElement);
    }

}
