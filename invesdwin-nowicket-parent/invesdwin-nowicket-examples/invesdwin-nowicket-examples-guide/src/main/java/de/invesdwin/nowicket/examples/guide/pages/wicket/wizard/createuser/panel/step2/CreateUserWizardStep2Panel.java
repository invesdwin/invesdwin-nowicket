package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.step2;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.CreateUserWizardStepBindingInterceptor;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateUserWizardStep2Panel extends Panel {

    public CreateUserWizardStep2Panel(final String id, final IModel<CreateUserWizardStep2> model) {
        super(id, model);
        new GeneratedBinding(this).withBindingInterceptor(new CreateUserWizardStepBindingInterceptor(this)).bind();
    }

}
