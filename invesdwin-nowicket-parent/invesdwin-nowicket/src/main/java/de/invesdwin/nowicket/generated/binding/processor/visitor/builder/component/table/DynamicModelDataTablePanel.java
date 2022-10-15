package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

import de.invesdwin.nowicket.NoWicketProperties;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.IEagerFilter;
import de.invesdwin.util.lang.string.Strings;

/**
 * This table wrapper supports validation of selection and a dynamic change of columnOrder.
 */
@NotThreadSafe
public class DynamicModelDataTablePanel extends FormComponentPanel<Object> implements IEagerFilter {

    private static final String DATA_TABLE_ID = "dataTable";
    private List<String> columnOrder;
    private final TableHtmlElement element;
    private final long rowsPerPage;

    public DynamicModelDataTablePanel(final TableHtmlElement element) {
        this(element, NoWicketProperties.DEFAULT_TABLE_ROWS_PER_PAGE);
    }

    public DynamicModelDataTablePanel(final TableHtmlElement element, final long rowsPerPage) {
        super(element.getWicketId(), element.getModel());
        this.element = element;
        this.columnOrder = element.getColumnOrder();
        this.rowsPerPage = rowsPerPage;
        add(createDataTable(ModelDataTable.newSortableDataProvider(element)));
    }

    public DataTable<?, ?> getTable() {
        return (DataTable<?, ?>) get(DATA_TABLE_ID);
    }

    private DataTable<?, ?> createDataTable(final ISortableDataProvider<Object, String> sortableDataProvider) {
        final DataTable<?, ?> dataTable = newDataTable(DATA_TABLE_ID, element, sortableDataProvider, rowsPerPage);
        appendAttributes(element, dataTable);
        return dataTable;
    }

    private void appendAttributes(final TableHtmlElement element, final DataTable<?, ?> dataTable) {
        final Attributes attributes = element.getElement().attributes();
        for (final Attribute attr : attributes) {
            if (Strings.startsWithIgnoreCase(attr.getKey(), "wicket:")) {
                continue;
            }
            dataTable.add(AttributeAppender.append(attr.getKey(), attr.getValue()));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onConfigure() {
        super.onConfigure();
        final List<String> newColumnOrder = element.getColumnOrder();
        if (!newColumnOrder.equals(columnOrder)) {
            final DataTable<?, ?> existingDataTable = getTable();
            final ISortableDataProvider<Object, String> sortableDataProvider = (ISortableDataProvider<Object, String>) existingDataTable
                    .getDataProvider();
            existingDataTable.replaceWith(createDataTable(sortableDataProvider));
            columnOrder = newColumnOrder;
        }
    }

    protected DataTable<?, ?> newDataTable(final String wicketId, final TableHtmlElement element,
            final ISortableDataProvider<Object, String> sortableDataProvider, final long rowsPerPage) {
        return new ModelDataTable(wicketId, element, sortableDataProvider, rowsPerPage);
    }

    @Override
    public boolean isEagerAllowed(final IHtmlElement<?, ?> element, final Component component) {
        if (component instanceof DynamicModelDataTablePanel) {
            return false;
        }
        return true;
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        tag.setName("div");
        tag.getAttributes().clear();
        super.onComponentTag(tag);
    }

    @Override
    public void updateModel() {
        //no-op to not destroy the selection
    }

    @Override
    public void convertInput() {
        //prevent null values for validation
        setConvertedInput(getDefaultModelObject());
    }

}
