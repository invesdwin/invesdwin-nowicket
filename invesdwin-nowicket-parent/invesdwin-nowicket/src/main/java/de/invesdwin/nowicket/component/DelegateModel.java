package de.invesdwin.nowicket.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;

@NotThreadSafe
public class DelegateModel<E> implements IModel<E> {

    private IModel<E> delegate;

    public DelegateModel(final IModel<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void detach() {
        delegate.detach();
    }

    @Override
    public E getObject() {
        return delegate.getObject();
    }

    @Override
    public void setObject(final E object) {
        delegate.setObject(object);
    }

    public void setDelegate(final IModel<E> delegate) {
        this.delegate = delegate;
    }

}
