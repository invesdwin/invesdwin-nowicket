package de.invesdwin.nowicket.generated.markup.processor.visitor;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;

@NotThreadSafe
public abstract class AModelVisitor implements IModelVisitor {

    private final ModelClassContext context;

    public AModelVisitor(final ModelClassContext context) {
        this.context = context;
    }

    @Override
    public ModelClassContext getContext() {
        return context;
    }

}
