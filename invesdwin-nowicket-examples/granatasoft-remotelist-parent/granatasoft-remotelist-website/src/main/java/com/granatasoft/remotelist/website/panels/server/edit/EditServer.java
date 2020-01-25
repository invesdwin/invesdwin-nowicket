package com.granatasoft.remotelist.website.panels.server.edit;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.persistence.ServerDao;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@Configurable
@GeneratedMarkup
public class EditServer extends AValueObject {

    private final Server server;
    @Inject
    private transient ServerDao serverDao;

    public EditServer(final Server server) {
        this.server = server;
    }

    public String getName() {
        return server.getName();
    }

    public void setName(final String name) {
        server.setName(name);
    }

    public String getIpv4() {
        return server.getIpv4();
    }

    public void setIpv4(final String ipv4) {
        server.setIpv4(ipv4);
    }

    public String getIpv6() {
        return server.getIpv6();
    }

    public void setIpv6(final String ipv6) {
        server.setIpv6(ipv6);
    }

    public String getDescription() {
        return server.getDescription();
    }

    public void setDescription(final String description) {
        server.setDescription(description);
    }

    @Forced
    @ModalCloser
    public void close() {}

    @Forced
    @ModalCloser
    public void save() {
        serverDao.save(server);
    }
}
