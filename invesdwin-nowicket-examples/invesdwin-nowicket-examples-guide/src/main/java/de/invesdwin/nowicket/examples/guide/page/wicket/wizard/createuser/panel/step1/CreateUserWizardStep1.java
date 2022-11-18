package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step1;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.domain.User;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.CreateUserWizardStepControl;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.ICreateUserWizardStep;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step2.CreateUserWizardStep2;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Objects;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@GeneratedMarkup
@NotThreadSafe
public class CreateUserWizardStep1 extends AValueObject implements ICreateUserWizardStep {

    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String emailAddress;
    @NotBlank
    @Email
    private String emailAddressConfirm;

    private final CreateUserWizardStepControl control;

    public CreateUserWizardStep1(final boolean isModal) {
        control = new CreateUserWizardStepControl(null, isModal);
        final CreateUserWizardStep2 next = new CreateUserWizardStep2(this);
        control.setNext(next);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddressConfirm() {
        return emailAddressConfirm;
    }

    public void setEmailAddressConfirm(final String emailAddressConfirm) {
        this.emailAddressConfirm = emailAddressConfirm;
    }

    public String validateEmailAddressConfirm(final String newValue) {
        if (!Objects.equals(emailAddress, newValue)) {
            return "does not match the first email address";
        }
        return null;
    }

    @Override
    @BeanPathEndPoint
    public CreateUserWizardStepControl getControl() {
        return control;
    }

    @Hidden(skip = true)
    @Override
    public void fillUserDetails(final User user) {
        user.setUsername(username);
        user.setEmailAddress(emailAddress);
    }

    /**
     * Utility method for title for the modal, also works for page titles
     */
    public String title() {
        return getHeader();
    }

    public boolean hideHeader() {
        return control.isModal();
    }

    /**
     * Header for the page, hidden when in modal
     */
    public String getHeader() {
        return "User Name";
    }

    public String getSubtitle() {
        return "Create a new user by providing a user name and an email address.";
    }

}
