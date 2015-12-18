package de.invesdwin.nowicket.generated.markup.processor.context;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class AModelContext {

    private final ModelElementRegistry elementRegistry;

    public AModelContext() {
        this.elementRegistry = new ModelElementRegistry();
    }

    public ModelElementRegistry getElementRegistry() {
        return elementRegistry;
    }

}
