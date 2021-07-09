package de.invesdwin.nowicket.component.logviewer;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.time.date.FDate;

@Immutable
public class LogViewerEntry extends AValueObject {

    private final FDate time;
    private final boolean error;
    private final String message;

    public LogViewerEntry(final FDate time, final boolean error, final String message) {
        this.time = time;
        this.error = error;
        this.message = message;
    }

    public FDate getTime() {
        return time;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
