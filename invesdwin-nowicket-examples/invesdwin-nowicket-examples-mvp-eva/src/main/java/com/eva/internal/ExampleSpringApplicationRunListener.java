package com.eva.internal;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

@NotThreadSafe
public class ExampleSpringApplicationRunListener implements SpringApplicationRunListener {

    private static ConfigurableApplicationContext applicationContext;

    public ExampleSpringApplicationRunListener(final SpringApplication application, final String[] args) {
    }

    @Override
    public void contextPrepared(final ConfigurableApplicationContext context) {
    }

    @Override
    public void contextLoaded(final ConfigurableApplicationContext context) {
        setApplicationContext(context);
    }

    public static void setApplicationContext(final ConfigurableApplicationContext applicationContext) {
        ExampleSpringApplicationRunListener.applicationContext = applicationContext;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void started(final ConfigurableApplicationContext context) {
    }

    @Override
    public void running(final ConfigurableApplicationContext context) {
    }

    @Override
    public void failed(final ConfigurableApplicationContext context, final Throwable exception) {
    }

}
