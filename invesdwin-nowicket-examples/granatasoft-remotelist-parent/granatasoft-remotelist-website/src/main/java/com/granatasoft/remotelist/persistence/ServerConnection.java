package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.invesdwin.context.persistence.jpa.api.dao.entity.AEntity;

@Entity
@NotThreadSafe
public class ServerConnection extends AEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Protocol protocol;
    @Column(nullable = false)
    private int port;

    public ServerConnection() {}

    public ServerConnection(final Protocol name, final int port) {
        this.setProtocol(name);
        this.port = port;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(final Protocol name) {
        this.protocol = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

}
