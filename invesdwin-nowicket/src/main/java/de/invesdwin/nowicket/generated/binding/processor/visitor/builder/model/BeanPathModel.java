package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.IPropertyReflectionAwareModel;

import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.norva.beanpath.impl.clazz.IBeanClassAccessor;
import de.invesdwin.norva.beanpath.impl.object.BeanObjectContext;
import de.invesdwin.norva.beanpath.impl.object.BeanObjectProcessor;
import de.invesdwin.norva.beanpath.spi.element.IActionBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IPropertyBeanPathElement;

/**
 * PropertyModel is unsuitable cases where anchors are used on invokers while there is also a field with the same name.
 * Thus the field is tried by PropertyModel instead of the invoker. This model fixes this by going with beanpath
 * implementation.
 */
@NotThreadSafe
public class BeanPathModel<T> implements IPropertyReflectionAwareModel<T>, IObjectClassAwareModel<T> {

    private final IModel<?> rootObjectModel;
    private final IModel<IBeanPathElement> beanPathElementModel;

    public BeanPathModel(final IHtmlElement<?, ?> element) {
        this.rootObjectModel = element.getRootObjectModel();
        this.beanPathElementModel = new AbstractReadOnlyModel<IBeanPathElement>() {
            @Override
            public IBeanPathElement getObject() {
                return element.getModelElement().getBeanPathElement();
            }
        };
    }

    public BeanPathModel(final IModel<?> rootObjectModel, final String beanPath) {
        this.rootObjectModel = rootObjectModel;
        this.beanPathElementModel = new AbstractReadOnlyModel<IBeanPathElement>() {

            private transient BeanObjectContext ctx;

            @Override
            public IBeanPathElement getObject() {
                if (ctx == null) {
                    ctx = new BeanObjectContext(rootObjectModel.getObject());
                    new BeanObjectProcessor(ctx).process();
                }
                return ctx.getElementRegistry().getElement(beanPath);
            }
        };
    }

    @Override
    public void detach() {
        beanPathElementModel.detach();
        rootObjectModel.detach();
    }

    @Override
    public T getObject() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            final T value = (T) propertyElement.getModifier().getValueFromRoot(rootObjectModel.getObject());
            return value;
        } else if (beanPathElement instanceof IActionBeanPathElement) {
            final IActionBeanPathElement actionElement = (IActionBeanPathElement) beanPathElement;
            final T value = (T) actionElement.getInvoker().invokeFromRoot(rootObjectModel.getObject());
            return value;
        } else {
            throw new IllegalArgumentException("Not a property or action element: "
                    + beanPathElement.getClass().getSimpleName());
        }
    }

    @Override
    public void setObject(final T object) {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            propertyElement.getModifier().setValueFromRoot(rootObjectModel.getObject(), object);
        } else {
            throw new IllegalArgumentException("Not a property element: " + beanPathElement.getClass().getSimpleName());
        }
    }

    @Override
    public Field getPropertyField() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = propertyElement.getModifier().getAccessor().getBeanClassAccessor();
            /*
             * field is maybe private, but might have important annotation that wicket needs to know about; methods
             * should instead only be considered if public
             */
            return accessor.getRawField();
        }
        return null;
    }

    @Override
    public Method getPropertyGetter() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = propertyElement.getModifier().getAccessor().getBeanClassAccessor();
            return accessor.getPublicGetterMethod();
        } else if (beanPathElement instanceof IActionBeanPathElement) {
            final IActionBeanPathElement actionElement = (IActionBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = actionElement.getInvoker().getAccessor().getBeanClassAccessor();
            return accessor.getPublicActionMethod();
        } else {
            throw new IllegalArgumentException("Not a property or action element: "
                    + beanPathElement.getClass().getSimpleName());
        }
    }

    @Override
    public Method getPropertySetter() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            return propertyElement.getModifier().getAccessor().getBeanClassAccessor().getPublicSetterMethod();
        } else {
            return null;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Class getObjectClass() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = propertyElement.getModifier().getAccessor().getBeanClassAccessor();
            return accessor.getRawType().getType();
        } else if (beanPathElement instanceof IActionBeanPathElement) {
            final IActionBeanPathElement actionElement = (IActionBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = actionElement.getInvoker().getAccessor().getBeanClassAccessor();
            return accessor.getRawType().getType();
        } else {
            throw new IllegalArgumentException("Not a property or action element: "
                    + beanPathElement.getClass().getSimpleName());
        }
    }

}
