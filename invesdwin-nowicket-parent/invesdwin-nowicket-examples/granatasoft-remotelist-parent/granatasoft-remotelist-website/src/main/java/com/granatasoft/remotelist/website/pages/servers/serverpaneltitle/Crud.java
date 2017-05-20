package com.granatasoft.remotelist.website.pages.servers.serverpaneltitle;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.ServerDao;
import com.granatasoft.remotelist.website.components.confirm.Confirm;
import com.granatasoft.remotelist.website.pages.servers.serverrow.ServerRow;
import com.granatasoft.remotelist.website.panels.server.details.ServerDetails;
import com.granatasoft.remotelist.website.panels.server.edit.EditServer;

import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@Configurable
@GeneratedMarkup
public class Crud extends AValueObject {

    @Inject
    private transient ServerDao serverDao;
    private boolean disableDeleteButton = true;

    private ServerRow serverRow;

    public Crud(final ServerRow serverRow) {
        this.serverRow = serverRow;
    }

    public void detail() {
        GuiService.get().showModalPanel(new ServerDetails(serverDao.findOne(serverRow.getServer())));
    }

    public void edit() {
        GuiService.get().showModalPanel(new EditServer(serverDao.findOne(serverRow.getServer())));
    }

    public void delete() {
        GuiService.get().showModalPanel(new Confirm() {
            @Override
            @ModalCloser
            public void yes() {
                super.yes();
                serverDao.delete(serverRow.getServer());
            }

            @Override
            @ModalCloser
            public void no() {
                super.no();
            }
        });
    }

    public String disableDelete() {

        if (!serverRow.getApplications().isEmpty()) {
            return "You can't delete server if an application is assigned to it.";
        }

        return null;
    }
}
