package com.bsgcoach.web;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Named;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.bsgcoach.web.request.RequestPage;

import de.invesdwin.nowicket.application.WebApplicationConfigSupport;

@Named
@NotThreadSafe
public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return RequestPage.class;
    }

    @Override
    public ResourceReference getFavicon() {
        return new PackageResourceReference(getClass(), "favicon.ico");
    }

}
