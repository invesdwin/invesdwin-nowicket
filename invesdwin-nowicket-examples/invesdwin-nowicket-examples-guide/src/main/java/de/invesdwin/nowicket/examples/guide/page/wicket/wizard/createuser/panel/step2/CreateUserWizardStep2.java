package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step2;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotBlank;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;
import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.domain.User;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.CreateUserWizardStepControl;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.ICreateUserWizardStep;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step1.CreateUserWizardStep1;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step3optional.CreateUserWizardStep3Optional;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step4.CreateUserWizardStep4;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class CreateUserWizardStep2 extends AValueObject implements ICreateUserWizardStep {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String department;
    private boolean assignRoles;
    private final CreateUserWizardStepControl control;

    public CreateUserWizardStep2(final CreateUserWizardStep1 previous) {
        this.control = new CreateUserWizardStepControl(previous, previous.getControl().isModal());
        //set the next model initially
        setAssignRoles(false);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public boolean isAssignRoles() {
        return assignRoles;
    }

    /**
     * Call this method eagerly, thus on value change, to update the next step. Instead of waiting for a submit button
     * press.
     * 
     * Instead this could also have been made lazy, by just providind the control a callback method to retrieve the next
     * step on click of the next button. But making it eager is a simpler solution.
     */
    @Eager
    public void setAssignRoles(final boolean assignRoles) {
        this.assignRoles = assignRoles;
        if (assignRoles) {
            control.setNext(new CreateUserWizardStep3Optional(this));
        } else {
            control.setNext(new CreateUserWizardStep4(this));
        }
    }

    @Override
    @BeanPathEndPoint
    public CreateUserWizardStepControl getControl() {
        return control;
    }

    @Hidden(skip = true)
    @Override
    public void fillUserDetails(final User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDepartment(department);
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
        return "Provide the details of new user '" + control.getUsernameFromStep1() + "'.";
    }

}
