package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.ButtonBeanPathElement;

@NotThreadSafe
public class SubmitButtonModelElement extends AModelElement<ButtonBeanPathElement> implements
IButtonModelElement<ButtonBeanPathElement> {

    public SubmitButtonModelElement(final AModelContext context, final ButtonBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitSubmitButton(this);
    }

    @Override
    public String getIconCssClassPropertyName() {
        return getWicketId() + ICON_CSS_CLASS_PROPERTY_NAME_SUFFIX;
    }

    @Override
    public String getDefaultIconCssClass() {
        return NO_ICON_CSS_CLASS;
    }

}
