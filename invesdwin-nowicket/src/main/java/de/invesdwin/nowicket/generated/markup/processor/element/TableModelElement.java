package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.ITableColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableButtonColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableTextColumnBeanPathElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class TableModelElement extends AChoiceModelElement<TableBeanPathElement> {

    private final List<TableTextColumnModelElement> textColumns;
    private final List<TableDateColumnModelElement> dateColumns;
    private final List<TableNumberColumnModelElement> numberColumns;
    private final List<TableSubmitButtonColumnModelElement> buttonColumns;
    private final List<TableAnchorColumnModelElement> anchorColumns;
    private final TableRemoveFromButtonColumnModelElement removeFromButtonColumn;
    private List<ATableColumnModelElement<?>> columns;
    private List<ATableColumnModelElement<?>> rawColumns;

    public TableModelElement(final AModelContext context, final TableBeanPathElement beanPathElement) {
        super(context, beanPathElement);
        textColumns = new ArrayList<TableTextColumnModelElement>();
        dateColumns = new ArrayList<TableDateColumnModelElement>();
        numberColumns = new ArrayList<TableNumberColumnModelElement>();
        for (final TableTextColumnBeanPathElement textColumn : beanPathElement.getTextColumns()) {
            if (textColumn.getAccessor().getType().isDate()) {
                dateColumns.add(new TableDateColumnModelElement(context, textColumn));
            } else if (textColumn.getAccessor().getType().isNumber()) {
                numberColumns.add(new TableNumberColumnModelElement(context, textColumn));
            } else {
                textColumns.add(new TableTextColumnModelElement(context, textColumn));
            }
        }
        buttonColumns = new ArrayList<TableSubmitButtonColumnModelElement>();
        anchorColumns = new ArrayList<TableAnchorColumnModelElement>();
        for (final TableButtonColumnBeanPathElement buttonColumn : beanPathElement.getButtonColumns()) {
            if (AnchorType.valueOf(buttonColumn) != null) {
                anchorColumns.add(new TableAnchorColumnModelElement(context, buttonColumn));
            } else {
                buttonColumns.add(new TableSubmitButtonColumnModelElement(context, buttonColumn));
            }
        }
        if (beanPathElement.getRemoveFromButtonColumn() != null) {
            removeFromButtonColumn = new TableRemoveFromButtonColumnModelElement(context,
                    beanPathElement.getRemoveFromButtonColumn());
        } else {
            removeFromButtonColumn = null;
        }
    }

    public List<TableTextColumnModelElement> getTextColumns() {
        return Collections.unmodifiableList(textColumns);
    }

    public List<TableDateColumnModelElement> getDateColumns() {
        return Collections.unmodifiableList(dateColumns);
    }

    public List<TableNumberColumnModelElement> getNumberColumns() {
        return Collections.unmodifiableList(numberColumns);
    }

    public List<TableSubmitButtonColumnModelElement> getButtonColumns() {
        return Collections.unmodifiableList(buttonColumns);
    }

    public List<TableAnchorColumnModelElement> getAnchorColumns() {
        return Collections.unmodifiableList(anchorColumns);
    }

    public TableRemoveFromButtonColumnModelElement getRemoveFromButtonColumn() {
        return removeFromButtonColumn;
    }

    public List<ATableColumnModelElement<?>> getColumns() {
        if (columns == null) {
            columns = new ArrayList<ATableColumnModelElement<?>>();
            for (final ITableColumnBeanPathElement column : getBeanPathElement().getColumns()) {
                final ATableColumnModelElement<?> columnElement = (ATableColumnModelElement<?>) getContext().getElementRegistry()
                        .getElement(column.getBeanPath());
                columns.add(columnElement);
            }
        }
        return Collections.unmodifiableList(columns);
    }

    public List<ATableColumnModelElement<?>> getRawColumns() {
        if (rawColumns == null) {
            rawColumns = new ArrayList<ATableColumnModelElement<?>>();
            for (final ITableColumnBeanPathElement column : getBeanPathElement().getRawColumns()) {
                final ATableColumnModelElement<?> columnElement = (ATableColumnModelElement<?>) getContext().getElementRegistry()
                        .getElement(column.getBeanPath());
                rawColumns.add(columnElement);
            }
        }
        return Collections.unmodifiableList(rawColumns);
    }

    @Override
    protected void onFirstAccept() {
        super.onFirstAccept();
        for (final TableTextColumnModelElement textColumn : textColumns) {
            Assertions.assertThat(textColumn.accept()).isTrue();
        }
        for (final TableDateColumnModelElement dateColumn : dateColumns) {
            Assertions.assertThat(dateColumn.accept()).isTrue();
        }
        for (final TableNumberColumnModelElement numberColumn : numberColumns) {
            Assertions.assertThat(numberColumn.accept()).isTrue();
        }
        for (final TableSubmitButtonColumnModelElement buttonColumn : buttonColumns) {
            Assertions.assertThat(buttonColumn.accept()).isTrue();
        }
        for (final TableAnchorColumnModelElement anchorColumn : anchorColumns) {
            Assertions.assertThat(anchorColumn.accept()).isTrue();
        }
        if (removeFromButtonColumn != null) {
            Assertions.assertThat(removeFromButtonColumn.accept()).isTrue();
        }
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitTable(this);
    }

}
