package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.WizardStart;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step1.CreateUserWizardStep1;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.steppage.PageWizardStep;
import de.invesdwin.nowicket.generated.binding.annotation.Forced;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class CreateUserWizardStepControl extends AValueObject {

    private final ICreateUserWizardStep previous;
    private final boolean isModal;
    private ICreateUserWizardStep next;
    private Object finish;

    public CreateUserWizardStepControl(final ICreateUserWizardStep previous, final boolean isModal) {
        this.previous = previous;
        this.isModal = isModal;
    }

    @Hidden(skip = true)
    public ICreateUserWizardStep getPrevious() {
        return previous;
    }

    @Forced
    public void previous() {
        redirect(previous);
    }

    public boolean disablePrevious() {
        return previous == null;
    }

    private void redirect(final ICreateUserWizardStep model) {
        if (isModal) {
            GuiService.get().hideModalPanel();
            GuiService.get().showModalPanel(model);
        } else {
            GuiService.get().showPage(new PageWizardStep(model));
        }
    }

    public void next() {
        redirect(next);
    }

    public boolean disableNext() {
        return next == null;
    }

    @Hidden(skip = true)
    public void setNext(final ICreateUserWizardStep next) {
        this.next = next;
    }

    @Forced
    public void cancel() {
        if (isModal) {
            GuiService.get().hideModalPanel();
        } else {
            GuiService.get().showPage(new WizardStart());
        }
    }

    public void finish() {
        if (isModal) {
            GuiService.get().hideModalPanel();
        } else {
            GuiService.get().showPage(finish);
        }
    }

    public boolean disableFinish() {
        return finish == null;
    }

    @Hidden(skip = true)
    public void setFinish(final Object finish) {
        this.finish = finish;
    }

    @Hidden(skip = true)
    public boolean isModal() {
        return isModal;
    }

    @Hidden(skip = true)
    public String getUsernameFromStep1() {
        ICreateUserWizardStep step = previous;
        while (step != null) {
            if (step instanceof CreateUserWizardStep1) {
                final CreateUserWizardStep1 step1 = (CreateUserWizardStep1) step;
                return step1.getUsername();
            }
            step = step.getControl().getPrevious();
        }
        throw new IllegalStateException(CreateUserWizardStep1.class.getSimpleName() + " not found in previous steps!");
    }

}
