package de.invesdwin.nowicket.generated.markup.processor;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.impl.object.BeanObjectProcessor;
import de.invesdwin.norva.beanpath.spi.BeanPathProcessorConfig;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelObjectContext;
import de.invesdwin.nowicket.generated.markup.processor.internal.ModelProcessorVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;

@NotThreadSafe
public class ModelObjectProcessor {

    private static final BeanPathProcessorConfig CONFIG = new BeanPathProcessorConfig()
            .withIgnoreBeanPathEndPointAnnotation(true);
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
        new BeanObjectProcessor(CONFIG, context.getBeanObjectContext(), new ModelProcessorVisitor(context, visitors))
                .process();
    }
}
