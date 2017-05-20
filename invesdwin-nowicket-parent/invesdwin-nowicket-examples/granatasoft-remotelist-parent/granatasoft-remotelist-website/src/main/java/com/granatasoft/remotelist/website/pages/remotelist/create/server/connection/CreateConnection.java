package com.granatasoft.remotelist.website.pages.remotelist.create.server.connection;

import javax.annotation.concurrent.NotThreadSafe;

import com.granatasoft.remotelist.persistence.Protocol;

import de.invesdwin.norva.beanpath.annotation.Hidden;
import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class CreateConnection extends AValueObject {
    private final Protocol protocol;
    private boolean isActive = false;
    private int port;

    public CreateConnection(final Protocol protocol, final int port) {
        this.protocol = protocol;
        this.port = port;
    }

    public boolean isActive() {
        return isActive;
    }

    @Eager
    public void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    public String getActiveTitle() {
        return this.protocol.toString();
    }

    @Hidden
    public Protocol getProtocol() {
        return this.protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public String disablePort() {
        if (!isActive) {
            return "not active";
        }
        return null;
    }
}
