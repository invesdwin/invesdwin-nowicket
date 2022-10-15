package de.invesdwin.nowicket.examples.isis.page.users.changepassword;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

import org.apache.isis.applib.value.Password;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.examples.isis.integration.AppUserRegistrationService;
import de.invesdwin.nowicket.examples.isis.integration.IsisInjector;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.string.Strings;

@GeneratedMarkup
@NotThreadSafe
public class ChangePassword extends AValueObject {

    @Inject
    private transient AppUserRegistrationService appUserRegistrationService;
    private final String username;
    @NotBlank
    private String newPassword;
    private String newPasswordRepeat;

    public ChangePassword(final String username) {
        this.username = username;
        IsisInjector.inject(this);
    }

    public String getUsername() {
        return username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(final String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    @ModalCloser
    public void ok() throws Exception {
        final ApplicationUser user = appUserRegistrationService.findByUsername(username);
        if (user == null) {
            throw new Exception("User [" + username + "] not found!");
        }
        final String validateResetPassword = user.validateResetPassword(new Password(newPassword),
                new Password(newPasswordRepeat));
        if (Strings.isNotBlank(validateResetPassword)) {
            throw new Exception(validateResetPassword);
        }
        user.resetPassword(new Password(newPassword), new Password(newPasswordRepeat));
        appUserRegistrationService.saveUser(user);
    }

    @Forced
    @ModalCloser
    public void cancel() {}

    private void readObject(final java.io.ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        IsisInjector.inject(this);
    }

}
