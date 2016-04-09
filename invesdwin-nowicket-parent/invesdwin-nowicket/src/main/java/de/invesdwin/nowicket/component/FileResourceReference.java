package de.invesdwin.nowicket.component;

import java.io.File;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

@NotThreadSafe
public class FileResourceReference extends ResourceReference {

    private final File file;

    public FileResourceReference(final File file) {
        super(file.getPath());
        this.file = file;
    }

    @Override
    public IResource getResource() {
        return new FileResource(file);
    }

}
