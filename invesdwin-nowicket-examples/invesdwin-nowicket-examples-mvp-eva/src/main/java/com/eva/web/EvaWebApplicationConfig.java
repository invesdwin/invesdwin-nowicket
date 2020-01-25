package com.eva.web;

import java.util.Locale;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Named;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.eva.web.css.CeruleanExtentionsCssReference;
import com.eva.web.decision.DecisionPage;
import com.eva.web.error.AccessDeniedPage;
import com.eva.web.error.InternalErrorPage;
import com.eva.web.error.PageExpiredPage;
import com.eva.web.error.PageNotFoundPage;

import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.filter.init.WebApplicationInitializer;
import de.invesdwin.nowicket.component.header.BootstrapExtensionsHeaderContributor;

@Named
@NotThreadSafe
public class EvaWebApplicationConfig extends WebApplicationConfigSupport {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return DecisionPage.class;
    }

    @Override
    public ResourceReference getFavicon() {
        return new PackageResourceReference(getClass(), "favicon.ico");
    }

    @Override
    public WebApplicationInitializer getInitializerOverride() {
        return new WebApplicationInitializer() {
            @Override
            protected BootstrapExtensionsHeaderContributor newBootstrapExtensionsHeaderContributor(
                    final BootstrapSettings bootstrapSettings) {
                return new BootstrapExtensionsHeaderContributor(bootstrapSettings) {

                    @Override
                    protected void renderHeadAfterBootstrap(final IHeaderResponse response) {
                        super.renderHeadAfterBootstrap(response);
                        new BootswatchThemeProvider(BootswatchTheme.Cerulean).defaultTheme().renderHead(response);
                        CeruleanExtentionsCssReference.INSTANCE.renderHead(response);
                    }
                };
            }
        };
    }

    @Override
    public void postProcessNewSession(final AWebSession session) {
        super.postProcessNewSession(session);
        session.setLocale(Locale.ENGLISH);
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
