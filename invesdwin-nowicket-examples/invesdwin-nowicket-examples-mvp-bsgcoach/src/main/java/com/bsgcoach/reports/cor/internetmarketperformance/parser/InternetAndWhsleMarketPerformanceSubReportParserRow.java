package com.bsgcoach.reports.cor.internetmarketperformance.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.internetmarketperformance.InternetMarketPerformance;
import com.bsgcoach.reports.cor.whslemarketperformance.WhsleMarketPerformance;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum InternetAndWhsleMarketPerformanceSubReportParserRow {
    //    Revenues from Sales
    RevenuesFromSales("Revenues from Sales", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setRevenuesFromSales(value);
        }
    },
    //    Customer-Paid Fees
    CustomerPaidFees("Customer-Paid Fees", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            ((InternetMarketPerformance) report).setCustomerPaidFees(value);
        }
    },
    //    Gross Revenues
    GrossRevenues("Gross Revenues", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            ((InternetMarketPerformance) report).setGrossRevenues(value);
        }
    },
    //      Exchange Rate Adj
    ExchangeRateAdj("Exchange Rate Adj", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setExchangeRateAdj(value);
        }
    },
    //    Net Revenues
    NetRevenues("Net Revenues", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setNetRevenues(value);
        }
    },
    //    Costs--------   COBPS
    CostsCobps("Costs--------", "COBPS") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.getCosts().setCobps(value);
        }
    },
    //        Whse
    CostsWhse("Costs--------", "Whse") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.getCosts().setWhse(value);
        }
    },
    //        Mktng
    CostsMktng("Costs--------", "Mktng") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.getCosts().setMktng(value);
        }
    },
    //        Admin
    CostsAdmin("Costs--------", "Admin") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.getCosts().setAdmin(value);
        }
    },
    //    Operating Profit
    OperatingProfit("Operating Profit", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setOperatingProfit(value);
        }
    },
    //
    //    % of Pairs Sold
    PercentOfPairsSold("% of Pairs Sold", null) {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setPercentOfPairsSld(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    This Yr-------  Pairs Sold
    ThisYrPairsSold("This Yr-------", "Pairs Sold") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setPairsSoldThisYr(value);
        }
    },
    //        Mkt Share
    ThisYrMktShare("This Yr-------", "Mkt Share") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setMktShareThisYr(value);
        }
    },
    //        OpMargin
    ThisYrOpMargin("This Yr-------", "OpMargin") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setOpMarginThisYr(value);
        }
    },
    //    Last Yr-------  Pairs Sold
    LastYrPairsSold("Last Yr-------", "Pairs Sold") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setPairsSoldLastYr(value);
        }
    },
    //        Mkt Share
    LastYrMktShare("Last Yr-------", "Mkt Share") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setMktShareLastYr(value);
        }
    },
    //        OpMargin
    LastYrOpMargin("Last Yr-------", "OpMargin") {
        @Override
        public void parse(final WhsleMarketPerformance report, final Decimal value) {
            report.setOpMarginLastYr(value);
        }
    };

    private String title1;
    private String title2;

    InternetAndWhsleMarketPerformanceSubReportParserRow(final String title1, final String title2) {
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return title1 + "_" + title2;
    }

    public static InternetAndWhsleMarketPerformanceSubReportParserRow valueOfTitle(final String title1,
            final String title2) {
        final String trimmedTitle = Strings.trim(title1) + "_" + Strings.trim(title2);
        for (final InternetAndWhsleMarketPerformanceSubReportParserRow r : InternetAndWhsleMarketPerformanceSubReportParserRow
                .values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(WhsleMarketPerformance report, Decimal value);

}
