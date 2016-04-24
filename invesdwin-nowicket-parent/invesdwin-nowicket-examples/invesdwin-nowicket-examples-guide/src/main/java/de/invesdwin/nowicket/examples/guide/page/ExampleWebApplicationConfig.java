package de.invesdwin.nowicket.examples.guide.page;

import java.util.Locale;

import javax.annotation.concurrent.Immutable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.springframework.security.authentication.AuthenticationManager;

import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.invesdwin.nowicket.application.WebApplicationConfigSupport;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.application.auth.IAuthenticationService;
import de.invesdwin.nowicket.application.filter.init.WebApplicationInitializer;
import de.invesdwin.nowicket.component.header.BootstrapExtensionsHeaderContributor;
import de.invesdwin.nowicket.examples.guide.Main;
import de.invesdwin.nowicket.examples.guide.component.ExampleThemeProvider;
import de.invesdwin.nowicket.examples.guide.component.nyancat.NyancatHtmlResourceReference;
import de.invesdwin.nowicket.examples.guide.page.documentation.introduction.IntroductionPage;
import de.invesdwin.nowicket.examples.guide.page.error.AccessDeniedPage;
import de.invesdwin.nowicket.examples.guide.page.error.InternalErrorPage;
import de.invesdwin.nowicket.examples.guide.page.error.PageExpiredPage;
import de.invesdwin.nowicket.examples.guide.page.error.PageNotFoundPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.authentication.ExampleSignInPage;
import de.invesdwin.nowicket.examples.guide.page.wicket.authentication.ExampleSignOutPage;
import de.invesdwin.nowicket.security.spring.SpringSecurityAuthenticationService;

@Immutable
public class ExampleWebApplicationConfig extends WebApplicationConfigSupport {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return IntroductionPage.class;
    }

    @Override
    public IAuthenticationService getAuthenticationService() {
        final AuthenticationManager authenticationManager = (AuthenticationManager) Main.getApplicationContext()
                .getBean("exampleAuthenticationManager");
        return new SpringSecurityAuthenticationService(authenticationManager);
    }

    @Override
    public Class<? extends WebPage> getSignInPage() {
        return ExampleSignInPage.class;
    }

    @Override
    public Class<? extends WebPage> getSignOutPage() {
        return ExampleSignOutPage.class;
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

    @Override
    public ResourceReference getFavicon() {
        return new PackageResourceReference(getClass(), "favicon.ico");
    }

    @Override
    public void postProcessNewSession(final AWebSession session) {
        session.setLocale(Locale.ENGLISH);
    }

    @Override
    public WebApplicationInitializer getInitializerOverride() {
        return new WebApplicationInitializer() {
            @Override
            protected void customizeBootstrapSettings(final BootstrapSettings bootstrapSettings) {
                super.customizeBootstrapSettings(bootstrapSettings);
                bootstrapSettings.setThemeProvider(new ExampleThemeProvider());
            }

            @Override
            protected BootstrapExtensionsHeaderContributor newBootstrapExtensionsHeaderContributor(
                    final BootstrapSettings bootstrapSettings) {
                return super.newBootstrapExtensionsHeaderContributor(bootstrapSettings)
                        //prevent "Read Next Chapter" button from intercepting the sample form submit
                        .withBtnPrimaryEnterBinding(false);
            }

            @Override
            public void init() {
                super.init();
                webApplication.mountResource("nyancat.html", NyancatHtmlResourceReference.INSTANCE);
            }
        };
    }

}
