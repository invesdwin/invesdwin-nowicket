package de.invesdwin.nowicket.component.pnotify;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum PNotifyType {
    success("success"),
    info("info"),
    warning("notice"),
    error("error");

    private String type;

    PNotifyType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
