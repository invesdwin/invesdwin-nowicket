package de.invesdwin.nowicket.generated.binding.processor.element;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.jsoup.nodes.Element;

import de.invesdwin.norva.beanpath.spi.element.TableContainerColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableRemoveFromButtonColumnBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.TableSelectionButtonColumnBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.context.HtmlContext;
import de.invesdwin.nowicket.generated.binding.processor.visitor.IHtmlVisitor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.IBindingBuilder;
import de.invesdwin.nowicket.generated.markup.processor.element.AChoiceModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ATableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.ChoiceAsTableModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableAnchorColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableDateColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableNumberColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableSubmitButtonColumnModelElement;
import de.invesdwin.nowicket.generated.markup.processor.element.TableTextColumnModelElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class TableHtmlElement extends AChoiceHtmlElement<AChoiceModelElement<?>> {

    private final List<TableTextColumnHtmlElement> textColumns;
    private final List<TableDateColumnHtmlElement> dateColumns;
    private final List<TableNumberColumnHtmlElement> numberColumns;
    private final List<TableSubmitButtonColumnHtmlElement> buttonColumns;
    private final List<TableAnchorColumnHtmlElement> anchorColumns;
    private final TableContainerColumnHtmlElement containerColumn;
    private final TableRemoveFromButtonColumnHtmlElement removeFromButtonColumn;
    private final TableSelectionButtonColumnHtmlElement selectionButtonColumn;
    private List<ATableColumnHtmlElement<?, ?>> columns;
    private List<ATableColumnHtmlElement<?, ?>> rawColumns;

    //CHECKSTYLE:OFF
    public TableHtmlElement(final HtmlContext context, final Element element) {
        //CHECKSTYLE:ON
        super(context, element);
        textColumns = new ArrayList<TableTextColumnHtmlElement>();
        for (final TableTextColumnModelElement textColumn : getModelElement().getTextColumns()) {
            textColumns.add(new TableTextColumnHtmlElement(context, textColumn));
        }
        dateColumns = new ArrayList<TableDateColumnHtmlElement>();
        for (final TableDateColumnModelElement dateColumn : getModelElement().getDateColumns()) {
            dateColumns.add(new TableDateColumnHtmlElement(context, dateColumn));
        }
        numberColumns = new ArrayList<TableNumberColumnHtmlElement>();
        for (final TableNumberColumnModelElement numberColumn : getModelElement().getNumberColumns()) {
            numberColumns.add(new TableNumberColumnHtmlElement(context, numberColumn));
        }
        buttonColumns = new ArrayList<TableSubmitButtonColumnHtmlElement>();
        for (final TableSubmitButtonColumnModelElement buttonColumn : getModelElement().getButtonColumns()) {
            buttonColumns.add(new TableSubmitButtonColumnHtmlElement(context, buttonColumn));
        }
        anchorColumns = new ArrayList<TableAnchorColumnHtmlElement>();
        for (final TableAnchorColumnModelElement anchorColumn : getModelElement().getAnchorColumns()) {
            anchorColumns.add(new TableAnchorColumnHtmlElement(context, anchorColumn));
        }
        if (getModelElement().getContainerColumn() != null) {
            this.containerColumn = new TableContainerColumnHtmlElement(context, getModelElement().getContainerColumn());
        } else {
            this.containerColumn = null;
        }
        if (getModelElement().getRemoveFromButtonColumn() != null) {
            this.removeFromButtonColumn = new TableRemoveFromButtonColumnHtmlElement(context,
                    getModelElement().getRemoveFromButtonColumn());
        } else {
            this.removeFromButtonColumn = null;
        }
        if (getModelElement().getSelectionButtonColumn() != null) {
            this.selectionButtonColumn = new TableSelectionButtonColumnHtmlElement(context,
                    getModelElement().getSelectionButtonColumn());
        } else {
            this.selectionButtonColumn = null;
        }
    }

    @Override
    public ATableModelElement getModelElement() {
        return ChoiceAsTableModelElement.maybeWrap(super.getModelElement());
    }

    public List<TableTextColumnHtmlElement> getTextColumns() {
        return Collections.unmodifiableList(textColumns);
    }

    public List<TableDateColumnHtmlElement> getDateColumns() {
        return Collections.unmodifiableList(dateColumns);
    }

    public List<TableNumberColumnHtmlElement> getNumberColumns() {
        return Collections.unmodifiableList(numberColumns);
    }

    public List<TableSubmitButtonColumnHtmlElement> getButtonColumns() {
        return Collections.unmodifiableList(buttonColumns);
    }

    public List<TableAnchorColumnHtmlElement> getAnchorColumns() {
        return anchorColumns;
    }

    public TableContainerColumnHtmlElement getContainerColumn() {
        return containerColumn;
    }

    public TableRemoveFromButtonColumnHtmlElement getRemoveFromButtonColumn() {
        return removeFromButtonColumn;
    }

    public List<ATableColumnHtmlElement<?, ?>> getColumns() {
        if (columns == null) {
            columns = new ArrayList<ATableColumnHtmlElement<?, ?>>();
            for (final ATableColumnModelElement<?> column : getModelElement().getColumns()) {
                final ATableColumnHtmlElement<?, ?> convertedColumn = convertColumn(column);
                columns.add(convertedColumn);
            }
        }
        return Collections.unmodifiableList(columns);
    }

    private ATableColumnHtmlElement<?, ?> convertColumn(final ATableColumnModelElement<?> column) {
        final ATableColumnHtmlElement<?, ?> columnElement;
        if (TableContainerColumnBeanPathElement.COLUMN_ID.equals(column.getColumnId())) {
            columnElement = containerColumn;
        } else if (TableRemoveFromButtonColumnBeanPathElement.COLUMN_ID.equals(column.getColumnId())) {
            columnElement = removeFromButtonColumn;
        } else if (TableSelectionButtonColumnBeanPathElement.COLUMN_ID.equals(column.getColumnId())) {
            columnElement = selectionButtonColumn;
        } else {
            columnElement = getContext().getElementRegistry().getElement(column.getWicketId());
        }
        Assertions.checkNotNull(columnElement, "%s: %s", column.getWicketId(), column.getColumnId());
        return columnElement;
    }

    public List<ATableColumnHtmlElement<?, ?>> getRawColumns() {
        if (rawColumns == null) {
            rawColumns = new ArrayList<ATableColumnHtmlElement<?, ?>>();
            for (final ATableColumnModelElement<?> column : getModelElement().getRawColumns()) {
                final ATableColumnHtmlElement<?, ?> convertedColumn = convertColumn(column);
                rawColumns.add(convertedColumn);
            }
        }
        return Collections.unmodifiableList(rawColumns);
    }

    @Override
    protected void onFirstAccept() {
        super.onFirstAccept();
        for (final TableTextColumnHtmlElement textColumn : textColumns) {
            Assertions.assertThat(textColumn.accept()).isTrue();
        }
        for (final TableDateColumnHtmlElement dateColumn : dateColumns) {
            Assertions.assertThat(dateColumn.accept()).isTrue();
        }
        for (final TableNumberColumnHtmlElement numberColumn : numberColumns) {
            Assertions.assertThat(numberColumn.accept()).isTrue();
        }
        for (final TableSubmitButtonColumnHtmlElement buttonColumn : buttonColumns) {
            Assertions.assertThat(buttonColumn.accept()).isTrue();
        }
        for (final TableAnchorColumnHtmlElement anchorColumn : anchorColumns) {
            Assertions.assertThat(anchorColumn.accept()).isTrue();
        }
        if (removeFromButtonColumn != null) {
            Assertions.assertThat(removeFromButtonColumn.accept()).isTrue();
        }
    }

    @Override
    protected void innerAccept(final IHtmlVisitor visitor) {
        visitor.visitTable(this);
    }

    @Deprecated
    @Override
    public Format getFormat(final Locale locale) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public List<? extends IColumn<Object, String>> createWicketColumns(final IBindingBuilder bindingBuilder) {
        final List<IColumn<Object, String>> wicketColumns = new ArrayList<IColumn<Object, String>>();
        for (final ATableColumnHtmlElement<?, ?> column : getColumns()) {
            final IColumn<Object, String> wicketColumn = (IColumn<Object, String>) column
                    .createWicketColumn(bindingBuilder);
            wicketColumns.add(wicketColumn);
        }
        return wicketColumns;
    }

    public List<? extends IColumn<Object, String>> createWicketColumns() {
        return createWicketColumns(getContext().getBindingBuilder());
    }

}
