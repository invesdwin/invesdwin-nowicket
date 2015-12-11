package de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.validation.IValidatable;

public interface IFormComponentAware {

    FormComponent<?> getFormComponent();

    Object getFormComponentValidatableValue(IValidatable<?> validatable);

}
