package com.granatasoft.remotelist.persistence;

import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.persistence.jpa.api.dao.entity.AEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
@NotThreadSafe
public class Server extends AEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ipv4;
    private String ipv6;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ServerConnection> connections;

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

    public List<ServerConnection> getConnections() {
        return connections;
    }

    public void setConnections(final List<ServerConnection> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
