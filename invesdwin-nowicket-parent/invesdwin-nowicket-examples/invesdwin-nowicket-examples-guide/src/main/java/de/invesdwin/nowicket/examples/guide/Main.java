package de.invesdwin.nowicket.examples.guide;

import java.io.File;

import javax.annotation.concurrent.Immutable;
import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

import de.invesdwin.instrument.DynamicInstrumentationLoader;
import de.invesdwin.nowicket.examples.guide.internal.ExampleSpringApplicationRunListener;
import de.invesdwin.nowicket.examples.guide.internal.ExampleWebApplication;
import de.invesdwin.nowicket.examples.guide.internal.ExampleWicketFilter;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultAccessDeniedPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultInternalErrorPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageExpiredPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageNotFoundPage;

@SpringBootApplication
@ImportResource(locations = { "classpath:/META-INF/ctx.spring.weaving.xml",
        "classpath:/META-INF/ctx.example.security.xml" })
@Immutable
public class Main {

    static {
        DynamicInstrumentationLoader.waitForInitialized();
        DynamicInstrumentationLoader.initLoadTimeWeavingContext();
    }

    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return ExampleSpringApplicationRunListener.getApplicationContext();
    }

    @Bean
    public FilterRegistrationBean filterInitializer() {
        final FilterRegistrationBean noWicketFilter = new FilterRegistrationBean();
        noWicketFilter.setFilter(new ExampleWicketFilter());
        noWicketFilter.addInitParameter("applicationClassName", ExampleWebApplication.class.getName());
        noWicketFilter.addInitParameter("filterMappingUrlPattern", "/*");

        if (!isTestEnvironment()) {
            // Deployment configuration enables custom error pages.
            noWicketFilter.addInitParameter("configuration", "deployment");
        }

        noWicketFilter.addUrlPatterns("/*");
        noWicketFilter.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return noWicketFilter;
    }

    private static boolean isTestEnvironment() {
        //since java classes are packaged in src/main/java, we check if the test directory exists and if it actually contains any tests
        final File srcTestJavaDir = new File("src/test/java");
        final boolean testClassesExist = srcTestJavaDir.exists() && srcTestJavaDir.isDirectory()
                && srcTestJavaDir.list().length > 0;
        return testClassesExist;
    }

    @Bean
    public EmbeddedServletContainerCustomizer filterCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(final ConfigurableEmbeddedServletContainer container) {
                // Definition of custom error pages.
                container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, DefaultAccessDeniedPage.MOUNT_PATH));
                container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, DefaultAccessDeniedPage.MOUNT_PATH));
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, DefaultPageNotFoundPage.MOUNT_PATH));
                container.addErrorPages(new ErrorPage(HttpStatus.GONE, DefaultPageExpiredPage.MOUNT_PATH));
                container.addErrorPages(new ErrorPage(DefaultInternalErrorPage.MOUNT_PATH));
            }
        };

    }

}
