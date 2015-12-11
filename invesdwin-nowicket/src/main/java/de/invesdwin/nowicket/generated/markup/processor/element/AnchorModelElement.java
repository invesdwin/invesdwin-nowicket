package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.APropertyBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.HiddenBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IActionBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.simple.PropertyGetterActionBeanPathElement;

@NotThreadSafe
public class AnchorModelElement extends AModelElement<IActionBeanPathElement> {

    public AnchorModelElement(final AModelContext context, final IActionBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    /**
     * This constructor adds support for getters being used as anchors via html customization.
     */
    public AnchorModelElement(final IModelElement<?> modelElement) {
        super(modelElement.getContext(), convertToActionBeanPathElement(modelElement));
    }

    private static IActionBeanPathElement convertToActionBeanPathElement(final IModelElement<?> modelElement) {
        final IBeanPathElement beanPathElement = HiddenBeanPathElement.maybeUnwrap(modelElement.getBeanPathElement());
        if (beanPathElement instanceof APropertyBeanPathElement) {
            final APropertyBeanPathElement propertyBeanPathElement = (APropertyBeanPathElement) beanPathElement;
            return new PropertyGetterActionBeanPathElement(propertyBeanPathElement.getSimplePropertyElement());
        } else {
            return (IActionBeanPathElement) beanPathElement;
        }
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitAnchor(this);
    }

    public AnchorType getType() {
        return AnchorType.valueOf(getBeanPathElement());
    }

}
