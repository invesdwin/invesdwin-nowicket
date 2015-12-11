package de.invesdwin.nowicket.generated.markup.processor.context;

import java.io.File;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.io.FilenameUtils;

@Immutable
public enum MarkupType {
    PANEL("Panel"),
    PAGE("Page");

    private String text;

    MarkupType(final String text) {
        this.text = text;
    }

    public static MarkupType fromFile(final File file) {
        if (file == null) {
            return null;
        }
        final String withoutExtension = FilenameUtils.removeExtension(file.getName());
        if (withoutExtension.endsWith(PANEL.text)) {
            return PANEL;
        } else if (withoutExtension.endsWith(PAGE.text)) {
            return PAGE;
        } else {
            throw new IllegalArgumentException(
                    "Unable to determine " + MarkupType.class.getSimpleName() + " for: " + file.toString());
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
