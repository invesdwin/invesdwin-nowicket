package de.invesdwin.nowicket.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

@NotThreadSafe
public class DelegateColumn<T, S> implements IColumn<T, S> {

    private final IColumn<T, S> delegate;

    public DelegateColumn(final IColumn<T, S> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId, final IModel<T> rowModel) {
        delegate.populateItem(cellItem, componentId, rowModel);
    }

    @Override
    public void detach() {
        delegate.detach();
    }

    @Override
    public Component getHeader(final String componentId) {
        return delegate.getHeader(componentId);
    }

    @Override
    public S getSortProperty() {
        return delegate.getSortProperty();
    }

    @Override
    public boolean isSortable() {
        return delegate.isSortable();
    }

    public IColumn<T, S> getDelegate() {
        return delegate;
    }

}
