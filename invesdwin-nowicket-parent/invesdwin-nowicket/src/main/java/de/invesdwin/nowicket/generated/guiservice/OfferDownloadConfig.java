package de.invesdwin.nowicket.generated.guiservice;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

import de.invesdwin.nowicket.component.InputStreamResourceStream;

@NotThreadSafe
public class OfferDownloadConfig implements Serializable {

    private final IResourceStream resourceStream;
    private final String fileName;

    public OfferDownloadConfig(final IResourceStream resourceStream, final String fileName) {
        this.resourceStream = resourceStream;
        this.fileName = fileName;
    }

    public OfferDownloadConfig(final InputStream stream, final String fileName) {
        this(new InputStreamResourceStream(stream), fileName);
    }

    public OfferDownloadConfig(final File file) {
        this(new FileResourceStream(file), file.getName());
    }

    public IResourceStream getResourceStream() {
        return resourceStream;
    }

    public String getFileName() {
        return fileName;
    }

}
