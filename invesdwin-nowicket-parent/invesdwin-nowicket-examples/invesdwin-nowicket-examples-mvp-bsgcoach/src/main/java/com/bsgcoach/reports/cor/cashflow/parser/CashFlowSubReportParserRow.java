package com.bsgcoach.reports.cor.cashflow.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.cashflow.CashFlow;
import com.bsgcoach.util.Err;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum CashFlowSubReportParserRow {
    //    Beginning Cash Balance
    BeginningCashBalance("Beginning Cash Balance", null, null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.setBeginningCashBalance(value);
        }
    },
    //
    //    Cash Inflows----------------        Receipts from Sales
    CashInflowsReceiptsFromSales("Cash Inflows----------------", null, "Receipts from Sales", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setReceiptsFromSales(value);
        }

    },
    //            Bank----------  1-Year
    CashInflowsBankLoans1Year("Cash Inflows----------------", null, "Bank----------", "1-Year") {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setBankLoans1Year(value);
        }
    },
    //              Loans 5-Year
    CashInflowsBankLoans5Year("Cash Inflows----------------", null, "Loans", "5-Year") {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setBankLoans5Year(value);
        }
    },
    //                10-Year
    CashInflowsBankLoans10Year("Cash Inflows----------------", null, "Loans", "10-Year") {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setBankLoands10Year(value);
        }
    },
    //            Stock Issue
    CashInflowsStockIssue("Cash Inflows----------------", null, "Stock Issue", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setStockIssue(value);
        }
    },
    //            Sale of Existing Plant
    CashInflowsSaleOfExistingPlant("Cash Inflows----------------", null, "Sale of Existing Plant", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setSaleOfExistingPlant(value);
        }
    },
    //            Overdraft Loan
    CashInflowsOverdraftLoan("Cash Inflows----------------", null, "Overdraft Loan", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setOverdraftLoan(value);
        }
    },
    //            Interest on Cash Bal
    CashInflowsInterestOnCashBal("Cash Inflows----------------", null, "Interest on Cash Bal", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setInterestOnCashBal(value);
        }
    },
    //            Refund
    CashInflowsRefund("Cash Inflows----------------", null, "Refund", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashInflows().setRefund(value);
        }
    },
    //
    //    Total Cash Available from All Sources
    TotalCashAvailableFromAllSources("Total Cash Available from All Sources", null, null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.setTotalCashAvailableFromAllSources(value);
        }
    },
    //
    //    Cash----------  Payments to Materials Suppliers
    CashOutlaysPaymentsToMaterialsSuppliers("Cash----------", "Payments to Materials Suppliers", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setPaymentsToMaterialsSuppliers(value);
        }
    },
    //      Outlays   Production Expenses
    CashOutlaysProductionExpenses("Outlays", "Production Expenses", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.setBeginningCashBalance(value);
        }
    },
    //        Distribution and Warehouse
    CashOutlaysDistributionAndWarehouse("Outlays", "Distribution and Warehouse", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setDistributionAndWarehouse(value);
        }
    },
    //        Marketing and Administrative
    CashOutlaysMarketingAndAdministrative("Outlays", "Marketing and Administrative", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setMarketingAndAdministrative(value);
        }
    },
    //        Capital-------  Capacity Purchase
    CashOutlaysCapitalCapacityPurchase("Outlays", "Capital-------", "Capacity Purchase", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setCapitalCapacityPurchase(value);
        }
    },
    //            Plant Upgrades
    CashOutlaysCapitalPlantUpgrades("Outlays", "Capital-------", "Plant Upgrades", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setCapitalPlantUpgrades(value);
        }
    },
    //            Capacity Construct
    CashOutlaysCapitalCapacityConstruct("Outlays", "Capital-------", "Capacity Construct", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setCapitalCapacityConstruct(value);
        }
    },
    //        Principal-----------    Overdraft Loan
    CashOutlaysPrincipalRepayOverdraftLoan("Outlays", "Principal-----------", "Overdraft Loan", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().getPrincipalRepay().setOverdraftLoan(value);
        }
    },
    //          Repay 1-Year Loan
    CashOutlaysPrincipalRepay1YearLoan("Outlays", "Repay", "1-Year Loan", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().getPrincipalRepay().setLoan1Year(value);
        }
    },
    //            5-Year Loans
    CashOutlaysPrincipalRepay5YearLoans("Outlays", "Repay", "5-Year Loans", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().getPrincipalRepay().setLoans5Year(value);
        }
    },
    //            10-Year Loans
    CashOutlaysPrincipalRepay10YearLoans("Outlays", "Repay", "10-Year Loans", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().getPrincipalRepay().setLoans10Year(value);
        }
    },
    //        Interest--------    Overdraft Loan
    CashOutlaysInterestPmtsOverdraftLoan("Outlays", "Interest--------", "Overdraft Loan", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().getInterestPmts().setOverdraftLoan(value);
        }
    },
    //          Pmts  Bank Loans
    CashOutlaysInterestPmtsBankLoans("Outlays", "Pmts", "Bank Loans", null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().getInterestPmts().setBankLoans(value);
        }
    },
    //        Stock Repurchase
    CashOutlaysStockRepurchase("Outlays", "Stock Repurchase", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setStockRepurchase(value);
        }
    },
    //        Income Tax Payments
    CashOutlaysIncomeTaxPayments("Outlays", "Income Tax Payments", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setIncomeTaxPayments(value);
        }
    },
    //        Dividend Payments
    CashOutlaysDividendPayments("Outlays", "Dividend Payments", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setDividendPayments(value);
        }
    },
    //        Fine
    CashOutlaysFine("Outlays", "Fine", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setFine(value);
        }
    },
    //        Charitable Contributions
    CashOutlaysCharitableContributions("Outlays", "Charitable Contributions", null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.getCashOutlays().setCharitableContributions(value);
        }
    },
    //    Total Cash Outlays
    TotalCashOutlays("Total Cash Outlays", null, null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.setTotalCashOutlays(value);
        }
    },
    //
    //    Net Cash Balance
    NetCashBalance("Net Cash Balance", null, null, null) {
        @Override
        public void parse(final CashFlow report, final Decimal value) {
            report.setNetCashBalance(value);
        }
    };

    private String category1;
    private String category2;
    private String title1;
    private String title2;

    CashFlowSubReportParserRow(final String category1, final String category2, final String title1,
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

    public static CashFlowSubReportParserRow valueOfTitle(final String category1, final String category2,
            final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_" + Strings.trim(title1)
                + "_" + Strings.trim(title2);
        for (final CashFlowSubReportParserRow r : CashFlowSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(CashFlow report, Decimal value);

}
