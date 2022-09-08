package de.invesdwin.nowicket.generated.markup.processor.element;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.table.column.TableButtonColumnBeanPathElement;
import de.invesdwin.nowicket.component.header.font.FontAwesome6IconType;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;

@NotThreadSafe
public class TableSubmitButtonColumnModelElement extends ATableColumnModelElement<TableButtonColumnBeanPathElement>
        implements IButtonModelElement<TableButtonColumnBeanPathElement> {

    public TableSubmitButtonColumnModelElement(final AModelContext context,
            final TableButtonColumnBeanPathElement beanPathElement) {
        super(context, beanPathElement);
    }

    @Override
    public String getIconCssClassPropertyName() {
        return getWicketId() + ICON_CSS_CLASS_PROPERTY_NAME_SUFFIX;
    }

    @Override
    public String getDefaultIconCssClass() {
        return FontAwesome6IconType.magnifying_glass_s.cssClassName();
    }

}
