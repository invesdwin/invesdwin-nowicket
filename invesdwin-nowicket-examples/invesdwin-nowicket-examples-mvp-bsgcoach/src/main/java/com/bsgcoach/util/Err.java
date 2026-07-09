package com.bsgcoach.util;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class Err {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(Err.class);

    private Err() {}

    public static RuntimeException process(final Throwable t) {
        LOG.catching(t);
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else {
            return new RuntimeException(t);
        }

    }

}
