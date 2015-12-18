package de.invesdwin.nowicket.application.filter.init.hook;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;

public interface IWebApplicationInitializerHook {

    void onInit(ABaseWebApplication webApplication);

}
