package de.invesdwin.nowicket.component.toastr;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum ToastrPosition {
    top_right("toast-top-right"),
    bottom_right("toast-bottom-right"),
    bottom_left("toast-bottom-left"),
    top_left("toast-top-left"),
    top_full_width("toast-top-full-width"),
    bottom_full_width("toast-bottom-full-width"),
    top_center("toast-top-center"),
    bottom_center("toast-bottom-center");

    private final String cssClass;

    ToastrPosition(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String toString() {
        return cssClass;
    }
}
