package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.table.column.hide;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.processor.element.ATableColumnHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.element.TableHtmlElement;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public final class ModelDelegateHiddenColumn implements IColumn<Object, String> {

    private final ATableColumnHtmlElement<?, ?> element;
    private final IColumn<Object, String> delegate;

    private ModelDelegateHiddenColumn(final ATableColumnHtmlElement<?, ?> element,
            final IColumn<Object, String> delegate) {
        this.element = element;
        this.delegate = delegate;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<Object>> cellItem, final String componentId,
            final IModel<Object> rowModel) {
        delegate.populateItem(cellItem, componentId, rowModel);
        ModelHideColumnBehavior.maybeAdd(element, cellItem);
    }

    @Override
    public void detach() {
        delegate.detach();
    }

    @Override
    public Component getHeader(final String componentId) {
        final Component header = delegate.getHeader(componentId);
        ModelHideColumnBehavior.maybeAdd(element, header);
        return header;
    }

    @Override
    public String getSortProperty() {
        return delegate.getSortProperty();
    }

    @Override
    public boolean isSortable() {
        return delegate.isSortable();
    }

    public static List<? extends IColumn<Object, String>> maybeWrap(final TableHtmlElement element,
            final List<? extends IColumn<Object, String>> columns) {
        final List<IColumn<Object, String>> wrapped = new ArrayList<>();
        for (final IColumn<Object, String> column : columns) {
            wrapped.add(maybeWrap(element, column));
        }
        return wrapped;
    }

    public static IColumn<Object, String> maybeWrap(final TableHtmlElement element,
            final IColumn<Object, String> column) {
        final ATableColumnHtmlElement<?, ?> columnElement = element.getColumn(column.getSortProperty());
        if (column instanceof ModelDelegateHiddenColumn) {
            final ModelDelegateHiddenColumn cColumn = (ModelDelegateHiddenColumn) column;
            Assertions.checkEquals(columnElement.getModelElement().getBeanPathElement().getBeanPath(),
                    cColumn.element.getModelElement().getBeanPathElement().getBeanPath());
            return cColumn;
        } else {
            return new ModelDelegateHiddenColumn(columnElement, column);
        }
    }

}
