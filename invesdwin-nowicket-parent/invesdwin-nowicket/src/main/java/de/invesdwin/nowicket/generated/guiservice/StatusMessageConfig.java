package de.invesdwin.nowicket.generated.guiservice;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.NoWicketProperties;
import de.invesdwin.util.lang.Objects;
import de.invesdwin.util.time.duration.Duration;

@NotThreadSafe
public class StatusMessageConfig implements Serializable {

    private Duration duration = NoWicketProperties.DEFAULT_STATUS_MESSAGE_DURATION;
    private StatusMessageType type;
    private String iconCssClass;
    private String title;
    private String message;

    public Duration getDuration() {
        return duration;
    }

    public StatusMessageConfig setDuration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    public StatusMessageType getType() {
        return type;
    }

    public StatusMessageConfig setType(final StatusMessageType type) {
        this.type = type;
        return this;
    }

    public String getIconCssClass() {
        return iconCssClass;
    }

    public StatusMessageConfig setIconCssClass(final String iconCssClass) {
        this.iconCssClass = iconCssClass;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public StatusMessageConfig setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public StatusMessageConfig setMessage(final String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

}
