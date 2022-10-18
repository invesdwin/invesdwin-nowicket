package de.invesdwin.nowicket;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.util.time.date.FTimeUnit;
import de.invesdwin.util.time.duration.Duration;

@Immutable
public final class NoWicketProperties {

    public static final int DEFAULT_TABLE_ROWS_PER_PAGE = 20;
    public static final Duration DEFAULT_STATUS_MESSAGE_DURATION = new Duration(10, FTimeUnit.SECONDS);
    /**
     * This is also the default max rows from ListMultipleChoice 
     */
    public static final int DEFAULT_SELECT_MAX_ROWS = 8;

    private NoWicketProperties() {}

}
