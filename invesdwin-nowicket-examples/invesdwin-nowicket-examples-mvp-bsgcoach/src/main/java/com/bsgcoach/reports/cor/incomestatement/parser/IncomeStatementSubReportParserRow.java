package com.bsgcoach.reports.cor.incomestatement.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.incomestatement.IncomeStatement;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum IncomeStatementSubReportParserRow {
    //N.A.------------    Gross Revenues----------        Internet
    NAGrossRevenuesInternet("N.A.------------", "Gross Revenues----------", null, "Internet") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getGrossRevenues().setInternet(value);
        }
    },
    //    Wholesale
    NAGrossRevenuesWholesale("N.A.------------", "Gross Revenues----------", null, "Wholesale") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getGrossRevenues().setWholesale(value);
        }
    },
    //    P-Label
    NAGrossRevenuesPLabel("N.A.------------", "Gross Revenues----------", null, "P-Label") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getGrossRevenues().setPLabel(value);
        }
    },
    //      Total
    NAGrossRevenuesTotal("N.A.------------", "Gross Revenues----------", null, "Total") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getGrossRevenues().setTotal(value);
        }
    },
    //Exchange Rate Adjustment
    NAExchangeRateAdjustment("N.A.------------", "Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setExchangeRateAdjustment(value);
        }
    },
    //Net Revenues
    NANetRevenues("N.A.------------", "Net Revenues", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setNetRevenues(value);
        }
    },
    //Operating Costs----------       COPS
    NAOperatingCostsCops("N.A.------------", "Operating Costs----------", null, "COPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getOperatingCosts().setCops(value);
        }
    },
    //    Whse
    NAOperatingCostsWhse("N.A.------------", "Operating Costs----------", null, "Whse") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getOperatingCosts().setWhse(value);
        }
    },
    //    Mktng
    NAOperatingCostsMktng("N.A.------------", "Operating Costs----------", null, "Mktng") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getOperatingCosts().setMktng(value);
        }
    },
    //    Admin
    NAOperatingCostsAdmin("N.A.------------", "Operating Costs----------", null, "Admin") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).getOperatingCosts().setAdmin(value);
        }
    },
    //Operating Profit
    NAOperatingProfit("N.A.------------", "Operating Profit", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setOperatingProfit(value);
        }
    },
    //
    //E-A------------ Gross Revenues----------        Internet
    EAGrossRevenuesInternet("E-A------------", "Gross Revenues----------", null, "Internet") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getGrossRevenues().setInternet(value);
        }
    },
    //    Wholesale
    EAGrossRevenuesWholesale("E-A------------", "Gross Revenues----------", null, "Wholesale") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getGrossRevenues().setWholesale(value);
        }
    },
    //    P-Label
    EAGrossRevenuesPLabel("E-A------------", "Gross Revenues----------", null, "P-Label") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getGrossRevenues().setPLabel(value);
        }
    },
    //      Total
    EAGrossRevenuesTotal("E-A------------", "Gross Revenues----------", null, "Total") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getGrossRevenues().setTotal(value);
        }
    },
    //Exchange Rate Adjustment
    EAExchangeRateAdjustment("E-A------------", "Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setExchangeRateAdjustment(value);
        }
    },
    //Net Revenues
    EANetRevenues("E-A------------", "Net Revenues", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setNetRevenues(value);
        }
    },
    //Operating Costs----------       COPS
    EAOperatingCostsCops("E-A------------", "Operating Costs----------", null, "COPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getOperatingCosts().setCops(value);
        }
    },
    //    Whse
    EAOperatingCostsWhse("E-A------------", "Operating Costs----------", null, "Whse") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getOperatingCosts().setWhse(value);
        }
    },
    //    Mktng
    EAOperatingCostsMktng("E-A------------", "Operating Costs----------", null, "Mktng") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getOperatingCosts().setMktng(value);
        }
    },
    //    Admin
    EAOperatingCostsAdmin("E-A------------", "Operating Costs----------", null, "Admin") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).getOperatingCosts().setAdmin(value);
        }
    },
    //Operating Profit
    EAOperatingProfit("E-A------------", "Operating Profit", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setOperatingProfit(value);
        }
    },
    //
    //A-P------------ Gross Revenues----------        Internet
    APGrossRevenuesInternet("A-P------------", "Gross Revenues----------", null, "Internet") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getGrossRevenues().setInternet(value);
        }
    },
    //    Wholesale
    APGrossRevenuesWholesale("A-P------------", "Gross Revenues----------", null, "Wholesale") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getGrossRevenues().setWholesale(value);
        }
    },
    //    P-Label
    APGrossRevenuesPLabel("A-P------------", "Gross Revenues----------", null, "P-Label") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getGrossRevenues().setPLabel(value);
        }
    },
    //      Total
    APGrossRevenuesTotal("A-P------------", "Gross Revenues----------", null, "Total") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getGrossRevenues().setTotal(value);
        }
    },
    //Exchange Rate Adjustment
    APExchangeRateAdjustment("A-P------------", "Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setExchangeRateAdjustment(value);
        }
    },
    //Net Revenues
    APNetRevenues("A-P------------", "Net Revenues", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setNetRevenues(value);
        }
    },
    //Operating Costs----------       COPS
    APOperatingCostsCops("A-P------------", "Operating Costs----------", null, "COPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getOperatingCosts().setCops(value);
        }
    },
    //    Whse
    APOperatingCostsWhse("A-P------------", "Operating Costs----------", null, "Whse") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getOperatingCosts().setWhse(value);
        }
    },
    //    Mktng
    APOperatingCostsMktng("A-P------------", "Operating Costs----------", null, "Mktng") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getOperatingCosts().setMktng(value);
        }
    },
    //    Admin
    APOperatingCostsAdmin("A-P------------", "Operating Costs----------", null, "Admin") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).getOperatingCosts().setAdmin(value);
        }
    },
    //Operating Profit
    APOperatingProfit("A-P------------", "Operating Profit", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setOperatingProfit(value);
        }
    },
    //
    //L.A.------------    Gross Revenues----------        Internet
    LAGrossRevenuesInternet("L.A.------------", "Gross Revenues----------", null, "Internet") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getGrossRevenues().setInternet(value);
        }
    },
    //    Wholesale
    LAGrossRevenuesWholesale("L.A.------------", "Gross Revenues----------", null, "Wholesale") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getGrossRevenues().setWholesale(value);
        }
    },
    //    P-Label
    LAGrossRevenuesPLabel("L.A.------------", "Gross Revenues----------", null, "P-Label") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getGrossRevenues().setPLabel(value);
        }
    },
    //      Total
    LAGrossRevenuesTotal("L.A.------------", "Gross Revenues----------", null, "Total") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getGrossRevenues().setTotal(value);
        }
    },
    //Exchange Rate Adjustment
    LAExchangeRateAdjustment("L.A.------------", "Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setExchangeRateAdjustment(value);
        }
    },
    //Net Revenues
    LANetRevenues("L.A.------------", "Net Revenues", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setNetRevenues(value);
        }
    },
    //Operating Costs----------       COPS
    LAOperatingCostsCops("L.A.------------", "Operating Costs----------", null, "COPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getOperatingCosts().setCops(value);
        }
    },
    //    Whse
    LAOperatingCostsWhse("L.A.------------", "Operating Costs----------", null, "Whse") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getOperatingCosts().setWhse(value);
        }
    },
    //    Mktng
    LAOperatingCostsMktng("L.A.------------", "Operating Costs----------", null, "Mktng") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getOperatingCosts().setMktng(value);
        }
    },
    //    Admin
    LAOperatingCostsAdmin("L.A.------------", "Operating Costs----------", null, "Admin") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).getOperatingCosts().setAdmin(value);
        }
    },
    //Operating Profit
    LAOperatingProfit("L.A.------------", "Operating Profit", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setOperatingProfit(value);
        }
    },
    //
    //Overall-------- Gross Revenues----------        Internet
    OverallGrossRevenuesInternet("Overall--------", "Gross Revenues----------", null, "Internet") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getGrossRevenues().setInternet(value);
        }
    },
    //    Wholesale
    OverallGrossRevenuesWholesale("Overall--------", "Gross Revenues----------", null, "Wholesale") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getGrossRevenues().setWholesale(value);
        }
    },
    //    P-Label
    OverallGrossRevenuesPLabel("Overall--------", "Gross Revenues----------", null, "P-Label") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getGrossRevenues().setPLabel(value);
        }
    },
    //      Total
    OverallGrossRevenuesTotal("Overall--------", "Gross Revenues----------", null, "Total") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getGrossRevenues().setTotal(value);
        }
    },
    //Exchange Rate Adjustment
    OverallExchangeRateAdjustment("Overall--------", "Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().setExchangeRateAdjustment(value);
        }
    },
    //Net Revenues
    OverallNetRevenues("Overall--------", "Net Revenues", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().setNetRevenues(value);
        }
    },
    //Operating Costs----------       COPS
    OverallOperatingCostsCops("Overall--------", "Operating Costs----------", null, "COPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getOperatingCosts().setCops(value);
        }
    },
    //    Whse
    OverallOperatingCostsWhse("Overall--------", "Operating Costs----------", null, "Whse") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getOperatingCosts().setWhse(value);
        }
    },
    //    Mktng
    OverallOperatingCostsMktng("Overall--------", "Operating Costs----------", null, "Mktng") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getOperatingCosts().setMktng(value);
        }
    },
    //    Admin
    OverallOperatingCostsAdmin("Overall--------", "Operating Costs----------", null, "Admin") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().getOperatingCosts().setAdmin(value);
        }
    },
    //Operating Profit
    OverallOperatingProfit("Overall--------", "Operating Profit", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.getOverall().setOperatingProfit(value);
        }
    },
    //
    //Interest Income (Expenses)
    InterestIncomeExpenses("Overall--------", "Interest Income (Expenses)", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //Refunds (Fines)
    RefundsFines("Overall--------", "Refunds (Fines)", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setRefundsFines(value);
        }
    },
    //Pre-Tax Profit (Loss)
    PreTaxProfitLoss("Overall--------", "Pre-Tax Profit (Loss)", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setPreTaxProfitLoss(value);
        }
    },
    //Taxable Income
    TaxableIncome("Overall--------", "Pre-Tax Profit (Loss)", "Taxable Income", null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setTaxableIncome(value);
        }
    },
    //Income Taxes
    IncomeTaxes("Overall--------", "Income Taxes", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setIncomeTaxes(value);
        }
    },
    //Net Profit (Loss)
    NetProfitLoss("Overall--------", "Net Profit (Loss)", null, null) {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setNetProfitLoss(value);
        }
    },
    //
    //This Yr-------  EPS
    ThisYrEps("Overall--------", "Net Profit (Loss)", "This Yr-------", "EPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //    DPS
    ThisYrDps("Overall--------", "Net Profit (Loss)", "This Yr-------", "DPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //Last Yr-------  EPS
    LastYrEps("Overall--------", "Net Profit (Loss)", "Last Yr-------", "EPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //    DPS
    LastYrDps("Overall--------", "Net Profit (Loss)", "Last Yr-------", "DPS") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //
    //Losses Not Yet Recovered------------            Current Yr
    LossesNotYetCoveredCurrentYr("Losses Not Yet Recovered------------", null, null, "Current Yr") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //    Year -1
    LossesNotYetCoveredYrMinus1("Losses Not Yet Recovered------------", null, null, "Year -1") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    },
    //    Year -2
    LossesNotYetCoveredYrMinus2("Losses Not Yet Recovered------------", null, null, "Year -2") {
        @Override
        public void parse(final IncomeStatement report, final Decimal value) {
            report.setInterestIncomeExpenses(value);
        }
    };

    private String category1;
    private String category2;
    private String title1;
    private String title2;

    IncomeStatementSubReportParserRow(final String category1, final String category2, final String title1,
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

    public static IncomeStatementSubReportParserRow valueOfTitle(final String category1, final String category2,
            final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_" + Strings.trim(title1)
                + "_" + Strings.trim(title2);
        for (final IncomeStatementSubReportParserRow r : IncomeStatementSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(IncomeStatement report, Decimal value);

}
