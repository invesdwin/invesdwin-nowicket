package de.invesdwin.nowicket.examples;

import javax.annotation.concurrent.Immutable;
import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

import de.invesdwin.instrument.DynamicInstrumentationLoader;
import de.invesdwin.nowicket.examples.internal.ExampleWebApplication;
import de.invesdwin.nowicket.examples.internal.ExampleWicketFilter;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultAccessDeniedPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultInternalErrorPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageExpiredPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageNotFoundPage;

@SpringBootApplication
@ImportResource(locations = "classpath:/META-INF/ctx.spring.weaving.xml")
@Immutable
public class Main {

    public static void main(final String[] args) {
        DynamicInstrumentationLoader.waitForInitialized();
        DynamicInstrumentationLoader.initLoadTimeWeavingContext();
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public FilterRegistrationBean filterInitializer() {
        final FilterRegistrationBean noWicketFilter = new FilterRegistrationBean();
        noWicketFilter.setFilter(new ExampleWicketFilter());
        noWicketFilter.addInitParameter("applicationClassName", ExampleWebApplication.class.getName());
        noWicketFilter.addInitParameter("filterMappingUrlPattern", "/*");

        // Deployment configuration enables custom error pages.
        // noWicketFilter.addInitParameter("configuration", "deployment");

        noWicketFilter.addUrlPatterns("/*");
        noWicketFilter.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return noWicketFilter;
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
