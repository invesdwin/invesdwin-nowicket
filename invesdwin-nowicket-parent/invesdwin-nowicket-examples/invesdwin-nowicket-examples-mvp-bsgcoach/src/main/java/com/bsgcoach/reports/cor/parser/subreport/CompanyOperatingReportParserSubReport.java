package com.bsgcoach.reports.cor.parser.subreport;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.adminexpenses.parser.AdminExpensesSubReportParser;
import com.bsgcoach.reports.cor.balancesheet.parser.BalanceSheetSubReportParser;
import com.bsgcoach.reports.cor.balancesheet.parser.ReturnOnAverageEquitySubReportParser;
import com.bsgcoach.reports.cor.bankloans.parser.BankLoansSubReportParser;
import com.bsgcoach.reports.cor.brandedwarehouseproductions.parser.BrandedWarehouseProductionsSubReportParser;
import com.bsgcoach.reports.cor.cashflow.parser.CashFlowSubReportParser;
import com.bsgcoach.reports.cor.celebrityendorsements.parser.CelebrityEndorsementsSubReportParser;
import com.bsgcoach.reports.cor.incomestatement.parser.IncomeStatementSubReportParser;
import com.bsgcoach.reports.cor.internetmarketingexpenses.parser.InternetMarketingExpensesSubReportParser;
import com.bsgcoach.reports.cor.internetmarketperformance.parser.InternetMarketPerformanceSubReportParser;
import com.bsgcoach.reports.cor.plannedshippingpercentages.parser.PlannedShippingPercentagesSubReportParser;
import com.bsgcoach.reports.cor.plantoperations.parser.PlantOperationsSubReportParser;
import com.bsgcoach.reports.cor.privatelabeloffersandbids.parser.PrivateLabelBidPricesSubReportParser;
import com.bsgcoach.reports.cor.privatelabeloffersandbids.parser.PrivateLabelBidsSubReportParser;
import com.bsgcoach.reports.cor.privatelabeloffersandbids.parser.PrivateLabelPairsOfferedSubReportParser;
import com.bsgcoach.reports.cor.privatelabeloffersandbids.parser.PrivateLabelSqRatingOfPairsOfferedSubReportParser;
import com.bsgcoach.reports.cor.privatelabeloperations.parser.PrivateLabelOperationsSubReportParser;
import com.bsgcoach.reports.cor.stockprice.parser.StockPriceSubReportParser;
import com.bsgcoach.reports.cor.wholesalemarketingexpenses.parser.WholesaleMarketingExpensesSubReportParser;
import com.bsgcoach.reports.cor.whslemarketperformance.parser.WhsleMarketPerformanceSubReportParser;

import de.invesdwin.util.error.UnknownArgumentException;

@Immutable
public enum CompanyOperatingReportParserSubReport {
    PlantOperations("Plant", new String[] { "Operations", "E-A", "A-P", "L.A." }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PlantOperationsSubReportParser();
        }
    },
    PlannedShippingPercentages("Planned", new String[] { "Shipping", "Percentages" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PlannedShippingPercentagesSubReportParser();
        }
    },
    BrandedWarehouseOperations("Branded", new String[] { "Warehouse", "Operations" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new BrandedWarehouseProductionsSubReportParser();
        }
    },
    CelebrityEndorsements("Celebrity", new String[] { "Endorsements" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new CelebrityEndorsementsSubReportParser();
        }
    },
    InternetMarketingExpenses("Internet", new String[] { "Marketing", "Expenses" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new InternetMarketingExpensesSubReportParser();
        }
    },
    WholesaleMarketingExpenses("Wholesale", new String[] { "Marketing", "Expenses" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new WholesaleMarketingExpensesSubReportParser();
        }
    },
    AdminExpenses("Admin", new String[] { "Expenses" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new AdminExpensesSubReportParser();
        }
    },
    PrivateLabelPairsOffered("Private-Label Pairs Offered ----------------------------", new String[] {}) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PrivateLabelPairsOfferedSubReportParser();
        }
    },
    PrivateLabelBids("Private-", new String[] { "Label", "Bids" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PrivateLabelBidsSubReportParser();
        }
    },
    PrivateLabelBidPrices("Private-Label Bid Prices ----------------------------", new String[] {}) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PrivateLabelBidPricesSubReportParser();
        }
    },
    PrivateLabelOperations("Private-", new String[] { "Label", "Operations" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PrivateLabelOperationsSubReportParser();
        }
    },
    PrivateLabelSQRatingOfPairsOffered(
            "Private-Label S/Q Rating of Pairs Offered ----------------------------",
            new String[] {}) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new PrivateLabelSqRatingOfPairsOfferedSubReportParser();
        }
    },
    InternetMarketPerformance("Internet-----", new String[] { "Market", "Performance" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new InternetMarketPerformanceSubReportParser();
        }
    },
    WhsleMarketPerformance("Whsle", new String[] { "Market", "Performance" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new WhsleMarketPerformanceSubReportParser();
        }
    },
    IncomeStatement("Income", new String[] { "Statement" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new IncomeStatementSubReportParser();
        }
    },
    CashFlow("Cash", new String[] { "Flow" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new CashFlowSubReportParser();
        }
    },
    BalanceSheet("Balance", new String[] { "Sheet" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new BalanceSheetSubReportParser();
        }
    },
    ReturnOnAverageEquity("Return on Average Equity------------------------------", new String[] {}) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new ReturnOnAverageEquitySubReportParser();
        }
    },
    StockPrice("Stock", new String[] { "Price" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new StockPriceSubReportParser();
        }
    },
    BankLoans("Bank", new String[] { "Loans" }) {
        @Override
        public ISubReportParser newSubReportParser() {
            return new BankLoansSubReportParser();
        }
    };

    private String title;
    private String[] appendix;

    CompanyOperatingReportParserSubReport(final String title, final String[] appendix) {
        this.title = title;
        this.appendix = appendix;
    }

    @Override
    public String toString() {
        return title;
    }

    public abstract ISubReportParser newSubReportParser();

    public static CompanyOperatingReportParserSubReport valueOfTitle(
            final CompanyOperatingReportParserSubReport curSubReport, final String title) {
        for (final CompanyOperatingReportParserSubReport r : CompanyOperatingReportParserSubReport.values()) {
            if (r.toString().equals(title) && (curSubReport == null || r.ordinal() > curSubReport.ordinal())) {
                return r;
            }
        }
        if (curSubReport != null) {
            if (curSubReport.title.equals(title)) {
                return curSubReport;
            }
            for (final String a : curSubReport.appendix) {
                if (a.equals(title)) {
                    return curSubReport;
                }
            }
        }
        throw UnknownArgumentException.newInstance(String.class, title);
    }
}
