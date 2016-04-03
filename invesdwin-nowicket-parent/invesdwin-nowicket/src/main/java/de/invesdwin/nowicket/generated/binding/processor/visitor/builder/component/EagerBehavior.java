package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.FormComponent;

import de.invesdwin.norva.beanpath.spi.element.APropertyBeanPathElement;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class EagerBehavior extends ModelAjaxFormSubmitBehavior {

    private final IHtmlElement<?, ?> element;
    private final Component component;

    public EagerBehavior(final IHtmlElement<?, ?> element, final Component component) {
        super("change");
        this.element = element;
        this.component = component;
        final FormComponent<?> formComponent = Components.asFormComponent(component);
        if (formComponent == null) {
            throw new IllegalArgumentException("Only " + FormComponent.class.getSimpleName() + "s are supported: "
                    + element.getWicketId() + ": " + component);
        }
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
        final APropertyBeanPathElement propertyElement = (APropertyBeanPathElement) element.getModelElement()
                .getBeanPathElement();
        propertyElement.getModifier().setValue(propertyElement.getModifier().getValue());

        //after that validate again to maybe have more errors being detected
        Components.validateModelUtilityValidators(target.getPage());
    }
}
