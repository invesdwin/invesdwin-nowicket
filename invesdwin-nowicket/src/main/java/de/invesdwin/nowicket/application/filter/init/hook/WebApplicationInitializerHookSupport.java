package de.invesdwin.nowicket.application.filter.init.hook;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.nowicket.application.auth.AWebApplication;

// @Named
@Immutable
public class WebApplicationInitializerHookSupport implements IWebApplicationInitializerHook {

    @Override
    public void onInit(final AWebApplication webApplication) {}

}
