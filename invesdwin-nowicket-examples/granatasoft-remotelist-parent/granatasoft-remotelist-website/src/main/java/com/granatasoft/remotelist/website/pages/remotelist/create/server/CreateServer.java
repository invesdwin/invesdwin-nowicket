package com.granatasoft.remotelist.website.pages.remotelist.create.server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

import com.granatasoft.remotelist.persistence.Protocol;
import com.granatasoft.remotelist.persistence.Server;
import com.granatasoft.remotelist.persistence.ServerConnection;
import com.granatasoft.remotelist.persistence.ServerDao;
import com.granatasoft.remotelist.website.pages.remotelist.create.server.connection.CreateConnection;

import de.invesdwin.norva.beanpath.annotation.Forced;
import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.norva.beanpath.annotation.ModalCloser;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.Arrays;

@NotThreadSafe
@GeneratedMarkup
@Configurable
public class CreateServer extends AValueObject {

    private final List<CreateConnection> connections;
    @NotNull
    private String name;
    @NotNull
    private String ipv4;
    private String ipv6;
    private Server createdServer;

    @Inject
    private transient ServerDao serverDao;

    public CreateServer() {
        connections = Arrays.asList(new CreateConnection(Protocol.RDP, 3389), new CreateConnection(Protocol.VNC, 5900),
                new CreateConnection(Protocol.SSH, 22));
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(final String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(final String ipv6) {
        this.ipv6 = ipv6;
    }

    public List<CreateConnection> getConnections() {
        return connections;
    }

    @Hidden
    public Server getCreatedServer() {
        return createdServer;
    }

    @ModalCloser
    public void create() {
        createdServer = new Server();
        final List<ServerConnection> tmpConnections = new ArrayList<ServerConnection>();
        createdServer.setName(name);
        createdServer.setIpv4(ipv4);
        for (final CreateConnection connection : connections) {
            if (connection.isActive()) {
                tmpConnections.add(new ServerConnection(connection.getProtocol(), connection.getPort()));
            }
        }
        createdServer.setConnections(tmpConnections);

        this.serverDao.save(createdServer);
    }

    @Forced
    @ModalCloser
    public void close() {
    }

}
