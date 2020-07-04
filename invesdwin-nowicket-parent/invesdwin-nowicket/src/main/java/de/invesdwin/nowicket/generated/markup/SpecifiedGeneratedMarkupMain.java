package de.invesdwin.nowicket.generated.markup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.reflection.Reflections;

@NotThreadSafe
public class SpecifiedGeneratedMarkupMain extends ANoWicketMain {

    @Option(required = true, name = "-d", aliases = "--destination", usage = "The folder where the html files are generated to or merged into", metaVar = "<some/directory/>")
    private File destination;
    @Argument(required = true, metaVar = "<some.ModelClass1> <some.other.ModelClass>", usage = "FQDNs of the model classes for which the html files should be generated")
    private String[] classesArgs;

    protected SpecifiedGeneratedMarkupMain(final String[] args) {
        super(args);
    }

    @Override
    protected void startApplication(final CmdLineParser parser) throws CmdLineException {
        Assertions.assertThat(destination).exists();
        final List<Class<?>> classes = new ArrayList<Class<?>>();
        for (final String classStr : classesArgs) {
            final Class<?> clazz = Reflections.classForName(classStr);
            classes.add(clazz);
        }
        new SpecifiedGeneratedMarkup(destination, classes).generate();
    }

    public static void main(final String[] args) {
        new SpecifiedGeneratedMarkupMain(args);
    }
}
