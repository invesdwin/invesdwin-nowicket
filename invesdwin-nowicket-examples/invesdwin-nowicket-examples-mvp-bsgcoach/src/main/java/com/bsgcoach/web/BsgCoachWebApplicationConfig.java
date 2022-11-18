package com.bsgcoach.web;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.bsgcoach.web.error.AccessDeniedPage;
import com.bsgcoach.web.error.InternalErrorPage;
import com.bsgcoach.web.error.PageExpiredPage;
import com.bsgcoach.web.error.PageNotFoundPage;
import com.bsgcoach.web.request.RequestPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import jakarta.inject.Named;

@Named
@NotThreadSafe
public class BsgCoachWebApplicationConfig extends WebApplicationConfigSupport {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return RequestPage.class;
    }

    @Override
    public ResourceReference getFavicon() {
        return new PackageResourceReference(getClass(), "favicon.ico");
    }

    @Override
    public Class<? extends WebPage> getAccessDeniedPage() {
        return AccessDeniedPage.class;
    }

    @Override
    public Class<? extends WebPage> getInternalErrorPage() {
        return InternalErrorPage.class;
    }

    @Override
    public Class<? extends WebPage> getPageExpiredPage() {
        return PageExpiredPage.class;
    }

    @Override
    public Class<? extends WebPage> getPageNotFoundPage() {
        return PageNotFoundPage.class;
    }

}
