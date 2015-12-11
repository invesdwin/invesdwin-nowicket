package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.List;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;

public interface IModelElement<E extends IBeanPathElement> {

    AModelContext getContext();

    E getBeanPathElement();

    String getWicketId();

    String getStaticTitle();

    boolean accept(final List<IModelVisitor> visitors);

    boolean accept(final IModelVisitor... visitors);

}
