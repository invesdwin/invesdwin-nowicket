package com.bsgcoach.web;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.flow.RedirectToUrlException;

@NotThreadSafe
public class RedirectToGuidePage extends ABsgCoachWebPage {

    public RedirectToGuidePage() {
        super(null);
        throw new RedirectToUrlException("http://invesdwin.de/nowicket/", HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

}
