package com.bsgcoach.reports.cor.privatelabeloffersandbids.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.util.Err;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum PrivateLabelBidsSubReportParserRow {
    //    N.A.--------------  Bid Won (0/1)
    NABidWon01("N.A.--------------", "Bid Won (0/1)", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            final Boolean b;
            if (value.equals(Decimal.ZERO)) {
                b = false;
            } else if (value.equals(Decimal.ONE)) {
                b = true;
            } else {
                throw UnknownArgumentException.newInstance(Decimal.class, value);
            }
            report.getPrivateLabelOffersAndBids(CompanyRegion.NorthAmerica).setPrivateLabelBidWon01(b);
        }
    },
    //    Production from-----------      N.A.
    NAProductionFromNA("N.A.--------------", "Production from-----------", "N.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.NorthAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.NorthAmerica, value);
        }
    },
    //      (pairs)       E-A
    NAProductionFromEA("N.A.--------------", "(pairs)", "E-A") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.NorthAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.EuropeAfrica, value);
        }
    },
    //            A-P
    NAProductionFromAP("N.A.--------------", "(pairs)", "A-P") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.NorthAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.AsiaPacific, value);
        }
    },
    //            L.A.
    NAProductionFromLA("N.A.--------------", "(pairs)", "L.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.NorthAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.LatinAmerica, value);
        }
    },
    //
    //E-A--------------   Bid Won (0/1)
    EABidWon01("E-A--------------", "Bid Won (0/1)", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            final Boolean b;
            if (value.equals(Decimal.ZERO)) {
                b = false;
            } else if (value.equals(Decimal.ONE)) {
                b = true;
            } else {
                throw UnknownArgumentException.newInstance(Decimal.class, value);
            }
            report.getPrivateLabelOffersAndBids(CompanyRegion.EuropeAfrica).setPrivateLabelBidWon01(b);
        }
    },
    //    Production from-----------      N.A.
    EAProductionFromNA("E-A--------------", "Production from-----------", "N.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.EuropeAfrica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.NorthAmerica, value);
        }
    },
    //      (pairs)       E-A
    EAProductionFromEA("E-A--------------", "(pairs)", "E-A") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.EuropeAfrica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.EuropeAfrica, value);
        }
    },
    //            A-P
    EAProductionFromAP("E-A--------------", "(pairs)", "A-P") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.EuropeAfrica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.AsiaPacific, value);
        }
    },
    //            L.A.
    EAProductionFromLA("E-A--------------", "(pairs)", "L.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.EuropeAfrica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.LatinAmerica, value);
        }
    },
    //
    //A-P--------------   Bid Won (0/1)
    APBidWon01("A-P--------------", "Bid Won (0/1)", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            final Boolean b;
            if (value.equals(Decimal.ZERO)) {
                b = false;
            } else if (value.equals(Decimal.ONE)) {
                b = true;
            } else {
                throw UnknownArgumentException.newInstance(Decimal.class, value);
            }
            report.getPrivateLabelOffersAndBids(CompanyRegion.AsiaPacific).setPrivateLabelBidWon01(b);
        }
    },
    //    Production from-----------      N.A.
    APProductionFromNA("A-P--------------", "Production from-----------", "N.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.AsiaPacific)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.NorthAmerica, value);
        }
    },
    //      (pairs)       E-A
    APProductionFromEA("A-P--------------", "(pairs)", "E-A") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.AsiaPacific)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.EuropeAfrica, value);
        }
    },
    //            A-P
    APProductionFromAP("A-P--------------", "(pairs)", "A-P") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.AsiaPacific)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.AsiaPacific, value);
        }
    },
    //            L.A.
    APProductionFromLA("A-P--------------", "(pairs)", "L.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.AsiaPacific)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.LatinAmerica, value);
        }
    },
    //
    //L.A.--------------  Bid Won (0/1)
    LABidWon01("L.A.--------------", "Bid Won (0/1)", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            final Boolean b;
            if (value.equals(Decimal.ZERO)) {
                b = false;
            } else if (value.equals(Decimal.ONE)) {
                b = true;
            } else {
                throw UnknownArgumentException.newInstance(Decimal.class, value);
            }
            report.getPrivateLabelOffersAndBids(CompanyRegion.LatinAmerica).setPrivateLabelBidWon01(b);
        }
    },
    //    Production from-----------      N.A.
    LAProductionFromNA("L.A.--------------", "Production from-----------", "N.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.LatinAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.NorthAmerica, value);
        }
    },
    //      (pairs)       E-A
    LAProductionFromEA("L.A.--------------", "(pairs)", "E-A") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.LatinAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.EuropeAfrica, value);
        }
    },
    //            A-P
    LAProductionFromAP("L.A.--------------", "(pairs)", "A-P") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.LatinAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.AsiaPacific, value);
        }
    },
    //            L.A.
    LAProductionFromLA("L.A.--------------", "(pairs)", "L.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getPrivateLabelOffersAndBids(CompanyRegion.LatinAmerica)
                    .setPrivateLabelProductionPairsFrom(CompanyRegion.LatinAmerica, value);
        }
    },

    ;

    private String category1;
    private String category2;
    private String title2;

    PrivateLabelBidsSubReportParserRow(final String category1, final String category2, final String title2) {
        this.category1 = category1;
        this.category2 = category2;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category1 + "_" + category2 + "_" + title2;
    }

    public static PrivateLabelBidsSubReportParserRow valueOfTitle(final String category1, final String category2,
            final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_"
                + Strings.trim(title2);
        for (final PrivateLabelBidsSubReportParserRow r : PrivateLabelBidsSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(CompanyOperatingReports report, Decimal value);

}
