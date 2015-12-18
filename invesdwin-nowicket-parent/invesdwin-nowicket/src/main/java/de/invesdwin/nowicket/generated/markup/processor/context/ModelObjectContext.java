package de.invesdwin.nowicket.generated.markup.processor.context;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.impl.object.BeanObjectContext;
import de.invesdwin.norva.beanpath.impl.object.IRootObjectReference;

@NotThreadSafe
public class ModelObjectContext extends AModelContext {

    private final BeanObjectContext beanObjectContext;

    public ModelObjectContext(final IRootObjectReference rootObjectReference) {
        this.beanObjectContext = new BeanObjectContext(rootObjectReference);
    }

    public ModelObjectContext(final Object modelObject) {
        this.beanObjectContext = new BeanObjectContext(modelObject);
    }

    public BeanObjectContext getBeanObjectContext() {
        return beanObjectContext;
    }

    public Object getModelObject() {
        return beanObjectContext.getRootContainer().getRootObject();
    }

}
