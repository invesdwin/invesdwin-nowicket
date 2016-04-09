package de.invesdwin.nowicket.component;

import java.io.File;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceStreamResource;
import org.apache.wicket.util.resource.FileResourceStream;

@NotThreadSafe
public class FileResource implements IResource {

    private final File file;

    public FileResource(final File file) {
        this.file = file;
    }

    @Override
    public void respond(final Attributes attributes) {
        final FileResourceStream fileResourceStream = new FileResourceStream(file);
        final ResourceStreamResource resource = new ResourceStreamResource(fileResourceStream);
        resource.respond(attributes);
    }
}
