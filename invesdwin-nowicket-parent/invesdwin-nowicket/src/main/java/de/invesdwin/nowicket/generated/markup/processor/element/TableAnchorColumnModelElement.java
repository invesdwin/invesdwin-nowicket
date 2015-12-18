package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.norva.beanpath.spi.element.TableButtonColumnBeanPathElement;

@NotThreadSafe
public class TableAnchorColumnModelElement extends ATableColumnModelElement<TableButtonColumnBeanPathElement> {

    public TableAnchorColumnModelElement(final AModelContext context,
            final TableButtonColumnBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    public AnchorType getType() {
        return AnchorType.valueOf(getBeanPathElement());
    }

}
