package de.invesdwin.nowicket.examples.guide.pages.wicket.wizard;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.step1.CreateUserWizardStep1;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.steppage.PageWizardStep;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class WizardStart extends AValueObject {

    public PageWizardStep createUserWizardInPage() {
        return new PageWizardStep(new CreateUserWizardStep1(false));
    }

    public void createUserWizardInModal() {
        GuiService.get().showModalPanel(new CreateUserWizardStep1(true));
    }

}
