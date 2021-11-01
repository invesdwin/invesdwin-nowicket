package de.invesdwin.nowicket.component.modal;

import java.io.Serializable;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.lang.Objects;

@NotThreadSafe
public class ModalConfig implements Serializable {

    private String width;
    private String height;

    public ModalConfig(final int width, final int height) {
        this.width = String.valueOf(width);
        this.height = String.valueOf(height);
    }

    public ModalConfig(final String width, final String height) {
        this.width = width;
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public ModalConfig setWidth(final String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public ModalConfig setHeight(final String height) {
        this.height = height;
        return this;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

}
