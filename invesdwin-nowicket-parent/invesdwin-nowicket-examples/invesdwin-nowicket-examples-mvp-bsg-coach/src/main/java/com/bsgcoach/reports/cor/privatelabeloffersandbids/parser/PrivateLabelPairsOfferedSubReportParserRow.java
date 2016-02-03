package com.bsgcoach.reports.cor.privatelabeloffersandbids.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum PrivateLabelPairsOfferedSubReportParserRow {
    //    N.A.
    NA("N.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.NorthAmerica).setPrivateLabelPairsOffered(value);
        }
    },
    //    E-A
    EA("E-A") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.EuropeAfrica).setPrivateLabelPairsOffered(value);
        }
    },
    //    A-P
    AP("A-P") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.AsiaPacific).setPrivateLabelPairsOffered(value);
        }
    },
    //    L.A.
    LA("L.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.LatinAmerica).setPrivateLabelPairsOffered(value);
        }
    };

    private String title2;

    PrivateLabelPairsOfferedSubReportParserRow(final String title2) {
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return title2;
    }

    public static PrivateLabelPairsOfferedSubReportParserRow valueOfTitle(final String title2) {
        final String trimmedTitle = Strings.trim(title2);
        for (final PrivateLabelPairsOfferedSubReportParserRow r : PrivateLabelPairsOfferedSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(final CompanyOperatingReports report, final Decimal value);

}
