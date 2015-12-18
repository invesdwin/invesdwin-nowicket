package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;

import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class ModelPropertyValidator extends PropertyValidator<Object> {

    private final Component component;

    public ModelPropertyValidator(final Component component) {
        this.component = component;
    }

    @Override
    public void validate(final IValidatable<Object> validatable) {
        super.validate(new DelegateValidatable(validatable));
    }

    private class DelegateValidatable implements IValidatable<Object> {

        private final IValidatable<Object> delegate;

        DelegateValidatable(final IValidatable<Object> delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object getValue() {
            //handle IFormComponentAware properly
            return Components.getValidatableValue(component, delegate);
        }

        @Override
        public void error(final IValidationError error) {
            delegate.error(error);
        }

        @Override
        public boolean isValid() {
            return delegate.isValid();
        }

        @Override
        public IModel<Object> getModel() {
            return delegate.getModel();
        }

    }

}
