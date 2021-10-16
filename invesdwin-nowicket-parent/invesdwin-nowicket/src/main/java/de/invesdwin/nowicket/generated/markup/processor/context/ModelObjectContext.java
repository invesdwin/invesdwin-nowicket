package de.invesdwin.nowicket.generated.markup.processor.context;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.impl.clazz.BeanClassContext;
import de.invesdwin.norva.beanpath.impl.clazz.BeanClassProcessor;
import de.invesdwin.norva.beanpath.impl.object.IRootObjectReference;

@NotThreadSafe
public class ModelObjectContext extends AModelContext {

    private final IRootObjectReference rootObjectReference;
    private final Class<? extends Object> modelClass;
    private final BeanClassContext beanClassContext;

    public ModelObjectContext(final IRootObjectReference rootObjectReference) {
        this.rootObjectReference = rootObjectReference;
        this.modelClass = rootObjectReference.getRootObject().getClass();
        this.beanClassContext = BeanClassProcessor.getContext(getModelClass());
    }

    public ModelObjectContext(final Object modelObject) {
        this.rootObjectReference = () -> modelObject;
        this.modelClass = modelObject.getClass();
        this.beanClassContext = new BeanClassContext(getModelClass());
    }

    public BeanClassContext getBeanClassContext() {
        return beanClassContext;
    }

    public Object getModelObject() {
        return rootObjectReference.getRootObject();
    }

    public Class<?> getModelClass() {
        return modelClass;
    }

}
