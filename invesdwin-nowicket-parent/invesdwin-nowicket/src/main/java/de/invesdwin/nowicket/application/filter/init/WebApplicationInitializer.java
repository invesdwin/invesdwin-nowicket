package de.invesdwin.nowicket.application.filter.init;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.IPageRendererProvider;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.core.util.crypt.KeyInSessionSunJceCryptFactory;
import org.apache.wicket.javascript.DefaultJavaScriptCompressor;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.markup.html.form.AutoLabelResolver;
import org.apache.wicket.markup.resolver.IComponentResolver;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.handler.render.PageRenderer;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.CssUrlReplacer;
import org.apache.wicket.settings.PageSettings;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.invesdwin.nowicket.application.IWebApplicationConfig;
import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.filter.StalePageRequestCycleListener;
import de.invesdwin.nowicket.application.filter.init.hook.IWebApplicationInitializerHook;
import de.invesdwin.nowicket.component.header.BootstrapExtensionsHeaderContributor;
import de.invesdwin.nowicket.component.header.FaviconHeaderContributor;
import de.invesdwin.nowicket.generated.binding.processor.visitor.builder.component.form.FormComponentAwarePropertyResolver;
import de.invesdwin.nowicket.page.auth.defaultpage.DefaultSignInPage;
import de.invesdwin.nowicket.page.auth.defaultpage.DefaultSignOutPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultAccessDeniedPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultInternalErrorPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageExpiredPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageNotFoundPage;
import de.invesdwin.util.error.Throwables;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.time.date.FDate;
import jakarta.servlet.ServletContext;
import jakarta.servlet.SessionCookieConfig;

/**
 * Not using JS-Footer, since it makes respond.js load quite late and might destroy custom css in header.
 * 
 * @author subes
 * 
 */
@NotThreadSafe
public class WebApplicationInitializer {

    public static final String JS_FOOTER_BUCKET = "jsFooterBucket";
    public static final String BOOTSTRAP_JS_BUNDLE = "bootstrapJsBundle";
    public static final String BOOTSTRAP_CSS_BUNDLE = "bootstrapCssBundle";
    protected final ABaseWebApplication webApplication;

    public WebApplicationInitializer() {
        this.webApplication = ABaseWebApplication.get();
    }

    public void init() {
        registerFavicon();
        registerBeanValidation();
        registerHtmlCompressor();
        registerJavascriptFooterBucket();
        registerHeaderContributors();
        registerPackageResourceGuardPatterns();
        registerMountedPages();
        registerPageFactoryUpdatingRequestCycleListener();
        registerStoreSettings();
        registerCryptFactory();
        registerOnePassRenderForBots();
        registerRootRequestMapper();
        registerHidingAutoLabelResolver();
        registerStalePageRequestCycleListener();
        registerSessionCookieConfig();
        //FST can cause error like:
        //        Caused by: java.lang.IncompatibleClassChangeError: Class java.lang.String does not implement the requested interface org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn
        //        at de.invesdwin.nowicket.component.ReusingDelegateColumn.detach(ReusingDelegateColumn.java:65)
        //        registerFastSerializer();
        registerAjaxDebugMode();
        runHooks();
    }

    protected void registerSessionCookieConfig() {
        final String sessionCookieName = webApplication.getSessionCookieName();
        if (Strings.isNotBlank(sessionCookieName)) {
            final ServletContext servletContext = webApplication.getServletContext();
            final SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
            if (sessionCookieConfig != null) {
                sessionCookieConfig.setName(sessionCookieName);
            }
        }
    }

    protected void registerHidingAutoLabelResolver() {
        final PageSettings pageSettings = webApplication.getPageSettings();
        boolean resolverFound = false;
        for (int i = 0; i < pageSettings.getComponentResolvers().size(); i++) {
            final IComponentResolver resolver = pageSettings.getComponentResolvers().get(i);
            if (resolver instanceof AutoLabelResolver) {
                final AutoLabelResolver cResolver = (AutoLabelResolver) resolver;
                pageSettings.getComponentResolvers().set(i, new HidingAutoLabelResolver(cResolver));
                resolverFound = true;
                break;
            }
        }
        if (!resolverFound) {
            throw new IllegalStateException(AutoLabelResolver.class.getName() + " not found!");
        }
    }

