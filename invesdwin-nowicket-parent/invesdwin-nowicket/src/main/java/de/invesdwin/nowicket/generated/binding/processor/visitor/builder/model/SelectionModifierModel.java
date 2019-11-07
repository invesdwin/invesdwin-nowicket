package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IObjectClassAwareModel;
import org.apache.wicket.model.IPropertyReflectionAwareModel;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.norva.beanpath.impl.clazz.IBeanClassAccessor;
import de.invesdwin.norva.beanpath.spi.element.simple.modifier.IBeanPathPropertyModifier;
import de.invesdwin.nowicket.generated.binding.processor.element.AChoiceHtmlElement;

@NotThreadSafe
public class SelectionModifierModel
        implements IPropertyReflectionAwareModel<List<Object>>, IObjectClassAwareModel<List<Object>> {

    private final IModel<?> rootObjectModel;
    private final IModel<IBeanPathPropertyModifier<List<?>>> selectionModifierModel;
    private final boolean singleSelection;
    private transient RequestCycle singleSelectionPrevRequestCycle;
    private transient Object singleSelectionPrevSelectedValue;

    public SelectionModifierModel(final AChoiceHtmlElement<?> element) {
        this.rootObjectModel = element.getRootObjectModel();
        this.selectionModifierModel = new IModel<IBeanPathPropertyModifier<List<?>>>() {
            @Override
            public IBeanPathPropertyModifier<List<?>> getObject() {
                return element.getModelElement().getBeanPathElement().getSelectionModifier();
            }
        };
        this.singleSelection = element.isSingleSelection();
    }

    @Override
    public void detach() {
        selectionModifierModel.detach();
        rootObjectModel.detach();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getObject() {
        final IBeanPathPropertyModifier<List<?>> selectionModifier = selectionModifierModel.getObject();
        return (List<Object>) selectionModifier.getValueFromRoot(rootObjectModel.getObject());
    }

    @Override
    public void setObject(final List<Object> object) {
        final IBeanPathPropertyModifier<List<?>> selectionModifier = selectionModifierModel.getObject();
        if (singleSelection) {
            final Object prevSelectedValue = getSingleSelectionPrevSelectedValue();
            if (object.size() > 1) {
                object.remove(prevSelectedValue);
            }
        }
        selectionModifier.setValueFromRoot(rootObjectModel.getObject(), object);
    }

    private Object getSingleSelectionPrevSelectedValue() {
        final RequestCycle curRequestCycle = RequestCycle.get();
        if (singleSelectionPrevRequestCycle == null || curRequestCycle != singleSelectionPrevRequestCycle) {
            singleSelectionPrevRequestCycle = curRequestCycle;
            final List<Object> value = getObject();
            if (value.size() > 1) {
                throw new IllegalStateException("SingleSelection cannot have more than 1 value!");
            } else if (value.isEmpty()) {
                singleSelectionPrevSelectedValue = null;
            } else {
                singleSelectionPrevSelectedValue = value.get(0);
            }
        }
        return singleSelectionPrevSelectedValue;
    }

    @Override
    public Field getPropertyField() {
        final IBeanClassAccessor accessor = selectionModifierModel.getObject().getBeanClassAccessor();
        /*
         * field is maybe private, but might have important annotation that wicket needs to know about; methods should
         * instead only be considered if public
         */
        return accessor.getRawField();
    }

    @Override
    public Method getPropertyGetter() {
        return selectionModifierModel.getObject().getBeanClassAccessor().getPublicGetterMethod();
    }

    @Override
    public Method getPropertySetter() {
        return selectionModifierModel.getObject().getBeanClassAccessor().getPublicSetterMethod();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Class getObjectClass() {
        final IBeanClassAccessor accessor = selectionModifierModel.getObject().getBeanClassAccessor();
        return accessor.getRawType().getType();
    }

}
