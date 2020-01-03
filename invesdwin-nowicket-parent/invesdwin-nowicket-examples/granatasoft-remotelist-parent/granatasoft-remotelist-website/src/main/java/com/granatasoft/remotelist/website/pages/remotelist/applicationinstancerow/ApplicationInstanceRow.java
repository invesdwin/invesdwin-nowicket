package com.granatasoft.remotelist.website.pages.remotelist.applicationinstancerow;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.IResource;
import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.ApplicationInstanceDao;
import com.granatasoft.remotelist.website.components.confirm.Confirm;
import com.granatasoft.remotelist.website.components.login.button.LoginButtons;
import com.granatasoft.remotelist.website.pages.remotelist.details.ShowApplicationInstanceDetails;
import com.granatasoft.remotelist.website.panels.applicationinstance.update.UpdateApplicationInstance;

import de.invesdwin.norva.beanpath.annotation.ColumnOrder;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.application.auth.Roles;
import de.invesdwin.nowicket.component.modal.ModalConfig;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@ColumnOrder({ ApplicationInstanceRowConstants.applicationLogo, ApplicationInstanceRowConstants.application,
        ApplicationInstanceRowConstants.login, ApplicationInstanceRowConstants.details,
        ApplicationInstanceRowConstants.edit, ApplicationInstanceRowConstants.delete })
@Configurable
public class ApplicationInstanceRow extends AValueObject {

    private final ApplicationInstance applicationInstance;
    @Inject
    private transient ApplicationInstanceDao applicationInstanceDao;

    public ApplicationInstanceRow(final ApplicationInstance applicationInstance) {
        this.applicationInstance = applicationInstance;
    }

    public IResource getApplicationLogo() {
        if (applicationInstance.getApplication().getLogo() != null) {
            return new ByteArrayResource(null, applicationInstance.getApplication().getLogo(),
                    applicationInstance.getApplication().getLogoFileName());
        }

        return null;
    }

    public String getApplication() {
        return applicationInstance.toString();
    }

    public LoginButtons getLogin() {
        return new LoginButtons(this.applicationInstance);
    }

    public void details() {
        GuiService.get().showModalPanel(new ShowApplicationInstanceDetails(this.applicationInstance));
    }

    public void edit() {
        GuiService.get().showModalPanel(new UpdateApplicationInstance(this.applicationInstance));
    }

    public boolean hideEdit() {
        return !Roles.get().hasRole(Roles.ADMIN);
    }

    public void delete() {

        GuiService.get().showModalPanel(new Confirm() {
            @Override
            @ModalCloser
            public void yes() {
                applicationInstanceDao.delete(applicationInstance);
            }
        }, new ModalConfig(350, 150));
    }

    public boolean hideDelete() {
        return !Roles.get().hasRole(Roles.ADMIN);
    }

    @Override
    public int compareTo(final Object o) {
        if (o instanceof ApplicationInstanceRow) {
            final ApplicationInstanceRow cO = (ApplicationInstanceRow) o;
            return getApplication().compareTo(cO.getApplication());
        } else {
            return 1;
        }
    }

}
