package de.invesdwin.nowicket.application.filter.init;

import java.util.ConcurrentModificationException;

import javax.annotation.concurrent.Immutable;

import org.nustaq.serialization.FSTConfiguration;
import org.wicketstuff.pageserializer.fast2.Fast2WicketSerializer;

import de.invesdwin.util.error.Throwables;

@Immutable
public class ConfiguredFast2WicketSerializer extends Fast2WicketSerializer {

    public ConfiguredFast2WicketSerializer() {
        //don't register classes so that application restarts don't cause problems with saved state
        this(getDefaultFSTConfiguration());
    }

    public ConfiguredFast2WicketSerializer(final FSTConfiguration config) {
        super(config);
    }

    @Override
    public byte[] serialize(final Object object) {
        for (int i = 0; i < 10; i++) {
            try {
                return super.serialize(object);
            } catch (final Throwable t) {
                //retry on concurrent modification exception
                if (!Throwables.isCausedByType(t, ConcurrentModificationException.class)) {
                    throw Throwables.propagate(t);
                } else {
                    continue;
                }
            }
        }
        return super.serialize(object);
    }
}