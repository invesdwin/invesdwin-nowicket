package de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.merge.layer;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.ecs.Element;

@NotThreadSafe
public abstract class AMergeLayer<E> {

    private final E container;
    private int size;

    public AMergeLayer(final E container) {
        this.container = container;
    }

    public void add(final Element element) {
        innerAdd(element);
        size++;
    }

    protected abstract void innerAdd(Element element);

    public E getContainer() {
        return container;
    }

    public int getSize() {
        return size;
    }
}
