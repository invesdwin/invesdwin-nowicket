package de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.panel;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.examples.guide.page.wicket.wizard.createuser.domain.User;

public interface ICreateUserWizardStep {

    /**
     * Annotations from interfaces or abstract base classes do not get applied on overriden methods if they are missing
     * those annotations. This is due to giving the implementation a higher priority over the annotations in order to
     * also be able to exclude ones that are not wanted. Thus you need to be careful to add these annotations in the
     * implementation classes, they are only an example here without actually being used.
     */
    @Hidden(skip = true /* if annotation would be applied, we could not bind the property in our interceptor */)
    CreateUserWizardStepControl getControl();

    @Hidden(skip = true)
    void fillUserDetails(User user);

}
