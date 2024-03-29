package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.IPropertyReflectionAwareModel;

import de.invesdwin.norva.beanpath.impl.clazz.BeanClassContext;
import de.invesdwin.norva.beanpath.impl.clazz.BeanClassProcessor;
import de.invesdwin.norva.beanpath.impl.clazz.BeanClassProcessorConfig;
import de.invesdwin.norva.beanpath.impl.clazz.IBeanClassAccessor;
import de.invesdwin.norva.beanpath.spi.element.IActionBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IBeanPathElement;
import de.invesdwin.norva.beanpath.spi.element.IPropertyBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

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
        this.beanPathElementModel = new IModel<IBeanPathElement>() {
            @Override
            public IBeanPathElement getObject() {
                return element.getModelElement().getBeanPathElement();
            }
        };
    }

    public BeanPathModel(final IModel<?> rootObjectModel, final String beanPath) {
        this.rootObjectModel = rootObjectModel;
        this.beanPathElementModel = new IModel<IBeanPathElement>() {

            private transient BeanClassContext ctx;

            @Override
            public IBeanPathElement getObject() {
                if (ctx == null) {
                    ctx = BeanClassProcessor
                            .getContext(BeanClassProcessorConfig.getDefault(rootObjectModel.getObject().getClass()));
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

    @SuppressWarnings("unchecked")
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
            throw new IllegalArgumentException(
                    "Not a property or action element: " + beanPathElement.getClass().getSimpleName());
        }
    }

    @Override
    public void setObject(final T object) {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            //skip if it is a final field
            if (propertyElement.getAccessor().hasPublicSetterOrField()) {
                propertyElement.getModifier().setValueFromRoot(rootObjectModel.getObject(), object);
            }
        } else {
            throw new IllegalArgumentException("Not a property element: " + beanPathElement.getClass().getSimpleName());
        }
    }

    @Override
    public Field getPropertyField() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = propertyElement.getModifier().getBeanClassAccessor();
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
            final IBeanClassAccessor accessor = propertyElement.getModifier().getBeanClassAccessor();
            return accessor.getPublicGetterMethod();
        } else if (beanPathElement instanceof IActionBeanPathElement) {
            final IActionBeanPathElement actionElement = (IActionBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = actionElement.getInvoker().getBeanClassAccessor();
            return accessor.getPublicActionMethod();
        } else {
            throw new IllegalArgumentException(
                    "Not a property or action element: " + beanPathElement.getClass().getSimpleName());
        }
    }

    @Override
    public Method getPropertySetter() {
        final IBeanPathElement beanPathElement = beanPathElementModel.getObject();
        if (beanPathElement instanceof IPropertyBeanPathElement) {
            final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) beanPathElement;
            return propertyElement.getModifier().getBeanClassAccessor().getPublicSetterMethod();
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
            final IBeanClassAccessor accessor = propertyElement.getModifier().getBeanClassAccessor();
            return accessor.getRawType().getType();
        } else if (beanPathElement instanceof IActionBeanPathElement) {
            final IActionBeanPathElement actionElement = (IActionBeanPathElement) beanPathElement;
            final IBeanClassAccessor accessor = actionElement.getInvoker().getBeanClassAccessor();
            return accessor.getRawType().getType();
        } else {
            throw new IllegalArgumentException(
                    "Not a property or action element: " + beanPathElement.getClass().getSimpleName());
        }
    }

}
