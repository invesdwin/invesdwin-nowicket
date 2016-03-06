package de.invesdwin.nowicket.examples.guide.pages.mvp;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;

@NotThreadSafe
public class RedirectToMvpBsgcoachPage extends AExampleWebPage {

    public static final PackageResourceReference ICON = new PackageResourceReference(RedirectToMvpBsgcoachPage.class,
            "bsgcoach.ico");

    public RedirectToMvpBsgcoachPage() {
        super(null);
        throw new RedirectToUrlException("http://invesdwin.de/nowicket-examples-mvp-bsgcoach/",
                HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

}
