package com.bsgcoach.internal;

import javax.annotation.concurrent.Immutable;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Immutable
@Configuration
@EnableWebSecurity
public class ExampleWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        /*
         * Multipart file upload forms do not behave properly with default config of spring-boot-security
         * 
         * This is caused by spring-security X-Frame config which interferes with multipart file upload forms, dunno how
         * to change these settings in spring-boot, worx fine in different platform:
         * http://rpuchkovskiy.blogspot.de/2014/10/spring-security-32-defaults-break.html
         * 
         * Even with the config here, spring-boot does not want to apply the settings... Dunno why...
         * 
         */
        http.csrf().disable().headers().defaultsDisabled().frameOptions().sameOrigin();
    }
}