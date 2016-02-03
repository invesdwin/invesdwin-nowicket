package com.bsgcoach.rules;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class FeedbackFormatter {

    private FeedbackFormatter() {}

    public static String highlightVariable(final String variable) {
        return "<b>" + variable + "</b>";
    }

}
