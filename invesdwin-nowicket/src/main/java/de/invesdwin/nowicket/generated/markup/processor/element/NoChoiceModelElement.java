package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.AChoiceBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.APropertyBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.NoChoiceBeanPathElement;

@NotThreadSafe
public final class NoChoiceModelElement extends AChoiceModelElement<NoChoiceBeanPathElement> {

    private NoChoiceModelElement(final AModelElement<? extends APropertyBeanPathElement> original) {
        super(original.getContext(), new NoChoiceBeanPathElement(original.getBeanPathElement()));
    }

    @Override
    protected void innerAccept(final IModelVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    /**
     * Allow text and number fields to be converted into single value choices.
     */
    @SuppressWarnings("unchecked")
    public static AChoiceModelElement<? extends AChoiceBeanPathElement> maybeWrap(
            final AModelElement<? extends APropertyBeanPathElement> modelElement) {
        if (modelElement instanceof AChoiceModelElement) {
            return (AChoiceModelElement<AChoiceBeanPathElement>) modelElement;
        } else {
            return new NoChoiceModelElement(modelElement);
        }
    }

}
