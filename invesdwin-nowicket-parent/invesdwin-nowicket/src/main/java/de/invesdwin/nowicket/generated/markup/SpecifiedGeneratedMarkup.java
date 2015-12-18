package de.invesdwin.nowicket.generated.markup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.processor.ModelClassProcessor;
import de.invesdwin.nowicket.generated.markup.processor.context.MarkupType;
import de.invesdwin.nowicket.generated.markup.processor.context.ModelClassContext;
import de.invesdwin.nowicket.generated.markup.processor.visitor.IModelVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.html.HtmlGeneratorVisitor;
import de.invesdwin.nowicket.generated.markup.processor.visitor.internal.properties.PropertiesVisitor;

@NotThreadSafe
public class SpecifiedGeneratedMarkup {

    private final File destination;
    private final Collection<Class<?>> modelClasses;

    public SpecifiedGeneratedMarkup(final File destination, final Class<?>... modelClasses) {
        this(destination, Arrays.asList(modelClasses));
    }

    public SpecifiedGeneratedMarkup(final File destination, final Collection<Class<?>> modelClasses) {
        this.destination = destination;
        this.modelClasses = modelClasses;
    }

    public void generate() {
        for (final Class<?> modelClass : modelClasses) {
            final ModelClassContext context = new ModelClassContext(destination, modelClass);
            final List<MarkupType> toBeGeneratedMarkupTypes = new ArrayList<MarkupType>();
            if (context.getExistingMarkupTypes().isEmpty()) {
                //default to generating a page
                toBeGeneratedMarkupTypes.add(MarkupType.PAGE);
            } else {
                //generate everything that exists
                toBeGeneratedMarkupTypes.addAll(context.getExistingMarkupTypes());
            }
            final List<IModelVisitor> visitors = new ArrayList<IModelVisitor>();
            for (final MarkupType toBeGeneratedMarkupType : toBeGeneratedMarkupTypes) {
                visitors.add(new HtmlGeneratorVisitor(context, toBeGeneratedMarkupType));
                visitors.add(new PropertiesVisitor(context, toBeGeneratedMarkupType));
            }
            new ModelClassProcessor(context, visitors).process();
        }
    }
}
