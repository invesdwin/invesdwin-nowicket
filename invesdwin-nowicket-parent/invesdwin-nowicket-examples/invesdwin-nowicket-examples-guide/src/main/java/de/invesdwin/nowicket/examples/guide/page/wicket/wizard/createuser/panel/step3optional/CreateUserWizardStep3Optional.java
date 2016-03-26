package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step3optional;

import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.BeanPathEndPoint;
import de.invesdwin.norva.beanpath.annotation.BeanPathRedirect;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.domain.RoleSet;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.domain.User;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.CreateUserWizardStepControl;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.ICreateUserWizardStep;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step2.CreateUserWizardStep2;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel.step4.CreateUserWizardStep4;
import de.invesdwin.nowicket.examples.guide.pages.wicket.wizard.createuser.panel.step3optional.CreateUserWizardStep3OptionalConstants;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Strings;

@GeneratedMarkup
@NotThreadSafe
public class CreateUserWizardStep3Optional extends AValueObject implements ICreateUserWizardStep {

    private final CreateUserWizardStepControl control;
    private RoleSet roleSet = new RoleSet();

    public CreateUserWizardStep3Optional(final CreateUserWizardStep2 previous) {
        this.control = new CreateUserWizardStepControl(previous, previous.getControl().isModal());
        final CreateUserWizardStep4 next = new CreateUserWizardStep4(this);
        control.setNext(next);
    }

    public RoleSet getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(final RoleSet roleSet) {
        this.roleSet = roleSet;
    }

    /**
     * Here we redirect the bean path to add the choices at the proper nested place from the outside.
     */
    @BeanPathRedirect(CreateUserWizardStep3OptionalConstants.roleSet_roles)
    public List<String> getRolesChoice() {
        return Arrays.asList("overlord", "ordinary mortal", "blabbermouth", "funny person", "spider web surfer (???)");
    }

    /**
     * And here we add a custom validation for the name
     */
    @BeanPathRedirect(CreateUserWizardStep3OptionalConstants.roleSet_name)
    public String validateName(final String newValue) {
        if (roleSet.getRoles() != null && !roleSet.getRoles().isEmpty() && Strings.isBlank(newValue)) {
            return "is required if you select roles for this user. You also have to provide a name for the role set";
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
        //since we reuse the domain type for the UI, nothing much to do here
        user.setRoleSet((RoleSet) roleSet.clone());
    }

    public String title() {
        return getHeader();
    }

    public boolean hideHeader() {
        return control.isModal();
    }

    public String getHeader() {
        return "User Roles";
    }

    public String getSubtitle() {
        return "Select the roles that apply for '" + control.getUsernameFromStep1() + "'.";
    }

}
