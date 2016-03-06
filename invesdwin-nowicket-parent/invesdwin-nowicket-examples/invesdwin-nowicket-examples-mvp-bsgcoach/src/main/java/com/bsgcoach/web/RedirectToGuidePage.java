package com.bsgcoach.web;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.flow.RedirectToUrlException;

@NotThreadSafe
public class RedirectToGuidePage extends ABsgCoachWebPage {

    public RedirectToGuidePage() {
        super(null);
        throw new RedirectToUrlException("http://invesdwin.de/nowicket/");
    }

}