    protected void registerRootRequestMapper() {
        webApplication.setRootRequestMapper(
                new DelegateWicketResourceFixingRequestMapper(webApplication.getRootRequestMapperAsCompound()));
    }

    protected void runHooks() {
        for (final IWebApplicationInitializerHook hook : webApplication.getWebApplicationInitializerHooks()) {
            hook.onInit(webApplication);
        }
    }

    protected void registerCryptFactory() {
        if (shouldPerformOptimizations()) {
            webApplication.getSecuritySettings().setCryptFactory(new KeyInSessionSunJceCryptFactory());
        }
    }

    protected void registerStoreSettings() {
        webApplication.getStoreSettings().setMaxSizePerSession(org.apache.wicket.util.lang.Bytes.megabytes(100));
    }

    protected void registerBeanValidation() {
        final BeanValidationConfiguration config = new BeanValidationConfiguration();
        config.configure(webApplication);
        config.add(new FormComponentAwarePropertyResolver());
    }

    protected void registerHtmlCompressor() {
        if (shouldPerformOptimizations()) {
            webApplication.getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory());
            webApplication.getResourceSettings().setJavaScriptCompressor(new DefaultJavaScriptCompressor());
            webApplication.getResourceSettings().setCssCompressor(new CssUrlReplacer());
        }
    }

    protected void registerJavascriptFooterBucket() {
        webApplication.getHeaderResponseDecorators().add(new JavaScriptToBucketResponseDecorator(JS_FOOTER_BUCKET));
    }

    protected boolean shouldPerformOptimizations() {
        return webApplication.usesDeploymentConfig();
    }

    /**
     * see https://github.com/wicketstuff/core/wiki/Annotation
     */
    protected void registerMountedPages() {
        for (final String basePackage : webApplication.getClasspathBasePackages()) {
            new AnnotatedMountScanner().scanPackage(basePackage).mount(webApplication);
        }

        if (webApplication.getDelegate().getAuthenticationService() != null) {
            webApplication.mountPage(DefaultSignInPage.MOUNT_PATH, webApplication.getSignInPage());
            webApplication.mountPage(DefaultSignOutPage.MOUNT_PATH, webApplication.getSignOutPage());
        }
        webApplication.getApplicationSettings().setPageExpiredErrorPage(webApplication.getPageExpiredPage());
        webApplication.mountPage(DefaultPageExpiredPage.MOUNT_PATH, webApplication.getPageExpiredPage());
        webApplication.getApplicationSettings().setInternalErrorPage(webApplication.getInternalErrorPage());
        webApplication.mountPage(DefaultInternalErrorPage.MOUNT_PATH, webApplication.getInternalErrorPage());
        webApplication.getApplicationSettings().setAccessDeniedPage(webApplication.getAccessDeniedPage());
        webApplication.mountPage(DefaultAccessDeniedPage.MOUNT_PATH,
                webApplication.getApplicationSettings().getAccessDeniedPage());
        webApplication.mountPage(DefaultPageNotFoundPage.MOUNT_PATH, webApplication.getPageNotFoundPage());
    }

    /**
     * not bundling resources here since this actually introduces problems and might in fact decrease load time instead
     * of improving it (e.g. with respond.js)
     */
    protected void registerHeaderContributors() {
        final BootstrapSettings bootstrapSettings = new BootstrapSettings();
        bootstrapSettings.setAutoAppendResources(false);
        customizeBootstrapSettings(bootstrapSettings);
        Bootstrap.install(webApplication, bootstrapSettings);
        final IHeaderContributor headerContributor = newBootstrapExtensionsHeaderContributor(bootstrapSettings);
        if (shouldPerformOptimizations()) {
            final BundleCollectingHeaderResponse bundleCollector = new BundleCollectingHeaderResponse();
            headerContributor.renderHead(bundleCollector);

            final JavaScriptReferenceHeaderItem jsBundleItem = webApplication.getResourceBundles()
                    .addJavaScriptBundle(getClass(), BOOTSTRAP_JS_BUNDLE, bundleCollector.getJavascriptResources());
            final CssReferenceHeaderItem cssBundleItem = webApplication.getResourceBundles()
                    .addCssBundle(getClass(), BOOTSTRAP_CSS_BUNDLE, bundleCollector.getCssResources());
            final HeaderItem[] otherItems = bundleCollector.getOtherHeaderItems();
            webApplication.getHeaderContributorListeners().add(new IHeaderContributor() {
                @Override
                public void renderHead(final IHeaderResponse response) {
                    response.render(cssBundleItem);
                    response.render(jsBundleItem);
                    for (int i = 0; i < otherItems.length; i++) {
                        response.render(otherItems[i]);
                    }
                }
            });
        } else {
            webApplication.getHeaderContributorListeners().add(headerContributor);
        }
    }

    protected void customizeBootstrapSettings(final BootstrapSettings bootstrapSettings) {}

    protected IHeaderContributor newBootstrapExtensionsHeaderContributor(final BootstrapSettings bootstrapSettings) {
        return new BootstrapExtensionsHeaderContributor(bootstrapSettings);
    }

    protected void registerPackageResourceGuardPatterns() {
        //see http://wicketguide.comsysto.com/guide/chapter19.html#chapter19_4
        final IPackageResourceGuard packageResourceGuard = webApplication.getResourceSettings()
                .getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            final SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) packageResourceGuard;
            addResourcePatterns(securePackageResourceGuard);
            securePackageResourceGuard.setAllowAccessToRootResources(true);
        }
    }

    protected void addResourcePatterns(final SecurePackageResourceGuard securePackageResourceGuard) {
        securePackageResourceGuard.addPattern("+*.ico");
        securePackageResourceGuard.addPattern("+*.webm");
        securePackageResourceGuard.addPattern("+*.ogv");
        securePackageResourceGuard.addPattern("+*.mp4");
        securePackageResourceGuard.addPattern("+*.woff2");
        securePackageResourceGuard.addPattern("+*.map");
    }

    /**
     * This enables the cache for pages.
     */
    protected void registerPageFactoryUpdatingRequestCycleListener() {
        webApplication.getRequestCycleListeners().add(new PageRequestHandlerTracker());
        webApplication.getRequestCycleListeners().add(new PageFactoryUpdatingRequestCycleListener());
    }

    protected void registerFavicon() {
        ResourceReference favicon = webApplication.getDelegate().getFavicon();
        if (favicon == null) {
            favicon = IWebApplicationConfig.DEFAULT_FAVICON;
        }
        /*
         * force clients to update favicon by including timestamp in url (on application restart the icon might have
         * been changed)
         */
        final String faviconUrl = "/favicon-" + new FDate().millisValue() + ".ico";
        webApplication.mountResource(faviconUrl, favicon);
        webApplication.getHeaderContributorListeners().add(new FaviconHeaderContributor(faviconUrl));
        final String fallbackFaviconUrl = "/favicon.ico";
        webApplication.mountResource(fallbackFaviconUrl, favicon);
    }

    protected void registerOnePassRenderForBots() {
        webApplication.setPageRendererProvider(new IPageRendererProvider() {
            @Override
            public PageRenderer apply(final RenderPageRequestHandler t) {
                return new BotAwareWebPageRenderer(t);
            }
        });
    }

    protected void registerStalePageRequestCycleListener() {
        webApplication.getRequestCycleListeners().add(new StalePageRequestCycleListener());
    }

    private void registerFastSerializer() {
        webApplication.getFrameworkSettings().setSerializer(new ConfiguredWicketSerializer());
    }

    private void registerAjaxDebugMode() {
        webApplication.getDebugSettings().setAjaxDebugModeEnabled(Throwables.isDebugStackTraceEnabled());
    }
}
