package de.invesdwin.nowicket.generated.markup.processor.element;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

import de.invesdwin.norva.beanpath.spi.element.IActionBeanPathElement;

@Immutable
public enum AnchorType {
    URL,
    FILE,
    RESOURCE,
    RESOURCE_REFERENCE;

    public static AnchorType valueOf(final IActionBeanPathElement element) {
        if (element.getAccessor().getRawType().isInstanceOf(URL.class)
                || element.getAccessor().getRawType().isInstanceOf(URI.class)
                || element.getAccessor().getRawType().isInstanceOf(Url.class)
                || element.getAccessor().getRawType().isInstanceOf(String.class)) {
            return URL;
        } else if (element.getAccessor().getRawType().isInstanceOf(File.class)) {
            return FILE;
        } else if (element.getAccessor().getRawType().isInstanceOf(IResource.class)) {
            return RESOURCE;
        } else if (element.getAccessor().getRawType().isInstanceOf(ResourceReference.class)) {
            return RESOURCE_REFERENCE;
        } else {
            return null;
        }
    }
}
