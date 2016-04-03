package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.spi.element.ITableColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableButtonColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableTextColumnBeanPathElement;
import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class TableModelElement extends ATableModelElement {

    private final List<TableTextColumnModelElement> textColumns;
    private final List<TableDateColumnModelElement> dateColumns;
    private final List<TableNumberColumnModelElement> numberColumns;
    private final List<TableSubmitButtonColumnModelElement> buttonColumns;
    private final List<TableAnchorColumnModelElement> anchorColumns;
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
    }

    @Override
    public List<TableTextColumnModelElement> getTextColumns() {
        return Collections.unmodifiableList(textColumns);
    }

    @Override
    public List<TableDateColumnModelElement> getDateColumns() {
        return Collections.unmodifiableList(dateColumns);
    }

    @Override
    public List<TableNumberColumnModelElement> getNumberColumns() {
        return Collections.unmodifiableList(numberColumns);
    }

    @Override
    public List<TableSubmitButtonColumnModelElement> getButtonColumns() {
        return Collections.unmodifiableList(buttonColumns);
    }

    @Override
    public List<TableAnchorColumnModelElement> getAnchorColumns() {
        return Collections.unmodifiableList(anchorColumns);
    }

    @Override
    public TableContainerColumnModelElement getContainerColumn() {
        //not used in real tables
        return null;
    }

    @Override
    public List<ATableColumnModelElement<?>> getColumns() {
        if (columns == null) {
            columns = new ArrayList<ATableColumnModelElement<?>>();
            for (final ITableColumnBeanPathElement column : getBeanPathElement().getColumns()) {
                final ATableColumnModelElement<?> columnElement = (ATableColumnModelElement<?>) getContext()
                        .getElementRegistry().getElement(column.getBeanPath());
                columns.add(columnElement);
            }
        }
        return Collections.unmodifiableList(columns);
    }

    @Override
    public List<ATableColumnModelElement<?>> getRawColumns() {
        if (rawColumns == null) {
            rawColumns = new ArrayList<ATableColumnModelElement<?>>();
            for (final ITableColumnBeanPathElement column : getBeanPathElement().getRawColumns()) {
                final ATableColumnModelElement<?> columnElement = (ATableColumnModelElement<?>) getContext()
                        .getElementRegistry().getElement(column.getBeanPath());
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
        if (getRemoveFromButtonColumn() != null) {
            Assertions.assertThat(getRemoveFromButtonColumn().accept()).isTrue();
        }
    }

    @Override
    public void innerAccept(final IModelVisitor visitor) {
        visitor.visitTable(this);
    }

}
