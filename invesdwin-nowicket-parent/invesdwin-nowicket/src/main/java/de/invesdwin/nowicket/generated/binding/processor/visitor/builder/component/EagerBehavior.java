package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

import de.invesdwin.norva.beanpath.spi.element.IPropertyBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class EagerBehavior extends ModelAjaxFormSubmitBehavior {

    public static final String DEFAULT_EAGER_EVENT = "change";

    private static final Set<IEagerFilter> EAGER_FILTERS = new HashSet<IEagerFilter>();

    static {
        EAGER_FILTERS.add(new IEagerFilter() {
            @Override
            public boolean isEagerAllowed(final IHtmlElement<?, ?> element, final Component component) {
                if (component instanceof Palette) {
                    return false;
                }
                return true;
            }
        });
    }

    private final IModel<Object> targetObjectModel;
    private final IHtmlElement<?, ?> element;
    private final Component component;

    public EagerBehavior(final IHtmlElement<?, ?> element, final Component component, final String eagerEvent) {
        super(eagerEvent);
        this.element = element;
        this.targetObjectModel = element.getTargetObjectModel();
        this.component = component;
        if (!isAllowed(element, component)) {
            throw new IllegalArgumentException("Eager not supported: " + element.getWicketId() + ": " + component);
        }
    }

    public EagerBehavior(final IHtmlElement<?, ?> element, final Component component) {
        this(element, component, DEFAULT_EAGER_EVENT);
    }

    @Override
    protected void onEvent(final AjaxRequestTarget target) {
        if (element.isForced()) {
            //remember old feedback messages
            Components.rememberAllFeedbackMessages(component);

            super.onEvent(target);

            //remove any new feedback messages and restore previous ones
            Components.rollbackAllFeedbackMessages(component);
        } else {
            super.onEvent(target);
        }
    }

    @Override
    protected void innerOnSubmit(final AjaxRequestTarget target) {
        /*
         * Reinvoke the eager setter, so that he can do checks and create side effects when all other fields have been
         * synchronized aswell by now. This workaround is required because wicket does not invoke setters in a specific
         * order. This workaround works because setters are supposed to contain cheap actions and because and each field
         * that depends on other fields is supposed to be an eager field anyway.
         */
        final IPropertyBeanPathElement propertyElement = (IPropertyBeanPathElement) element.getModelElement()
                .getBeanPathElement();
        final Object targetObject = targetObjectModel.getObject();
        propertyElement.getModifier()
                .setValueFromTarget(targetObject, propertyElement.getModifier().getValueFromTarget(targetObject));

        //after that validate again to maybe have more errors being detected
        Components.validateModelUtilityValidators(target.getPage());
    }

    public static boolean isAllowed(final IHtmlElement<?, ?> element, final Component component) {
        if (!element.isEager()) {
            return false;
        }
        final FormComponent<?> formComponent = Components.asFormComponent(component);
        if (formComponent == null) {
            return false;
        }
        if (component instanceof IEagerFilter) {
            final IEagerFilter filter = (IEagerFilter) component;
            if (!filter.isEagerAllowed(element, component)) {
                return false;
            }
        }
        for (final IEagerFilter filter : getEagerFilters()) {
            if (!filter.isEagerAllowed(element, formComponent)) {
                return false;
            }
        }
        return true;
    }

    public static Set<IEagerFilter> getEagerFilters() {
        return EAGER_FILTERS;
    }

}
