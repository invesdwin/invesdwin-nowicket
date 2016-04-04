package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

import de.invesdwin.nowicket.NoWicketProperties;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.IEagerFilter;
import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class ValidatableSelectionModelDataTablePanel extends FormComponentPanel<Object> implements IEagerFilter {

    public ValidatableSelectionModelDataTablePanel(final TableHtmlElement element) {
        this(element, NoWicketProperties.DEFAULT_TABLE_ROWS_PER_PAGE);
    }

    public ValidatableSelectionModelDataTablePanel(final TableHtmlElement element, final long rowsPerPage) {
        super(element.getWicketId(), element.getModel());
        final DataTable<?, ?> dataTable = newDataTable("dataTable", element, rowsPerPage);
        appendAttributes(element, dataTable);
        add(dataTable);
        setRenderBodyOnly(true);
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

    protected DataTable<?, ?> newDataTable(final String wicketId, final TableHtmlElement element,
            final long rowsPerPage) {
        return new ModelDataTable(wicketId, element, rowsPerPage);
    }

    @Override
    public boolean isEagerAllowed(final IHtmlElement<?, ?> element, final Component component) {
        if (component instanceof ValidatableSelectionModelDataTablePanel) {
            return false;
        }
        return true;
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
