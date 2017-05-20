package com.granatasoft.remotelist.website.panels.server.details;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.persistence.Server;

import de.invesdwin.nowicket.generated.binding.annotation.Forced;
import de.invesdwin.nowicket.generated.binding.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class ServerDetails extends AValueObject {

    private final Server server;

    public ServerDetails(final Server server) {
        this.server = server;
    }

    public String getName() {
        return server.getName();
    }

    public String getIpv4() {
        return server.getIpv4();
    }

    public String getIpv6() {
        return server.getIpv6();
    }

    public String getDescription() {
        return server.getDescription();
    }

    @Forced
    @ModalCloser
    public void close() {}
}
