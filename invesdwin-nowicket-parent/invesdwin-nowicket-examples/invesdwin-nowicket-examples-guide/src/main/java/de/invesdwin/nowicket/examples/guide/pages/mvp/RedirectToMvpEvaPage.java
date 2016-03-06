package de.invesdwin.nowicket.examples.guide.pages.mvp;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.flow.RedirectToUrlException;

import de.invesdwin.nowicket.examples.guide.pages.AExampleWebPage;

@NotThreadSafe
public class RedirectToMvpEvaPage extends AExampleWebPage {

    public RedirectToMvpEvaPage() {
        super(null);
        throw new RedirectToUrlException("http://invesdwin.de/nowicket-examples-mvp-eva/",
                HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

}
