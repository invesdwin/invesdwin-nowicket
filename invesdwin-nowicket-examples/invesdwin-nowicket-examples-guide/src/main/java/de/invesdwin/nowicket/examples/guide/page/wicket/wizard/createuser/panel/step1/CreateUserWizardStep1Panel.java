package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step1;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.CreateUserWizardStepBindingInterceptor;
import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateUserWizardStep1Panel extends Panel {

    public CreateUserWizardStep1Panel(final String id, final IModel<CreateUserWizardStep1> model) {
        super(id, model);
        new GeneratedBinding(this).addBindingInterceptor(new CreateUserWizardStepBindingInterceptor(this)).bind();
    }

}
