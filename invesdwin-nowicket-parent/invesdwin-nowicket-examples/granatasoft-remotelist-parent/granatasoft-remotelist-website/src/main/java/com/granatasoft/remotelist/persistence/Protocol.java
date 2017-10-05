package com.granatasoft.remotelist.persistence;

import javax.annotation.concurrent.Immutable;

import com.granatasoft.remotelist.utils.config.IRemoteConfig;
import com.granatasoft.remotelist.utils.config.RdpConfig;

@Immutable
public enum Protocol {
    RDP {
        @Override
        public IRemoteConfig getConfig(final Server server) {
            return new RdpConfig(server.getIpv4());
        }
    },
    VNC {
        @Override
        public IRemoteConfig getConfig(final Server server) {
            return new RdpConfig(server.getIpv4());
        }
    },
    SSH {
        @Override
        public IRemoteConfig getConfig(final Server server) {
            return new RdpConfig(server.getIpv4());
        }
    };

    public abstract IRemoteConfig getConfig(Server server);
}
