package com.granatasoft.remotelist.website.pages.remotelist.details;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.persistence.ApplicationInstance;
import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.website.pages.remotelist.details.server.ServerDetails;

import de.invesdwin.nowicket.generated.binding.annotation.Forced;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class ShowApplicationInstanceDetails extends AValueObject {

    private final ApplicationInstance applicationInstance;

    public ShowApplicationInstanceDetails(final ApplicationInstance applicationInstance) {
        this.applicationInstance = applicationInstance;
    }

    public List<ServerDetails> getServers() {
        final List<ServerDetails> serverDetials = new ArrayList<ServerDetails>();
        for (final Server server : this.applicationInstance.getServers()) {
            serverDetials.add(new ServerDetails(server));
        }
        return serverDetials;
    }

    @ModalCloser
    @Forced
    public void close() {}
}
