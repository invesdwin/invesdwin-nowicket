package de.invesdwin.nowicket.examples.guide.component.nyancat;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.PackageResourceReference;

@NotThreadSafe
public class NyancatHtmlResourceReference extends PackageResourceReference {

    public static final NyancatHtmlResourceReference INSTANCE = new NyancatHtmlResourceReference();

    public NyancatHtmlResourceReference() {
        super(NyancatHtmlResourceReference.class, "nyancat.html");
    }

}
