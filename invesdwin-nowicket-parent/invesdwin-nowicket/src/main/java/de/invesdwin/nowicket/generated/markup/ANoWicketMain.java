package de.invesdwin.nowicket.generated.markup;

import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.Immutable;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

@Immutable
public abstract class ANoWicketMain {

    protected final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(getClass());

    @Option(help = true, name = "-h", aliases = "--help", usage = "Shows this help text")
    protected boolean help;

    protected ANoWicketMain(final String[] args) {
        parseCommandline(args);
    }

    private void parseCommandline(final String[] args) {
        final CmdLineParser parser = newCmdLineParser();
        try {
            parser.parseArgument(args);
            if (help) {
                printHelp(parser);
            } else {
                startApplication(parser);
            }
        } catch (final Throwable e) {
            e.printStackTrace(); //SUPPRESS CHECKSTYLE single line
            printHelp(parser);
        }
    }

    protected CmdLineParser newCmdLineParser() {
        return new CmdLineParser(this);
    }

    protected abstract void startApplication(CmdLineParser parser) throws Exception;

    protected final void waitForShutdown() {
        try {
            TimeUnit.DAYS.sleep(Long.MAX_VALUE);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected final void printHelp(final CmdLineParser parser) {
        log.error(createHelpString(parser));
    }

    protected String createHelpString(final CmdLineParser parser) {
        final StringWriter writer = new StringWriter();
        parser.setUsageWidth(120);
        writer.append("\nUsage:\tjava ");
        writer.append(getClass().getName());
        parser.printSingleLineUsage(writer, null);
        writer.append("\nOptions:\n");
        parser.printUsage(writer, null);
        final String help = writer.toString();
        return help;
    }
}
