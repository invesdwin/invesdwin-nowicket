package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.bean.validation.IPropertyResolver;
import org.apache.wicket.bean.validation.Property;
import org.apache.wicket.markup.html.form.FormComponent;

import de.invesdwin.norva.beanpath.impl.clazz.BeanClassAccessor;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;

/**
 * This class fixes the following problem in PropertyResolver for IFormComponentAware:
 * 
 * Caused by: java.lang.IllegalStateException: Could not resolve Bean Property from component: [Selection [Component id
 * = selection]]. (Hints:) Possible causes are a typo in the PropertyExpression, a null reference or a model that does
 * not work in combination with a IPropertyResolver.
 *
 */
@Immutable
public final class FormComponentAwarePropertyResolver implements IPropertyResolver {

    private static final MetaDataKey<Property> PROPERTY_KEY = new MetaDataKey<Property>() {
    };

    public FormComponentAwarePropertyResolver() {
    }

    @Override
    public Property resolveProperty(final FormComponent<?> component) {
        return component.getMetaData(PROPERTY_KEY);
    }

    public static void maybeRegisterElement(final IHtmlElement<?, ?> element, final Component component,
            final FormComponent<?> formComponent) {
        if (component instanceof IFormComponentAware) {
            final BeanClassAccessor accessor = element.getModelElement()
                    .getBeanPathElement()
                    .getAccessor()
                    .unwrap(BeanClassAccessor.class);
            formComponent.setMetaData(PROPERTY_KEY,
                    new Property(accessor.getContainer().getType().getType(), accessor.getBeanPathFragment()));
        }
    }

}
