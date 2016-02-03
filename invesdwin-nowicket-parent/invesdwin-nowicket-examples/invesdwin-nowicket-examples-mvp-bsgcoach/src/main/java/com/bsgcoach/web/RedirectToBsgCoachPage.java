package com.bsgcoach.web;

import javax.annotation.concurrent.NotThreadSafe;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.flow.RedirectToUrlException;

@NotThreadSafe
public class RedirectToBsgCoachPage extends ABsgCoachWebPage {

    public RedirectToBsgCoachPage() {
        super(null);
        throw new RedirectToUrlException("https://github.com/subes/invesdwin-nowicket",
                HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

}
