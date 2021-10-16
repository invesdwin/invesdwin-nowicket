package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column.selection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.TableSelectionButtonColumnHtmlElement;

@NotThreadSafe
public class ModelSelectionButtonColumn extends PropertyColumn<Object, String> {

    public static final String CSS_CLASS = "table-selection-column";

    private final TableSelectionButtonColumnHtmlElement element;

    public ModelSelectionButtonColumn(final TableSelectionButtonColumnHtmlElement element) {
        super(element.getTitleModelFromTarget(null), element.getColumnId());
        this.element = element;
    }

    public TableSelectionButtonColumnHtmlElement getElement() {
        return element;
    }

    @Override
    public Component getHeader(final String componentId) {
        //no header per default to keep column size as small as possible
        return new WebMarkupContainer(componentId).setVisible(false);
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> item, final String componentId,
            final IModel<Object> rowModel) {
        final Component checkBox = newCheckBox(componentId, rowModel, element);
        item.add(checkBox);
    }

    protected Component newCheckBox(final String componentId, final IModel<Object> rowModel,
            final TableSelectionButtonColumnHtmlElement element) {
        return new SelectionColumnCheckBoxPanel(componentId, rowModel, element);
    }

    @Override
    public String getCssClass() {
        return CSS_CLASS;
    }

}
