package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.table.column.TableRemoveFromButtonColumnBeanPathElement;
import de.invesdwin.nowicket.component.header.font.FontAwesome6IconType;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;

@NotThreadSafe
public class TableRemoveFromButtonColumnModelElement extends TableSubmitButtonColumnModelElement {

    public TableRemoveFromButtonColumnModelElement(final AModelContext context,
            final TableRemoveFromButtonColumnBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public TableRemoveFromButtonColumnBeanPathElement getBeanPathElement() {
        return (TableRemoveFromButtonColumnBeanPathElement) super.getBeanPathElement();
    }

    @Override
    public String getDefaultIconCssClass() {
        return FontAwesome6IconType.xmark_s.cssClassName();
    }

}
