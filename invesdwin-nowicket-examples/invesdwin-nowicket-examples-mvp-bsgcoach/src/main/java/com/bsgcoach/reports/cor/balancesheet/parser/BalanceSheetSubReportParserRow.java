package com.bsgcoach.reports.cor.balancesheet.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.balancesheet.BalanceSheet;
import com.bsgcoach.reports.cor.balancesheet.BalanceSheet.Liabilities;
import com.bsgcoach.util.Err;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum BalanceSheetSubReportParserRow {
    //    Assets--------  Cash On Hand
    AssetsCashOnHand("Assets--------", "Cash On Hand", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().setCashOnHand(value);
        }
    },
    //    Interest Rate
    AssetsInterestRate("Assets--------", "Interest Rate", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().setInterestRate(value);
        }
    },
    //  Accounts Receivable
    AssetsAccountsReceivable("Assets--------", "Accounts Receivable", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().setAccountsReceivable(value);
        }
    },
    //  Footwear Inventories
    AssetsFootwearInventories("Assets--------", "Footwear Inventories", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().setFootwearInventories(value);
        }
    },
    //    Total Curetn Assets
    AssetsTotalCurrentAssets("Assets--------", "Total Curetn Assets", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().setTotalCurrentAssets(value);
        }
    },
    //  Fixed---------- Net Plant Investment
    AssetsFixedAssetsNetPlantInvestment("Assets--------", "Fixed----------", "Net Plant Investment", null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().getFixedAssets().setNetPlantInvestment(value);
        }
    },
    //    Assets    Work in Progress
    AssetsFixedAssetsWorkInProgress("Assets--------", "Assets", "Work in Progress", null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().getFixedAssets().setWorkInProgress(value);
        }
    },
    //        Total Fixed Assets
    AssetsFixedAssetsTotalFixedAssets("Assets--------", "Assets", "Total Fixed Assets", null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getAssets().getFixedAssets().setTotalFixedAssets(value);
        }
    },
    //
    //Total Assets
    TotalAssets("Total Assets", null, null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.setTotalAssets(value);
        }
    },
    //
    //Liabilities------   Accounts Payable
    LiabilitiesAccountsPayable("Liabilities------", "Accounts Payable", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getLiabilities().setAccountsPayable(value);
        }
    },
    //  Overdraft Loan Payable
    LiabilitiesOverdraftLoanPayable("Liabilities------", "Overdraft Loan Payable", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getLiabilities().setOverdraftLoanPayable(value);
        }
    },
    //    Interest Rate
    LiabilitiesInterestRate("Liabilities------", "Interest Rate", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            final Liabilities liabilities = report.getLiabilities();
            if (liabilities.getOverdraftLoanInterestRate() == null) {
                liabilities.setOverdraftLoanInterestRate(value);
            } else {
                Assertions.assertThat(liabilities.getLoanPayable1YearInterestRate()).isNull();
                liabilities.setLoanPayable1YearInterestRate(value);
            }
        }
    },
    //  1-Year Loan Payable
    Liabilities1YearLoanPayable("Liabilities------", "1-Year Loan Payable", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            Assertions.assertThat(report.getLiabilities().getOverdraftLoanInterestRate()).isNotNull();
            Assertions.assertThat(report.getLiabilities().getLoanPayable1YearInterestRate()).isNull();
            report.getLiabilities().setLoanPayable1Year(value);
        }
    },
    //    Interest Rate
    //  Current Portion of Long-Term
    LiabilitiesCurrentPortionOfLongTerm("Liabilities------", "Current Portion of Long-Term", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getLiabilities().setCurrentPortionOfLongTerm(value);
        }
    },
    //    Total Current Liabilities
    LiabilitiesTotalCurrentLiabilities("Liabilities------", "Total Current Liabilities", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getLiabilities().setTotalCurrentLiabilities(value);
        }
    },
    //  Long-Term bank Loans
    LiabilitiesLongTermBankLoans("Liabilities------", "Long-Term bank Loans", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getLiabilities().setLongTermBankLoans(value);
        }
    },
    //    Total Liabilities
    LiabilitiesTotalLiabilities("Liabilities------", "Total Liabilities", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getLiabilities().setTotalLiabilities(value);
        }
    },
    //
    //Equity----------    Common Stock-----------     This Year
    EquityCommonStockThisYear("Equity----------", "Common Stock-----------", null, "This Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setCommonStockThisYear(value);
        }
    },
    //          Last Year
    EquityCommonStockLastYear("Equity----------", "Common Stock-----------", null, "Last Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setCommonStockLastYear(value);
        }
    },
    //  Additional Capital      This Year
    EquityAdditionalCapitalThisYear("Equity----------", "Additional Capital", null, "This Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setAdditionalCapitalThisYear(value);
        }
    },
    //          Last Year
    EquityAdditionalCapitalLastYear("Equity----------", "Additional Capital", null, "Last Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setAdditionalCapitalLastYear(value);
        }
    },
    //  Retained Earnings       This Year
    EquityRetainedEarningsThisYear("Equity----------", "Retained Earnings", null, "This Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setRetainedEarningsThisYear(value);
        }
    },
    //          Last Year
    EquityRetainedEarningsLastYear("Equity----------", "Retained Earnings", null, "Last Year") {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setRetainedEarningsLastYear(value);
        }
    },
    //    Total Equity
    EquityTotalEquity("Equity----------", "Total Equity", null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.getEquity().setTotalEquity(value);
        }
    },
    //
    //Total Liabilities and Equity
    TotalLiabilitiesAndEquity("Total Liabilities and Equity", null, null, null) {
        @Override
        public void parse(final BalanceSheet report, final Decimal value) {
            report.setTotalLiabilitiesAndEquity(value);
        }
    };

    private String category1;
    private String category2;
    private String title1;
    private String title2;

    BalanceSheetSubReportParserRow(final String category1, final String category2, final String title1,
            final String title2) {
        this.category1 = category1;
        this.category2 = category2;
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category1 + "_" + category2 + "_" + title1 + "_" + title2;
    }

    public static BalanceSheetSubReportParserRow valueOfTitle(final String category1, final String category2,
            final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_" + Strings.trim(title1)
                + "_" + Strings.trim(title2);
        for (final BalanceSheetSubReportParserRow r : BalanceSheetSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(BalanceSheet report, Decimal value);

}
