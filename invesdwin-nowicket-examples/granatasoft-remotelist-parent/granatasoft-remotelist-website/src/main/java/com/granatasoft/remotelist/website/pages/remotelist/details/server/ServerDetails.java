package com.granatasoft.remotelist.website.pages.remotelist.details.server;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.persistence.Server;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class ServerDetails extends AValueObject {

    private final String name;
    private final String ipv4;
    private final String ipv6;

    public ServerDetails(final Server server) {
        this.name = server.getName();
        this.ipv4 = server.getIpv4();
        this.ipv6 = server.getIpv6();
    }

    public String getName() {
        return name;
    }

    public String getIpv4() {
        return ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }
}
