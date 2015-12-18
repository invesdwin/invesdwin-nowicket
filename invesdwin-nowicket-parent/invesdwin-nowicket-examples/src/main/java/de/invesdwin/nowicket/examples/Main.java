package de.invesdwin.nowicket.examples;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

import de.invesdwin.nowicket.examples.internal.NoWicketExamplesWicketApplication;
import de.invesdwin.nowicket.examples.internal.NoWicketExamplesWicketFilter;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public ServletContextInitializer wicketInitializer() {
		return new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				FilterRegistration filter = servletContext.addFilter("wicket-filter",
						NoWicketExamplesWicketFilter.class);
				filter.setInitParameter("applicationClassName", NoWicketExamplesWicketApplication.class.getName());
				filter.setInitParameter("filterMappingUrlPattern", "/*");
				filter.addMappingForUrlPatterns(null, false, "/*");
			}
		};
	}
}
