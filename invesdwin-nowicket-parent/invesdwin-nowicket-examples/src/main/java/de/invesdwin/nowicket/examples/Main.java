package de.invesdwin.nowicket.examples;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import de.invesdwin.nowicket.examples.internal.NoWicketExamplesWebApplication;
import de.invesdwin.nowicket.examples.internal.NoWicketExamplesWicketFilter;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultAccessDeniedPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultInternalErrorPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageExpiredPage;
import de.invesdwin.nowicket.page.error.defaultpage.DefaultPageNotFoundPage;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public FilterRegistrationBean noWicketInitializer() {
		FilterRegistrationBean noWicketFilter = new FilterRegistrationBean();
		noWicketFilter.setFilter(new NoWicketExamplesWicketFilter());
		noWicketFilter.addInitParameter("applicationClassName", NoWicketExamplesWebApplication.class.getName());
		noWicketFilter.addInitParameter("filterMappingUrlPattern", "/*");
		noWicketFilter.addUrlPatterns("/*");
		noWicketFilter.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
		return noWicketFilter;
	}

	@Bean
	public EmbeddedServletContainerCustomizer noWicketCustomizer() {
		return new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, DefaultAccessDeniedPage.MOUNT_PATH));
				container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, DefaultAccessDeniedPage.MOUNT_PATH));
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, DefaultPageNotFoundPage.MOUNT_PATH));
				container.addErrorPages(new ErrorPage(HttpStatus.GONE, DefaultPageExpiredPage.MOUNT_PATH));
				container.addErrorPages(new ErrorPage(DefaultInternalErrorPage.MOUNT_PATH));
			}
		};

	}
}
