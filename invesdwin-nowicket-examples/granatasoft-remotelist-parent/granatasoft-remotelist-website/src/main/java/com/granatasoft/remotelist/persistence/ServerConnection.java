package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.persistence.jpa.api.dao.entity.AEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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
