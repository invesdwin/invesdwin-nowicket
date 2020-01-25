package de.invesdwin.nowicket.examples.guide;

import java.util.Set;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.nowicket.examples.guide.internal.ExampleWebApplication;
import de.invesdwin.nowicket.generated.markup.AAnnotatedGeneratedMarkup;

@Immutable
public class ExampleMarkupGenerator extends AAnnotatedGeneratedMarkup {

    //CHECKSTYLE:OFF
    public static void main(final String[] args) {
        //CHECKSTYLE:ON
        new ExampleMarkupGenerator().generate();
    }

    @Override
    protected Set<String> getClasspathBasePackages() {
        return ExampleWebApplication.BASE_PACKAGE;
    }
}
