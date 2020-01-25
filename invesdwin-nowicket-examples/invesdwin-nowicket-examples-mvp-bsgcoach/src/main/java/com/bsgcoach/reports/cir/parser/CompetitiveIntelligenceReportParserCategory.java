package com.bsgcoach.reports.cir.parser;

import javax.annotation.concurrent.Immutable;

import de.invesdwin.util.error.UnknownArgumentException;

@Immutable
public enum CompetitiveIntelligenceReportParserCategory {
    Internet("Internet--------"),
    Wholesale("Wholesale--------"),
    PLabel("P-Label--------");

    private String title;

    CompetitiveIntelligenceReportParserCategory(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public static CompetitiveIntelligenceReportParserCategory valueOfTitle(final String title) {
        for (final CompetitiveIntelligenceReportParserCategory r : CompetitiveIntelligenceReportParserCategory
                .values()) {
            if (r.toString().equals(title)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, title);
    }

}
