package com.bsgcoach.reports.cor.balancesheet.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.balancesheet.BalanceSheet;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum ReturnOnAverageEquitySubReportParserRow {
    //  N.A.
    ThisYear("This Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.setReturnOnAverageEquityThisYear(value);
        }
    };

    private String title2;

    ReturnOnAverageEquitySubReportParserRow(final String title2) {
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return title2;
    }

    public static ReturnOnAverageEquitySubReportParserRow valueOfTitle(final String title2) {
        final String trimmedTitle = Strings.trim(title2);
        for (final ReturnOnAverageEquitySubReportParserRow r : ReturnOnAverageEquitySubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(BalanceSheet report, Decimal value);

}
