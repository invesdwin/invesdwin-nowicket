package de.invesdwin.nowicket.component;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.util.resource.AbstractResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

@NotThreadSafe
public class InputStreamResourceStream extends AbstractResourceStream {
    private final InputStream inputStream;

    public InputStreamResourceStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() throws ResourceStreamNotFoundException {
        return inputStream;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

}