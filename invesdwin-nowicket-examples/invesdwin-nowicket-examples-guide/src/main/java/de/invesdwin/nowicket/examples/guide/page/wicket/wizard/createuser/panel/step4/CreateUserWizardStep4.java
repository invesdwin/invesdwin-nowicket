package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step4;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.WizardStart;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.domain.User;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.CreateUserWizardStepControl;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.ICreateUserWizardStep;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.string.Strings;

@GeneratedMarkup
@NotThreadSafe
public class CreateUserWizardStep4 extends AValueObject implements ICreateUserWizardStep {

    private final CreateUserWizardStepControl control;

    public CreateUserWizardStep4(final ICreateUserWizardStep previous) {
        this.control = new CreateUserWizardStepControl(previous, previous.getControl().isModal()) {
            @Override
            public void finish() {
                super.finish();
                GuiService.get().showStatusMessage("User created successfully", createUser().toStringMultiline());
            }
        };
        control.setFinish(new WizardStart());
    }

    @BeanPathEndPoint
    @Override
    public CreateUserWizardStepControl getControl() {
        return control;
    }

    @Hidden(skip = true)
    @Override
    public void fillUserDetails(final User user) {
        //nothing to do here
    }

    /**
     * We could also have dragged the user object along the steps, instead of constructing it at the end. But we want to
     * show that your are flexible in your approach here.
     * 
     * If we wanted it the traditional way, we could have used org.apache.wicket.extensions.wizard..., but then we could
     * not have demonstrated that you can actually rebuilt that functionality with a lot less code using this framework.
     * :)
     */
    private User createUser() {
        final User user = new User();
        ICreateUserWizardStep previousPreviousStep = this;
        while (previousPreviousStep != null) {
            previousPreviousStep.fillUserDetails(user);
            previousPreviousStep = previousPreviousStep.getControl().getPrevious();
        }
        return user;
    }

    public String title() {
        return getHeader();
    }

    public boolean hideHeader() {
        return control.isModal();
    }

    public String getHeader() {
        return "Confirmation";
    }

    public String getSubtitle() {
        return "Please confirm creating '" + control.getUsernameFromStep1() + "'.";
    }

    public String getMessage() {
        final User user = createUser();
        return "You are about to create user '" + Strings.asStringEmptyText(user.getFirstName()) + " "
                + Strings.asStringEmptyText(user.getLastName()) + "', for department '"
                + Strings.asStringEmptyText(user.getDepartment()) + "' and user name '"
                + Strings.asStringEmptyText(user.getUsername())
                + "'. Are you sure you want to do this? I mean, do you <b>really</b> want to allow '"
                + Strings.asStringEmptyText(user.getUsername())
                + "' on this system? If you are, please press finish to complete.";
    }

}
