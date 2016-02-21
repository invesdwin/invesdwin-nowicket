package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.invesdwin.nowicket.generated.binding.GeneratedBinding;

@NotThreadSafe
public class CreateUserWizardStepControlPanel extends Panel {

    public CreateUserWizardStepControlPanel(final String id, final IModel<CreateUserWizardStepControl> model) {
        super(id, model);
        new GeneratedBinding(this).bind();
    }

}
