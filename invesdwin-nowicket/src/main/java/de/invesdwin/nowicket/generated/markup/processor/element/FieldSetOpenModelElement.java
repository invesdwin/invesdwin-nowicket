package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.ContainerOpenBeanPathElement;

@NotThreadSafe
public class FieldSetOpenModelElement extends AModelElement<ContainerOpenBeanPathElement> {

    public static final String LEGEND_WICKET_ID_SUFFIX = "_legend";

    public FieldSetOpenModelElement(final AModelContext context, final ContainerOpenBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    public String getLegendWicketId() {
        return getWicketId() + LEGEND_WICKET_ID_SUFFIX;
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitFieldSetOpen(this);
    }

}
