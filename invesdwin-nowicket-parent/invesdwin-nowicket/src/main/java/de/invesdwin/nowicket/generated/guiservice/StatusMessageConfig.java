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

    public StatusMessageConfig withDuration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    public StatusMessageType getType() {
        return type;
    }

    public StatusMessageConfig withType(final StatusMessageType type) {
        this.type = type;
        return this;
    }

    public String getIconCssClass() {
        return iconCssClass;
    }

    public StatusMessageConfig withIconCssClass(final String iconCssClass) {
        this.iconCssClass = iconCssClass;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public StatusMessageConfig withTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public StatusMessageConfig withMessage(final String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

}
