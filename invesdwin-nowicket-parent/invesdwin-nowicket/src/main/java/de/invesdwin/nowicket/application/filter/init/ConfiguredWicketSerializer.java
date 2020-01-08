package de.invesdwin.nowicket.application.filter.init;

import java.util.ConcurrentModificationException;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.serialize.ISerializer;
import org.nustaq.serialization.simpleapi.DefaultCoder;

import de.invesdwin.util.error.Throwables;
import io.netty.util.concurrent.FastThreadLocal;

@Immutable
public class ConfiguredWicketSerializer implements ISerializer {

    private static final FastThreadLocal<DefaultCoder> CODER = new FastThreadLocal<DefaultCoder>() {
        @Override
        protected DefaultCoder initialValue() throws Exception {
            return new DefaultCoder(true);
        }
    };

    @Override
    public byte[] serialize(final Object object) {
        final DefaultCoder coder = CODER.get();
        for (int i = 0; i < 10; i++) {
            try {
                return coder.toByteArray(object);
            } catch (final Throwable t) {
                //retry on concurrent modification exception
                if (!Throwables.isCausedByType(t, ConcurrentModificationException.class)) {
                    throw Throwables.propagate(t);
                } else {
                    continue;
                }
            }
        }
        return coder.toByteArray(object);
    }

    @Override
    public Object deserialize(final byte[] data) {
        return CODER.get().toObject(data);
    }

}