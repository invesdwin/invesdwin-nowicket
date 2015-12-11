package de.invesdwin.nowicket.generated.markup.processor;

import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.internal.ModelProcessorVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.norva.beanpath.impl.clazz.BeanClassProcessor;
import de.invesdwin.norva.beanpath.spi.visitor.PrintVisitor;

@NotThreadSafe
public class ModelClassProcessor {

    private final ModelClassContext context;
    private final List<IModelVisitor> visitors;

    public ModelClassProcessor(final ModelClassContext context, final IModelVisitor... visitors) {
        this(context, Arrays.asList(visitors));
    }

    public ModelClassProcessor(final ModelClassContext context, final List<IModelVisitor> visitors) {
        this.context = context;
        this.visitors = visitors;
    }

    public ModelClassContext getContext() {
        return context;
    }

    public void process() {
        new BeanClassProcessor(context.getBeanClassContext(), new ModelProcessorVisitor(context.getBeanClassContext(),
                context, visitors), new PrintVisitor(context.getBeanClassContext())).process();
    }

}
