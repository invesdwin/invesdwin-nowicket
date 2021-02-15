package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.table.column.TableButtonColumnBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;

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
