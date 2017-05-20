package com.granatasoft.remotelist.website.components.login.button.child;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.Protocol;
import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.website.components.login.button.child.dialog.resource.LoginDialog;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class LoginButtonsChild extends AValueObject {

    private final ApplicationInstance applicationInstance;
    private final Server server;
    private final Protocol protocol;

    public LoginButtonsChild(final ApplicationInstance applicationInstance) {
        this.protocol = null;
        this.server = null;
        this.applicationInstance = applicationInstance;
    }

    public LoginButtonsChild(final Server server, final Protocol protocol) {
        this.protocol = protocol;
        this.server = server;
        this.applicationInstance = null;
    }

    public void openDialogButton() {
        if (protocol != null) {
            GuiService.get().showModalPanel(new LoginDialog(server, protocol));
        } else {
            GuiService.get().showModalPanel(new LoginDialog(applicationInstance));
        }
    }

    public String openDialogButtonTitle() {
        if (protocol != null) {
            return server.getName() + " (" + protocol + ")";
        }

        return "Web Login";
    }
}
