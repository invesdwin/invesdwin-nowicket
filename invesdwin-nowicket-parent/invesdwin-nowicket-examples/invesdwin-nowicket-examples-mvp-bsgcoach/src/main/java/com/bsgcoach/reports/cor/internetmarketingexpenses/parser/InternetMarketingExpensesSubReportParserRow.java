package com.bsgcoach.reports.cor.internetmarketingexpenses.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.internetmarketingexpenses.InternetMarketingExpenses;
import com.bsgcoach.reports.cor.internetmarketingexpenses.InternetMarketingExpenses.PerRegion;
import com.bsgcoach.util.Err;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum InternetMarketingExpensesSubReportParserRow {
    //Web Site Maintenance and Support
    WebSiteMaintenanceAndSupport("Web Site Maintenance and Support", null, null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.setWebSiteMaintenanceAndSupport(value);
        }
    },
    //    Allocation Percentages-----------------         N.A.
    AllocationPercentagesWithinRegionNA("Allocation Percentages-----------------", null, null, "N.A.") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            final PerRegion perRegion = report.getPerRegion(CompanyRegion.NorthAmerica);
            if (perRegion.getAllocationPercentageWithinRegion() == null) {
                perRegion.setAllocationPercentageWithinRegion(new Percent(value, PercentScale.PERCENT));
            } else {
                Assertions.assertThat(perRegion.getAllocationPercentageInternetOnly()).isNull();
                perRegion.setAllocationPercentageInternetOnly(new Percent(value, PercentScale.PERCENT));
            }
        }
    },
    //    (within region)           E-A
    AllocationPercentagesWithinRegionEA("(within region)", null, null, "E-A") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica)
                    .setAllocationPercentageWithinRegion(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              A-P
    AllocationPercentagesWithinRegionAP("(within region)", null, null, "A-P") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific)
                    .setAllocationPercentageWithinRegion(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              L.A.
    AllocationPercentagesWithinRegionLA("(within region)", null, null, "L.A.") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica)
                    .setAllocationPercentageWithinRegion(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              Global
    AllocationPercentagesWithinRegionGlobal("(within region)", null, null, "Global") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.setGlobalAllocationPercentageWithinRegion(new Percent(value, PercentScale.PERCENT));
        }
    },
    //  Allocation Percentages-----------------         N.A.
    //    (internet only)           E-A
    AllocationPercentagesInternetOnlyEA("(internet only)", null, null, "E-A") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica)
                    .setAllocationPercentageInternetOnly(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              A-P
    AllocationPercentagesInternetOnlyAP("(internet only)", null, null, "A-P") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific)
                    .setAllocationPercentageInternetOnly(new Percent(value, PercentScale.PERCENT));
        }
    },
    //              L.A.
    AllocationPercentagesInternetOnlyLA("(internet only)", null, null, "L.A.") {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica)
                    .setAllocationPercentageInternetOnly(new Percent(value, PercentScale.PERCENT));
        }
    },

    //  N.A.----------- Advertising Expense Allocation
    NAAdvertisingExpenseAllocation("N.A.-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Web Site Maintenance and Sup
    NAWebSiteMaintenanceAndSup("N.A.-----------", "Web Site Maintenance and Sup", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setWebSiteMaintenanceAndSup(value);
        }
    },
    //      Order Processing and Delivery
    NAOrderProcessingAndDelivery("N.A.-----------", "Order Processing and Delivery", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setOrderProcessingAndDelivery(value);
        }
    },
    //      Celebrity Endorsements
    NACelebrityEndorsements("N.A.-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setCelebrityEndorsements(value);
        }
    },
    //          Total
    NATotal("N.A.-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setAdvertisingExpenseAllocation(value);
        }
    },
    //
    //  E-A-----------  Advertising Expense Allocation
    EAAdvertisingExpenseAllocation("E-A-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Web Site Maintenance and Sup
    EAWebSiteMaintenanceAndSup("E-A-----------", "Web Site Maintenance and Sup", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setWebSiteMaintenanceAndSup(value);
        }
    },
    //      Order Processing and Delivery
    EAOrderProcessingAndDelivery("E-A-----------", "Order Processing and Delivery", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setOrderProcessingAndDelivery(value);
        }
    },
    //      Celebrity Endorsements
    EACelebrityEndorsements("E-A-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setCelebrityEndorsements(value);
        }
    },
    //          Total
    EATotal("E-A-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setAdvertisingExpenseAllocation(value);
        }
    },

    //  A-P-----------  Advertising Expense Allocation
    APAdvertisingExpenseAllocation("A-P-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Web Site Maintenance and Sup
    APWebSiteMaintenanceAndSup("A-P-----------", "Web Site Maintenance and Sup", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setWebSiteMaintenanceAndSup(value);
        }
    },
    //      Order Processing and Delivery
    APOrderProcessingAndDelivery("A-P-----------", "Order Processing and Delivery", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setOrderProcessingAndDelivery(value);
        }
    },
    //      Celebrity Endorsements
    APCelebrityEndorsements("A-P-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setCelebrityEndorsements(value);
        }
    },
    //          Total
    APTotal("A-P-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setAdvertisingExpenseAllocation(value);
        }
    },

    //  L.A.----------- Advertising Expense Allocation
    LAAdvertisingExpenseAllocation("L.A.-----------", "Advertising Expense Allocation", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setAdvertisingExpenseAllocation(value);
        }
    },
    //      Web Site Maintenance and Sup
    LAWebSiteMaintenanceAndSup("L.A.-----------", "Web Site Maintenance and Sup", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setWebSiteMaintenanceAndSup(value);
        }
    },
    //      Order Processing and Delivery
    LAOrderProcessingAndDelivery("L.A.-----------", "Order Processing and Delivery", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setOrderProcessingAndDelivery(value);
        }
    },
    //      Celebrity Endorsements
    LACelebrityEndorsements("L.A.-----------", "Celebrity Endorsements", null, null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setCelebrityEndorsements(value);
        }
    },
    //          Total
    LATotal("L.A.-----------", "Celebrity Endorsements", "Total", null) {
        @Override
        public void parse(final InternetMarketingExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setAdvertisingExpenseAllocation(value);
        }
    };

    private String category1;
    private String category2;
    private String title1;
    private String title2;

    InternetMarketingExpensesSubReportParserRow(final String category1, final String category2, final String title1,
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

    public static InternetMarketingExpensesSubReportParserRow valueOfTitle(final String category1,
            final String category2, final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_" + Strings.trim(title1)
                + "_" + Strings.trim(title2);
        for (final InternetMarketingExpensesSubReportParserRow r : InternetMarketingExpensesSubReportParserRow
                .values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(InternetMarketingExpenses report, Decimal value);

}
