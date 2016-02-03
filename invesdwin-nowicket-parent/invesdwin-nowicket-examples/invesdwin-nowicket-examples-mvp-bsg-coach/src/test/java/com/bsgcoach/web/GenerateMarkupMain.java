package com.bsgcoach.web;

import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.AAnnotatedGeneratedMarkup;

@NotThreadSafe
public final class GenerateMarkupMain {

    private GenerateMarkupMain() {}

    public static void main(final String[] args) {
        new AAnnotatedGeneratedMarkup() {
            @Override
            protected Set<String> getClasspathBasePackages() {
                return com.bsgcoach.internal.ExampleWebApplication.BASE_PACKAGE;
            }
        }.generate();
    }

}
