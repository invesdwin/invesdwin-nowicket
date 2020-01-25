package com.bsgcoach.reports.cor.wholesalemarketingexpenses.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.util.Err;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum WholesaleMarketingExpensesSubReportParserRow {
    //    Allocation Percentages-----------------         N.A.
    AllocationPercentagesNA("Allocation Percentages-----------------", null, null, "N.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica)
                    .setAllocationPercentagesWholesaleOnly(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    (wholesale only)          E-A
    AllocationPercentagesEA("(wholesale only)", null, null, "E-A") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica)
                    .setAllocationPercentagesWholesaleOnly(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              A-P
    AllocationPercentagesAP("(wholesale only)", null, null, "A-P") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific)
                    .setAllocationPercentagesWholesaleOnly(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              L.A.
    AllocationPercentagesLA("(wholesale only)", null, null, "L.A.") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica)
                    .setAllocationPercentagesWholesaleOnly(new Percent(value, PercentScale.PERCENT));
        }
    },

    //  N.A.----------- Advertising Expense Allocation
    NAAdvertisingExpenseAllocation("N.A.-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Rebate Redemption Expenses
    NARebateRedemptionExpenses("N.A.-----------", "Rebate Redemption Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setRebateRedemptionExpenses(value);
        }
    },
    //      Retailers Availabel-------      Current
    NARetailersAvailableCurrent("N.A.-----------", "Retailers Availabel-------", null, "Current") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setRetailersAvailableCurrent(value);
        }
    },
    //              Next Year
    NARetailersAvailableNextYr("N.A.-----------", "Retailers Availabel-------", null, "Next Year") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setRetailersAvailableNextYr(value);
        }
    },
    //      Number of Retialers Utilized
    NANumberOfRetailersUtilized("N.A.-----------", "Number of Retialers Utilized", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setNumberOfRetailersUtilized(value);
        }
    },
    //      Retailer Support Expenses
    NARetailerSupportExpenses("N.A.-----------", "Retailer Support Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setRetailerSupportExpenses(value);
        }
    },
    //      On-Time Delivery Expenses
    NAOnTimeDeliveryExpenses("N.A.-----------", "On-Time Delivery Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setOnTimeDeliveryExpenses(value);
        }
    },
    //      Celebrity Endorsements
    NACelebrityEndorsements("N.A.-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setCelebrityEndorsements(value);
        }
    },
    //          Total
    NATotal("N.A.-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.NorthAmerica).setTotal(value);
        }
    },

    //  E-A-----------  Advertising Expense Allocation
    EAAdvertisingExpenseAllocation("E-A-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Rebate Redemption Expenses
    EARebateRedemptionExpenses("E-A-----------", "Rebate Redemption Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setRebateRedemptionExpenses(value);
        }
    },
    //      Retailers Availabel-------      Current
    EARetailersAvailableCurrent("E-A-----------", "Retailers Availabel-------", null, "Current") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setRetailersAvailableCurrent(value);
        }
    },
    //              Next Year
    EARetailersAvailableNextYr("E-A-----------", "Retailers Availabel-------", null, "Next Year") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setRetailersAvailableNextYr(value);
        }
    },
    //      Number of Retialers Utilized
    EANumberOfRetailersUtilized("E-A-----------", "Number of Retialers Utilized", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setNumberOfRetailersUtilized(value);
        }
    },
    //      Retailer Support Expenses
    EARetailerSupportExpenses("E-A-----------", "Retailer Support Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setRetailerSupportExpenses(value);
        }
    },
    //      On-Time Delivery Expenses
    EAOnTimeDeliveryExpenses("E-A-----------", "On-Time Delivery Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setOnTimeDeliveryExpenses(value);
        }
    },
    //      Celebrity Endorsements
    EACelebrityEndorsements("E-A-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setCelebrityEndorsements(value);
        }
    },
    //          Total
    EATotal("E-A-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.EuropeAfrica).setTotal(value);
        }
    },
    //
    //  A-P-----------  Advertising Expense Allocation
    APAdvertisingExpenseAllocation("A-P-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Rebate Redemption Expenses
    APRebateRedemptionExpenses("A-P-----------", "Rebate Redemption Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setRebateRedemptionExpenses(value);
        }
    },
    //      Retailers Availabel-------      Current
    APRetailersAvailableCurrent("A-P-----------", "Retailers Availabel-------", null, "Current") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setRetailersAvailableCurrent(value);
        }
    },
    //              Next Year
    APRetailersAvailableNextYr("A-P-----------", "Retailers Availabel-------", null, "Next Year") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setRetailersAvailableNextYr(value);
        }
    },
    //      Number of Retialers Utilized
    APNumberOfRetailersUtilized("A-P-----------", "Number of Retialers Utilized", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setNumberOfRetailersUtilized(value);
        }
    },
    //      Retailer Support Expenses
    APRetailerSupportExpenses("A-P-----------", "Retailer Support Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setRetailerSupportExpenses(value);
        }
    },
    //      On-Time Delivery Expenses
    APOnTimeDeliveryExpenses("A-P-----------", "On-Time Delivery Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setOnTimeDeliveryExpenses(value);
        }
    },
    //      Celebrity Endorsements
    APCelebrityEndorsements("A-P-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setCelebrityEndorsements(value);
        }
    },
    //          Total
    APTotal("A-P-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setTotal(value);
        }
    },

    //  L.A.----------- Advertising Expense Allocation
    LAAdvertisingExpenseAllocation("L.A.-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Rebate Redemption Expenses
    LARebateRedemptionExpenses("L.A.-----------", "Rebate Redemption Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setRebateRedemptionExpenses(value);
        }
    },
    //      Retailers Availabel-------      Current
    LARetailersAvailableCurrent("L.A.-----------", "Retailers Availabel-------", null, "Current") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setRetailersAvailableCurrent(value);
        }
    },
    //              Next Year
    LARetailersAvailableNextYr("L.A.-----------", "Retailers Availabel-------", null, "Next Year") {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setRetailersAvailableNextYr(value);
        }
    },
    //      Number of Retialers Utilized
    LANumberOfRetailersUtilized("L.A.-----------", "Number of Retialers Utilized", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setNumberOfRetailersUtilized(value);
        }
    },
    //      Retailer Support Expenses
    LARetailerSupportExpenses("L.A.-----------", "Retailer Support Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setRetailerSupportExpenses(value);
        }
    },
    //      On-Time Delivery Expenses
    LAOnTimeDeliveryExpenses("L.A.-----------", "On-Time Delivery Expenses", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setOnTimeDeliveryExpenses(value);
        }
    },
    //      Celebrity Endorsements
    LACelebrityEndorsements("L.A.-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.LatinAmerica).setCelebrityEndorsements(value);
        }
    },
    //          Total
    LATotal("L.A.-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final CompanyOperatingReports report, final Decimal value) {
            report.getWholesaleMarketingExpenses(CompanyRegion.AsiaPacific).setTotal(value);
        }
    };

    private String category1;
    private String category2;
    private String title1;
    private String title2;

    WholesaleMarketingExpensesSubReportParserRow(final String category1, final String category2, final String title1,
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

    public static WholesaleMarketingExpensesSubReportParserRow valueOfTitle(final String category1,
            final String category2, final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_" + Strings.trim(title1)
                + "_" + Strings.trim(title2);
        for (final WholesaleMarketingExpensesSubReportParserRow r : WholesaleMarketingExpensesSubReportParserRow
                .values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(CompanyOperatingReports report, Decimal value);

}
