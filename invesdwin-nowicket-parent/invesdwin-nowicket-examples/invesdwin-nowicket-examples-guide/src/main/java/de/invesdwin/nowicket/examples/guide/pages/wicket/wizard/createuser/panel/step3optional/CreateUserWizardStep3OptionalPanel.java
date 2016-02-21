package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.step3optional;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.CreateUserWizardStepBindingInterceptor;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateUserWizardStep3OptionalPanel extends Panel {

    public CreateUserWizardStep3OptionalPanel(final String id, final IModel<CreateUserWizardStep3Optional> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new CreateUserWizardStepBindingInterceptor(this)).bind();
    }

}
