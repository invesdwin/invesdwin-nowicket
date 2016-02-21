package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;

import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.panel.step1.CreateUserWizardStep1Constants;
import de.invesdwin.nowicket.generated.binding.processor.element.IHtmlElement;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.BindingInterceptor;

@NotThreadSafe
public class CreateUserWizardStepBindingInterceptor extends BindingInterceptor {

    private final MarkupContainer container;

    public CreateUserWizardStepBindingInterceptor(final MarkupContainer container) {
        this.container = container;
    }

    @Override
    protected Component create(final IHtmlElement<?, ?> e) {
        if (CreateUserWizardStep1Constants.control.equals(e.getWicketId())) {
            return new CreateUserWizardStepControlPanel(e.getWicketId(),
                    new AbstractReadOnlyModel<CreateUserWizardStepControl>() {
                        @Override
                        public CreateUserWizardStepControl getObject() {
                            final ICreateUserWizardStep model = (ICreateUserWizardStep) container
                                    .getDefaultModelObject();
                            return model.getControl();
                        }
                    });
        }
        return super.create(e);
    }

}
