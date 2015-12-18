package de.invesdwin.nowicket.generated.markup.processor;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.ModelObjectContext;
import de.invesdwin.nowicket.generated.markup.processor.internal.ModelProcessorVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.impl.object.BeanObjectProcessor;

@NotThreadSafe
public class ModelObjectProcessor {

    private final ModelObjectContext context;
    private final IModelVisitor[] visitors;

    public ModelObjectProcessor(final ModelObjectContext context, final IModelVisitor... visitors) {
        this.context = context;
        this.visitors = visitors;
    }

    public ModelObjectContext getContext() {
        return context;
    }

    public void process() {
        new BeanObjectProcessor(context.getBeanObjectContext(), new ModelProcessorVisitor(
                context.getBeanObjectContext(), context, visitors)).process();
    }
}
