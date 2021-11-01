package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step4;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.CreateUserWizardStepBindingInterceptor;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateUserWizardStep4Panel extends Panel {

    public CreateUserWizardStep4Panel(final String id, final IModel<CreateUserWizardStep4> model) {
        super(id, model);
        new GeneratedBinding(this).addBindingInterceptor(new CreateUserWizardStepBindingInterceptor(this)).bind();
    }

}
