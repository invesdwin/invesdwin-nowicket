package de.invesdwin.nowicket.examples.guide.page.mvp;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.PackageResourceReference;
import org.wicketstuff.annotation.mount.MountPath;

import de.invesdwin.nowicket.examples.guide.page.AExampleWebPage;

@MountPath("eva")
@NotThreadSafe
public class RedirectToMvpEvaPage extends AExampleWebPage {

    public static final PackageResourceReference ICON = new PackageResourceReference(RedirectToMvpBsgcoachPage.class,
            "eva.ico");

    public RedirectToMvpEvaPage() {
        super(null);
    }

}
