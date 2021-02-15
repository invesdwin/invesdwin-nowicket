package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.invesdwin.norva.beanpath.spi.element.table.TableRemoveFromButtonColumnBeanPathElement;
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
        return GlyphIconType.remove.cssClassName();
    }

}
