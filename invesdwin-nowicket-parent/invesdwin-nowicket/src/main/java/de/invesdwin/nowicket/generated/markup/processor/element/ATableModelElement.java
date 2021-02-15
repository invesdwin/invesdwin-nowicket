package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.table.ATableBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.table.ITableColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.table.TableContainerColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.table.TableRemoveFromButtonColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.table.TableSelectionButtonColumnBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.collections.delegate.DelegateList;

@NotThreadSafe
public abstract class ATableModelElement extends AChoiceModelElement<ATableBeanPathElement> {

    private final TableRemoveFromButtonColumnModelElement removeFromButtonColumn;
    private final TableSelectionButtonColumnModelElement selectionButtonColumn;
    private List<ATableColumnModelElement<?>> rawColumns;
    private List<ATableColumnModelElement<?>> columns;

    public ATableModelElement(final AModelContext context, final ATableBeanPathElement beanPathElement) {
        super(context, beanPathElement);
        if (beanPathElement.getRemoveFromButtonColumn() != null) {
            removeFromButtonColumn = new TableRemoveFromButtonColumnModelElement(context,
                    beanPathElement.getRemoveFromButtonColumn());
        } else {
            removeFromButtonColumn = null;
        }
        if (beanPathElement.getSelectionButtonColumn() != null) {
            selectionButtonColumn = new TableSelectionButtonColumnModelElement(context,
                    beanPathElement.getSelectionButtonColumn());
        } else {
            selectionButtonColumn = null;
        }
    }

    public TableRemoveFromButtonColumnModelElement getRemoveFromButtonColumn() {
        return removeFromButtonColumn;
    }

    public TableSelectionButtonColumnModelElement getSelectionButtonColumn() {
        return selectionButtonColumn;
    }

    public abstract List<TableAnchorColumnModelElement> getAnchorColumns();

    public abstract List<TableSubmitButtonColumnModelElement> getButtonColumns();

    public abstract List<TableNumberColumnModelElement> getNumberColumns();

    public abstract List<TableDateColumnModelElement> getDateColumns();

    public abstract List<TableTextColumnModelElement> getTextColumns();

    public abstract TableContainerColumnModelElement getContainerColumn();

    public final List<ATableColumnModelElement<?>> getRawColumns() {
        if (rawColumns == null) {
            rawColumns = new ArrayList<ATableColumnModelElement<?>>();
            for (final ITableColumnBeanPathElement column : getBeanPathElement().getRawColumns()) {
                final ATableColumnModelElement<?> convertedColumn = convertElement(column);
                rawColumns.add(convertedColumn);
            }
        }
        return Collections.unmodifiableList(rawColumns);
    }

    public final List<ATableColumnModelElement<?>> getColumns() {
        if (columns == null) {
            columns = Collections.unmodifiableList(new DelegateList<ATableColumnModelElement<?>>(null) {
                @Override
                public List<ATableColumnModelElement<?>> getDelegate() {
                    final List<ATableColumnModelElement<?>> delegate = new ArrayList<ATableColumnModelElement<?>>();
                    for (final ITableColumnBeanPathElement column : getBeanPathElement().getColumns()) {
                        final ATableColumnModelElement<?> convertedColumn = convertElement(column);
                        delegate.add(convertedColumn);
                    }
                    return delegate;
                }

                @Override
                public int size() {
                    return getBeanPathElement().getColumns().size();
                }

                @Override
                public boolean isEmpty() {
                    return getBeanPathElement().getColumns().isEmpty();
                }
            });
        }
        return columns;
    }

    private ATableColumnModelElement<?> convertElement(final ITableColumnBeanPathElement column) {
        final ATableColumnModelElement<?> columnElement;
        if (TableContainerColumnBeanPathElement.COLUMN_ID.equals(column.getColumnId())) {
            columnElement = getContainerColumn();
        } else if (TableRemoveFromButtonColumnBeanPathElement.COLUMN_ID.equals(column.getColumnId())) {
            columnElement = getRemoveFromButtonColumn();
        } else if (TableSelectionButtonColumnBeanPathElement.COLUMN_ID.equals(column.getColumnId())) {
            columnElement = getSelectionButtonColumn();
        } else {
            columnElement = (ATableColumnModelElement<?>) getContext().getElementRegistry()
                    .getElement(column.getBeanPath());
        }
        Assertions.checkNotNull(columnElement, "%s: %s", column.getBeanPath(), column.getColumnId());
        return columnElement;
    }
}
