package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.ATableBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;

@NotThreadSafe
public abstract class ATableModelElement extends AChoiceModelElement<ATableBeanPathElement> {

    private final TableRemoveFromButtonColumnModelElement removeFromButtonColumn;

    public ATableModelElement(final AModelContext context, final ATableBeanPathElement beanPathElement) {
        super(context, beanPathElement);
        if (beanPathElement.getTableRemoveFromButtonColumn() != null) {
            removeFromButtonColumn = new TableRemoveFromButtonColumnModelElement(context,
                    beanPathElement.getTableRemoveFromButtonColumn());
        } else {
            removeFromButtonColumn = null;
        }
    }

    public TableRemoveFromButtonColumnModelElement getRemoveFromButtonColumn() {
        return removeFromButtonColumn;
    }

    public abstract List<TableAnchorColumnModelElement> getAnchorColumns();

    public abstract List<TableSubmitButtonColumnModelElement> getButtonColumns();

    public abstract List<TableNumberColumnModelElement> getNumberColumns();

    public abstract List<TableDateColumnModelElement> getDateColumns();

    public abstract List<TableTextColumnModelElement> getTextColumns();

    public abstract TableContainerColumnModelElement getContainerColumn();

    public abstract List<ATableColumnModelElement<?>> getColumns();

    public abstract List<ATableColumnModelElement<?>> getRawColumns();
}
