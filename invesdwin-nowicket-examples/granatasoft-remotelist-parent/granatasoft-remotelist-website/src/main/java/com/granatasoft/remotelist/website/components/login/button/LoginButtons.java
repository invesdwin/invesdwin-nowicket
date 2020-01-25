package com.granatasoft.remotelist.website.components.login.button;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.persistence.ServerConnection;
import com.granatasoft.remotelist.website.components.login.button.child.LoginButtonsChild;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class LoginButtons extends AValueObject {

    private final List<LoginButtonsChild> childButtons;
    private LoginButtonsChild masterButton;

    public LoginButtons(final ApplicationInstance applicationInstance) {
        if (applicationInstance.getUrl() != null) {
            masterButton = new LoginButtonsChild(applicationInstance);
        }
        childButtons = new ArrayList<LoginButtonsChild>();
        for (final Server server : applicationInstance.getServers()) {
            for (final ServerConnection connection : server.getConnections()) {
                if (masterButton == null) {
                    masterButton = new LoginButtonsChild(server, connection.getProtocol());
                } else {
                    childButtons.add(new LoginButtonsChild(server, connection.getProtocol()));
                }
            }
        }
    }

    public LoginButtons(final Server server) {
        if (server.getConnections().size() > 1) {
            childButtons = new ArrayList<LoginButtonsChild>();
        } else {
            childButtons = null;
        }

        for (final ServerConnection connection : server.getConnections()) {
            if (masterButton == null) {
                masterButton = new LoginButtonsChild(server, connection.getProtocol());
            } else {
                childButtons.add(new LoginButtonsChild(server, connection.getProtocol()));
            }
        }

    }

    @Hidden
    public LoginButtonsChild getMasterButton() {
        return masterButton;
    }

    public boolean hideMasterButton() {
        return masterButton == null;
    }

    public boolean hideChildButtons() {
        return childButtons == null || childButtons.isEmpty();
    }

    public List<LoginButtonsChild> getChildButtons() {
        return childButtons;
    }
}
