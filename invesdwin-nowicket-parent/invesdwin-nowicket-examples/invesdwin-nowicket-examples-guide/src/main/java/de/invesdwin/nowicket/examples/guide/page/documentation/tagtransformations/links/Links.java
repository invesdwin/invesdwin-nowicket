package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.links;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.core.io.ClassPathResource;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.uri.URIs;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class Links extends AValueObject {

    private static final String IMAGE_FILE_NAME = "image.jpg";
    private static final ResourceReference IMAGE_AS_RESOURCE_REFERENCE = new PackageResourceReference(Links.class,
            IMAGE_FILE_NAME);
    private File imageAsFile;

    private File newImageAsFile() {
        if (imageAsFile == null) {
            //not able to do this in static intializer, since GuiService is not available without wicket application during markup generation
            imageAsFile = new File(GuiService.get().getSessionFolder(), Links.class.getName() + "/" + IMAGE_FILE_NAME);
            try {
                final InputStream in = new ClassPathResource(IMAGE_FILE_NAME, Links.class).getInputStream();
                FileUtils.copyInputStreamToFile(in, imageAsFile);
                in.close();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imageAsFile;
    }

    //gets converted to a relative PackageResourceReference automatically if it is not an absolute URL
    public String string() {
        return IMAGE_FILE_NAME;
    }

    public String stringAsImg() {
        return string();
    }

    public String stringAsIFrame() {
        return string();
    }

    public Url wicketUrl() {
        return RequestCycle.get().mapUrlFor(IMAGE_AS_RESOURCE_REFERENCE, null);
    }

    public Url wicketUrlAsImg() {
        return wicketUrl();
    }

    public Url wicketUrlAsIFrame() {
        return wicketUrl();
    }

    public URL url() {
        //always use relative urls: http://stackoverflow.com/questions/3665484/how-to-get-url-using-relative-path
        return URIs.asUrl("http:" + wicketUrl().toString());
    }

    public URL urlAsImg() {
        return url();
    }

    public URL urlAsIFrame() {
        return url();
    }

    public URI uri() {
        return URIs.asUri(url());
    }

    public URI uriAsImg() {
        return uri();
    }

    public URI uriAsIFrame() {
        return uri();
    }

    public File file() {
        return newImageAsFile();
    }

    public File fileAsImg() {
        return file();
    }

    public File fileAsIFrame() {
        return file();
    }

    public IResource resource() {
        return IMAGE_AS_RESOURCE_REFERENCE.getResource();
    }

    public IResource resourceAsImg() {
        return resource();
    }

    public IResource resourceAsIFrame() {
        return resource();
    }

    public ResourceReference resourceReference() {
        return IMAGE_AS_RESOURCE_REFERENCE;
    }

    public ResourceReference resourceReferenceAsImg() {
        return resourceReference();
    }

    public ResourceReference resourceReferenceAsIFrame() {
        return resourceReference();
    }

}
