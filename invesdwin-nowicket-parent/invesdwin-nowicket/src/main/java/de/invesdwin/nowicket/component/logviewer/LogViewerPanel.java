package de.invesdwin.nowicket.component.logviewer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import de.invesdwin.nowicket.component.header.render.preact.PreactPartialPageRequestHandler;
import de.invesdwin.nowicket.component.logviewer.js.LogViewerJsReference;
import de.invesdwin.nowicket.component.websocket.AWebSocketFallbackTimerBehavior;
import de.invesdwin.util.collections.iterable.ICloseableIterable;
import de.invesdwin.util.collections.iterable.ICloseableIterator;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;
import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.date.FDates;
import de.invesdwin.util.time.duration.Duration;

/**
 * http://apache-wicket.1842946.n4.nabble.com/How-to-build-a-hudson-jenkins-like-live-log-viewer-td4090224.html#a4659629
 */
@NotThreadSafe
public class LogViewerPanel extends GenericPanel<ILogViewerSource> {

    private static final Resource JS_RESOURCE = new ClassPathResource(LogViewerPanel.class.getSimpleName() + ".js",
            LogViewerPanel.class);

    private FDate logTo;
    private int expectedRowCount;
    private final Set<String> lastLogToMessages = new HashSet<>();
    private boolean shouldReset;

    public LogViewerPanel(final String id, final IModel<ILogViewerSource> model, final Duration refreshInterval) {
        super(id, model);
        final AWebSocketFallbackTimerBehavior timer = newWebsocketTimerBehavior(refreshInterval);
        if (timer != null) {
            add(timer);
        }
    }

    protected AWebSocketFallbackTimerBehavior newWebsocketTimerBehavior(final Duration refreshInterval) {
        return new AWebSocketFallbackTimerBehavior(refreshInterval) {

            @Override
            protected void onTimer(final PreactPartialPageRequestHandler handler) {
                LogViewerPanel.this.onTimer(handler);
            }

            @Override
            protected String createClientResponseScript() {
                return LogViewerPanel.this.createClientResponseScript();
            }

            @Override
            protected void processClientResponse(final String clientResponse) {
                LogViewerPanel.this.processClientResponse(clientResponse);
            }
        };
    }

    public void onTimer(final IPartialPageRequestHandler handler) {
        /*
         * We are doing the following here: - append the content of "nextLog" to "logData" - remove "nextLog" - insert
         * "nextLog" after "logData".
         */
        if (shouldReset) {
            shouldReset = false;
            expectedRowCount = 0;
            lastLogToMessages.clear();
            logTo = null;
            handler.appendJavaScript("window." + LogViewerJsReference.FUNCTION_NAME + "_reset()");
        }

        final String tailLog = tailLog();
        if (Strings.isNotBlank(tailLog)) {
            handler.appendJavaScript(tailLog);
        }
    }

    public String createClientResponseScript() {
        if (expectedRowCount > 0) {
            return "window.logViewer_rowCount";
        } else {
            return null;
        }
    }

    public void processClientResponse(final String clientResponse) {
        final int rowCount = Integer.parseInt(clientResponse);
        if (rowCount != expectedRowCount) {
            shouldReset = true;
        }
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        LogViewerJsReference.INSTANCE.renderHead(response);
        final CharSequence js = createJavaScript();
        response.render(JavaScriptHeaderItem.forScript(js, getClass().getSimpleName()));
    }

    private CharSequence createJavaScript() {
        try {
            final InputStream in = JS_RESOURCE.getInputStream();
            String js = IOUtils.toString(in, Charset.defaultCharset());
            in.close();

            js = js.replace("${LOG_HEIGHT}", new Decimal(getLogHeight().getRate()).toString());

            return js;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Percent getLogHeight() {
        return new Percent(80D, PercentScale.PERCENT);
    }

    protected String tailLog() {
        final StringBuilder js = new StringBuilder();
        int countEntries = 0;
        final ICloseableIterable<LogViewerEntry> entries = getModelObject().getLogViewerEntries(logTo,
                getMaxTrailingMessages());
        try (ICloseableIterator<LogViewerEntry> iterator = entries.iterator()) {
            while (true) {
                final LogViewerEntry entry = iterator.next();
                final StringBuilder message = new StringBuilder();
                message.append("window." + LogViewerJsReference.FUNCTION_NAME + "_append('");
                message.append(FDates.toString(entry.getTime()));
                message.append("', ");
                message.append(entry.isError());
                message.append(", '");
                message.append(entry.getMessage().replace("'", "&#x27;"));
                message.append("');");
                if (!Objects.equals(logTo, entry.getTime()) && (logTo == null || logTo.isBefore(entry.getTime()))) {
                    lastLogToMessages.clear();
                    logTo = entry.getTime();
                }
                final String messageStr = message.toString();
                if (lastLogToMessages.add(messageStr)) {
                    if (js.length() > 0) {
                        js.append("\n");
                    }
                    js.append(messageStr);
                    expectedRowCount++;
                    countEntries++;
                }
            }
        } catch (final NoSuchElementException e) {
            //end reached
        }
        if (countEntries > 0) {
            js.append("\nwindow." + LogViewerJsReference.FUNCTION_NAME + "_update();");
        }
        return js.toString();
    }

    protected Integer getMaxTrailingMessages() {
        return 10000;
    }

}
