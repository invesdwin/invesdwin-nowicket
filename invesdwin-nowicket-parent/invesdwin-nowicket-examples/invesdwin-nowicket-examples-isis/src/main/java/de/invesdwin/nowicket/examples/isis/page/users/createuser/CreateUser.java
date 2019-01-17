package de.invesdwin.nowicket.examples.isis.page.users.createuser;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.apache.isis.applib.services.userreg.UserDetails;

import de.invesdwin.norva.beanpath.annotation.BeanPathRedirect;
import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.examples.isis.integration.IsisInjector;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.lang.Strings;

@GeneratedMarkup
@NotThreadSafe
public class CreateUser extends AValueObject {

    @Inject
    private transient AppUserRegistrationService appUserRegistrationService;

    private final UserDetails userDetails = new UserDetails();

    public CreateUser() {
        IsisInjector.inject(this);
    }

    private void readObject(final java.io.ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        IsisInjector.inject(this);
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @BeanPathRedirect(CreateUserConstants.userDetails_username)
    public String validateUsername(final String newValue) {
        if (Strings.isBlank(newValue)) {
            return "must not be blank";
        }
        if (appUserRegistrationService.usernameExists(newValue)) {
            return "already exists";
        }
        return null;
    }

    @BeanPathRedirect(CreateUserConstants.userDetails_emailAddress)
    public String validateEmail(final String newValue) {
        if (Strings.isBlank(newValue)) {
            return "must not be blank";
        }
        if (!Pattern.matches(".+\\@.+\\..+", newValue)) {
            return "is not valid";
        }
        if (appUserRegistrationService.emailExists(newValue)) {
            return "already exists";
        }
        return null;
    }

    @BeanPathRedirect(CreateUserConstants.userDetails_confirmPassword)
    public String validateConfirmPassword(final String newValue) {
        if (!Objects.equals(userDetails.getPassword(), newValue)) {
            return "does not match";
        }
        return null;
    }

    @ModalCloser
    public void ok() {
        appUserRegistrationService.registerUser(userDetails);
    }

    @Forced
    @ModalCloser
    public void cancel() {}

}
