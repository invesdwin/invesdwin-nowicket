package com.bsgcoach.reports.fir.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.fir.FootwearIndustryReport;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum FootwearIndustryReportParserRow {

    //Income Statement-------
    NetRevenues("Net Revenues") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setNetRevenues(value);
        }
    },
    CostOfPairs("Cost of Pairs") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setCostOfPairs(value);
        }
    },
    Warehouse("Warehouse") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setWarehouse(value);
        }
    },
    Marketing("Marketing") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setMarketing(value);
        }
    },
    Administrative("Administrative") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setAdministrative(value);
        }
    },
    OperatingProfit("Operating Profit") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setOperatingProfit(value);
        }
    },
    InterestExpense("Interest Expense") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setInterestExpense(value);
        }
    },
    IncomeTaxes("Income Taxes") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setIncomeTaxes(value);
        }
    },
    NetProfit("Net Profit") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setNetProfit(value);
        }
    },
    Dividends("Dividends") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setDividends(value);
        }
    },
    StockShares("Stock Shares") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getIncomeStatement().setStockShares(value);
        }
    },

    //Balance Sheet-------------
    CashOnHand("Cash On Hand") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setCashOnHand(value);
        }
    },
    CurrentAssets("Current Assets") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setCurrentAssets(value);
        }
    },
    TotalAssets("Total Assets") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setTotalAssets(value);
        }
    },
    CurrentLiabilities("Current Liabilities") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setCurrentLiabilities(value);
        }
    },
    LongTermDebt("Long-Term Debt") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setLongTermDebt(value);
        }
    },
    BeginningEquity("Beginning Equity") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setBeginningEquity(value);
        }
    },
    StockSalePurch("Stock Sale (Purch)") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setStockSalePurch(value);
        }
    },
    EarningsRetained("Earnings Retained") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setEarningsRetained(value);
        }
    },
    EndingEquity("Ending Equity") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getBalanceSheet().setEndingEquity(value);
        }
    },

    //Dividend Data-------------
    YearXDividendDollars("Year X Dividend ($)") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getDividendData().setYearXDividendDollars(value);
        }
    },
    NoOfDivIncreases("No.of Div Increases") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getDividendData().setNoOfDivIncreases(value);
        }
    },
    NoOfDivDecreases("No.of Div Decreases") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getDividendData().setNoOfDivDecreases(value);
        }
    },

    //Selected Financial Stats---------------
    CostPercent("Cost %") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setCostPercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    WhsePercent("Whse %") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setWhsePercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    MktngPercent("Mktng %") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setMktngPercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    AdminPercent("Admin %") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setAdminPercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    OpProfPercent("OpProf %") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setOpProfPercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    NetProfPercent("NetProf %") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats()
                    .setNetProfPercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    CurRatio("Cur Ratio") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setCurRatio(new Percent(value.doubleValue(), PercentScale.RATE));
        }
    },
    InvDays("InvDays") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getSelectedFinancialStats().setInvDays(value);
        }
    },

    //Credit Rating Data-------
    InterestCoverage("Interest Coverage") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getCreditRatingData().setInterestCoverage(new Percent(value.doubleValue(), PercentScale.RATE));
        }
    },
    DebtAssetsRatio("Debt Asstes Ratio") {
        @Override
        public void parse(final FootwearIndustryReport report, final Decimal value) {
            report.getCreditRatingData().setDebtAssetsRatio(new Percent(value.doubleValue(), PercentScale.RATE));
        }
    };

    private String title;

    FootwearIndustryReportParserRow(final String title) {
        this.title = title;
    }

    public abstract void parse(FootwearIndustryReport report, Decimal value);

    @Override
    public String toString() {
        return title;
    }

    public static FootwearIndustryReportParserRow valueOfTitle(final String title) {
        for (final FootwearIndustryReportParserRow r : FootwearIndustryReportParserRow.values()) {
            if (r.toString().equals(title)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, title);
    }

}
