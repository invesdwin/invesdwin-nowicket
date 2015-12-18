package de.invesdwin.nowicket.application.filter.init.hook;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

// @Named
@Immutable
public class WebApplicationInitializerHookSupport implements IWebApplicationInitializerHook {

    @Override
    public void onInit(final ABaseWebApplication webApplication) {}

}
