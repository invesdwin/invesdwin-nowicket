package de.invesdwin.nowicket.application.filter.init.hook;

import de.invesdwin.nowicket.application.auth.AWebApplication;

public interface IWebApplicationInitializerHook {

    void onInit(AWebApplication webApplication);

}
