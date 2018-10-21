package com.bsgcoach.reports.cir.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cir.CompetitiveIntelligenceReport;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum CompetitiveIntelligenceReportParserRow {

    Internet_Price(CompetitiveIntelligenceReportParserCategory.Internet, "Price") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setPrice(value);
        }
    },
    Internet_SQ(CompetitiveIntelligenceReportParserCategory.Internet, "S/Q") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setSQ(value);
        }
    },
    Internet_Models(CompetitiveIntelligenceReportParserCategory.Internet, "Models") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setModels(value);
        }
    },
    Internet_Shipping(CompetitiveIntelligenceReportParserCategory.Internet, "Shipping") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setShipping(value);
        }
    },
    Internet_Advertising(CompetitiveIntelligenceReportParserCategory.Internet, "Advertising") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setAdvertising(value);
        }
    },
    Internet_Appeal(CompetitiveIntelligenceReportParserCategory.Internet, "Appeal") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setAppeal(value);
        }
    },
    Internet_Orders(CompetitiveIntelligenceReportParserCategory.Internet, "Orders") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setOrders(value);
        }
    },
    Internet_PairsSold(CompetitiveIntelligenceReportParserCategory.Internet, "Pairs Sold") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setPairsSold(value);
        }
    },
    Internet_PercentShare(CompetitiveIntelligenceReportParserCategory.Internet, "% Share") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getInternet().setPercentShare(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },

    Wholesale_Price(CompetitiveIntelligenceReportParserCategory.Wholesale, "Price") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setPrice(value);
        }
    },
    Wholesale_SQ(CompetitiveIntelligenceReportParserCategory.Wholesale, "S/Q") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setSQ(value);
        }
    },
    Wholesale_Models(CompetitiveIntelligenceReportParserCategory.Wholesale, "Models") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setModels(value);
        }
    },
    Wholesale_Advertising(CompetitiveIntelligenceReportParserCategory.Wholesale, "Advertising") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setAdvertising(value);
        }
    },
    Wholesale_Rebates(CompetitiveIntelligenceReportParserCategory.Wholesale, "Rebates") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setRebates(value);
        }
    },
    Wholesale_Retailers(CompetitiveIntelligenceReportParserCategory.Wholesale, "Retailers") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setRetailers(value);
        }
    },
    Wholesale_Support(CompetitiveIntelligenceReportParserCategory.Wholesale, "Support") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setSupport(value);
        }
    },
    Wholesale_Delivery(CompetitiveIntelligenceReportParserCategory.Wholesale, "Delivery") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setDelivery(value);
        }
    },
    Wholesale_Appeal(CompetitiveIntelligenceReportParserCategory.Wholesale, "Appeal") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setAppeal(value);
        }
    },
    Wholesale_Demand(CompetitiveIntelligenceReportParserCategory.Wholesale, "Demand") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setDemand(value);
        }
    },
    Wholesale_GnStkOut(CompetitiveIntelligenceReportParserCategory.Wholesale, "Gn(StkOut)") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setGnStkOut(value);
        }
    },
    Wholesale_PairsSold(CompetitiveIntelligenceReportParserCategory.Wholesale, "Pairs Sold") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setPairsSold(value);
        }
    },
    Wholesale_PercentShare(CompetitiveIntelligenceReportParserCategory.Wholesale, "% Share") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getWholesale().setPercentShare(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },

    PLabel_Price(CompetitiveIntelligenceReportParserCategory.PLabel, "Price") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getPLabel().setPrice(value);
        }
    },
    PLabel_SQ(CompetitiveIntelligenceReportParserCategory.PLabel, "S/Q") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getPLabel().setSQ(value);
        }
    },
    PLabel_Offered(CompetitiveIntelligenceReportParserCategory.PLabel, "Offered") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getPLabel().setOffered(value);
        }
    },
    PLabel_Sold(CompetitiveIntelligenceReportParserCategory.PLabel, "Sold") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getPLabel().setSold(value);
        }
    },
    PLabel_PercentShare(CompetitiveIntelligenceReportParserCategory.PLabel, "% Share") {
        @Override
        public void parse(final CompetitiveIntelligenceReport report, final Decimal value) {
            report.getPLabel().setPercentShare(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    };

    private CompetitiveIntelligenceReportParserCategory category;
    private String title;

    CompetitiveIntelligenceReportParserRow(final CompetitiveIntelligenceReportParserCategory category,
            final String title) {
        this.category = category;
        this.title = title;
    }

    public CompetitiveIntelligenceReportParserCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return title;
    }

    public static CompetitiveIntelligenceReportParserRow valueOfTitle(
            final CompetitiveIntelligenceReportParserCategory category, final String title) {
        for (final CompetitiveIntelligenceReportParserRow r : CompetitiveIntelligenceReportParserRow.values()) {
            if (r.getCategory().equals(category) && r.toString().equals(title)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, title);
    }

    public abstract void parse(CompetitiveIntelligenceReport report, Decimal value);

}
