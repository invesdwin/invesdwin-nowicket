package com.granatasoft.remotelist.utils.config;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class RdpConfigTest {

    private RdpConfig rdpConfig;

    @BeforeEach
    public void startup() {
        rdpConfig = new RdpConfig("192.168.0.1", "dummy");
    }

    @Test
    public void assertThatRdpConfigIsSerializedAsJSON() {
        final String json = rdpConfig.toJSON();
        Assertions.assertThat(json).isNotNull();
    }
}