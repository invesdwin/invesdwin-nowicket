package de.invesdwin.nowicket.generated.markup.processor.element;

import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.AModelContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public abstract class AModelElement<E extends IBeanPathElement> implements IModelElement<E> {

    private final AModelContext context;
    private final E beanPathElement;
    private boolean firstAccept = true;

    public AModelElement(final AModelContext context, final E beanPathElement) {
        this.context = context;
        this.beanPathElement = beanPathElement;
    }

    @Override
    public final AModelContext getContext() {
        return context;
    }

    @Override
    public E getBeanPathElement() {
        return beanPathElement;
    }

    @Override
    public final String getWicketId() {
        return getBeanPathElement().getBeanPath();
    }

    @Override
    public final String getStaticTitle() {
        return getBeanPathElement().getTitle();
    }

    /**
     * Can be overriden to do additional things on first accept.
     */
    protected void onFirstAccept() {}

    @Override
    public boolean accept(final List<IModelVisitor> visitors) {
        if (getBeanPathElement().shouldBeAddedToElementRegistry()) {
            getContext().getElementRegistry().addElement(this);
        }
        if (firstAccept) {
            onFirstAccept();
            firstAccept = false;
        }
        for (final IModelVisitor visitor : visitors) {
            innerAccept(visitor);
        }
        return true;
    }

    @Override
    public final boolean accept(final IModelVisitor... visitors) {
        return accept(Arrays.asList(visitors));
    }

    protected abstract void innerAccept(final IModelVisitor visitor);

    @Override
    public final String toString() {
        return Objects.toStringHelper(this).add("wicketId", getWicketId()).toString();
    }
}
