package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.IPropertyReflectionAwareModel;

import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;
import de.invesdwin.norva.beanpath.impl.clazz.IBeanClassAccessor;
import de.invesdwin.norva.beanpath.spi.element.simple.modifier.IBeanPathPropertyModifier;

@NotThreadSafe
public class SelectionModifierModel implements IPropertyReflectionAwareModel<List<Object>>,
IObjectClassAwareModel<List<Object>> {

    private final IModel<?> rootObjectModel;
    private final IModel<IBeanPathPropertyModifier<List<Object>>> selectionModifierModel;

    public SelectionModifierModel(final AChoiceHtmlElement<?> element) {
        this.rootObjectModel = element.getRootObjectModel();
        this.selectionModifierModel = new AbstractReadOnlyModel<IBeanPathPropertyModifier<List<Object>>>() {
            @Override
            public IBeanPathPropertyModifier<List<Object>> getObject() {
                return element.getModelElement().getBeanPathElement().getSelectionModifier();
            }
        };
    }

    @Override
    public void detach() {
        selectionModifierModel.detach();
        rootObjectModel.detach();
    }

    @Override
    public List<Object> getObject() {
        return selectionModifierModel.getObject().getValueFromRoot(rootObjectModel.getObject());
    }

    @Override
    public void setObject(final List<Object> object) {
        selectionModifierModel.getObject().setValueFromRoot(rootObjectModel.getObject(), object);
    }

    @Override
    public Field getPropertyField() {
        final IBeanClassAccessor accessor = selectionModifierModel.getObject().getAccessor().getBeanClassAccessor();
        /*
         * field is maybe private, but might have important annotation that wicket needs to know about; methods should
         * instead only be considered if public
         */
        return accessor.getRawField();
    }

    @Override
    public Method getPropertyGetter() {
        return selectionModifierModel.getObject().getAccessor().getBeanClassAccessor().getPublicGetterMethod();
    }

    @Override
    public Method getPropertySetter() {
        return selectionModifierModel.getObject().getAccessor().getBeanClassAccessor().getPublicSetterMethod();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Class getObjectClass() {
        final IBeanClassAccessor accessor = selectionModifierModel.getObject().getAccessor().getBeanClassAccessor();
        return accessor.getRawType().getType();
    }

}
