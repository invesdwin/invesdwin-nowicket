package com.eva;

import java.io.File;

import javax.annotation.concurrent.Immutable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

import com.eva.internal.ExampleSpringApplicationRunListener;
import com.eva.internal.ExampleWebApplication;
import com.eva.internal.ExampleWicketFilter;

import de.invesdwin.instrument.DynamicInstrumentationLoader;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultAccessDeniedPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultInternalErrorPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageExpiredPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageNotFoundPage;
import de.invesdwin.util.lang.reflection.Reflections;
import jakarta.servlet.DispatcherType;

@SpringBootApplication
@ImportResource(locations = "classpath:/META-INF/ctx.spring.weaving.xml")
@Immutable
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class Main {

    public static void main(final String[] args) {
        //https://github.com/spring-projects/spring-boot/issues/12649#issuecomment-1269568055
        //CHECKSTYLE:OFF
        System.setProperty("org.springframework.boot.logging.LoggingSystem", "none");
        //CHECKSTYLE:ON
        Reflections.disableJavaModuleSystemRestrictions();
        DynamicInstrumentationLoader.waitForInitialized();
        DynamicInstrumentationLoader.initLoadTimeWeavingContext();
        SpringApplication.run(Main.class, args);
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return ExampleSpringApplicationRunListener.getApplicationContext();
    }

    @Bean
    public FilterRegistrationBean<ExampleWicketFilter> filterInitializer() {
        final FilterRegistrationBean<ExampleWicketFilter> noWicketFilter = new FilterRegistrationBean<ExampleWicketFilter>();
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
    public ErrorPageRegistrar filterCustomizer() {
        return new ErrorPageRegistrar() {

            @Override
            public void registerErrorPages(final ErrorPageRegistry registry) {
                // Definition of custom error pages.
                registry.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, DefaultAccessDeniedPage.MOUNT_PATH));
                registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, DefaultAccessDeniedPage.MOUNT_PATH));
                registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, DefaultPageNotFoundPage.MOUNT_PATH));
                registry.addErrorPages(new ErrorPage(HttpStatus.GONE, DefaultPageExpiredPage.MOUNT_PATH));
                registry.addErrorPages(new ErrorPage(DefaultInternalErrorPage.MOUNT_PATH));
            }

        };

    }

}
