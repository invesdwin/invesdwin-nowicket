package com.granatasoft.remotelist.website.pages.servers.instancerow;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.concurrent.NotThreadSafe;
import jakarta.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.ApplicationInstanceDao;
import com.granatasoft.remotelist.website.components.confirm.Confirm;
import com.granatasoft.remotelist.website.panels.applicationinstance.update.UpdateApplicationInstance;

import de.invesdwin.norva.beanpath.annotation.ColumnOrder;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.uri.URIs;

@NotThreadSafe
@Configurable
@ColumnOrder({ ApplicationInstanceRowConstants.category, ApplicationInstanceRowConstants.environment,
    ApplicationInstanceRowConstants.applicationName, ApplicationInstanceRowConstants.version,
    ApplicationInstanceRowConstants.url, ApplicationInstanceRowConstants.edit,
    ApplicationInstanceRowConstants.delete })
public class ApplicationInstanceRow extends AValueObject {

    private final ApplicationInstance applicationInstance;
    @Inject
    private transient ApplicationInstanceDao applicationInstanceDao;

    public ApplicationInstanceRow(final ApplicationInstance applicationInstance) {
        this.applicationInstance = applicationInstance;
    }

    public Long getVersion() {
        return applicationInstance.getVersion();
    }

    public String getApplicationName() {
        return applicationInstance.getApplication().getName();
    }

    public String getCategory() {
        return applicationInstance.getCategory().getName();
    }

    public String getEnvironment() {
        return applicationInstance.getEnvironment().name();
    }

    public URI getUrl() throws URISyntaxException {
        return URIs.asUri(applicationInstance.getUrl());
    }

    public void edit() {
        GuiService.get().showModalPanel(new UpdateApplicationInstance(applicationInstance));
    }

    public void delete() {
        GuiService.get().showModalPanel(new Confirm() {
            @Override
            @ModalCloser
            public void yes() {
                super.yes();
                applicationInstanceDao.delete(applicationInstance);
            }

            @Override
            @ModalCloser
            public void no() {
                super.no();
            }
        });
    }
}
