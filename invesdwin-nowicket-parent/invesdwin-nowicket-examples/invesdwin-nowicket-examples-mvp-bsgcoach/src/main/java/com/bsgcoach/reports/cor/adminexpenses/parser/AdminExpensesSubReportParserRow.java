package com.bsgcoach.reports.cor.adminexpenses.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.adminexpenses.AdminExpenses;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum AdminExpensesSubReportParserRow {
    //    Fixed Cost of Unused Plant-----------           N.A.
    FixedCostOfUnusedPlantNA("Fixed Cost of Unused Plant-----------", null, "N.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setFixedCostOfUnusedPlant(value);
        }
    },
    //    E-A
    FixedCostOfUnusedPlantEA("Fixed Cost of Unused Plant-----------", null, "E-A") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setFixedCostOfUnusedPlant(value);
        }
    },
    //    A-P
    FixedCostOfUnusedPlantAP("Fixed Cost of Unused Plant-----------", null, "A-P") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setFixedCostOfUnusedPlant(value);
        }
    },
    //    L.A.
    FixedCostOfUnusedPlantLA("Fixed Cost of Unused Plant-----------", null, "L.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setFixedCostOfUnusedPlant(value);
        }
    },

    //General Administrative Expenses
    GeneralAdministrativeExpenses("General Administrative Expenses", null, null) {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.setGeneralAdministrativeExpenses(value);
        }
    },
    //Other Corporate Overhead
    OtherCorporateOverhead("Other Corporate Overhead", null, null) {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.setOtherCorporateOverhead(value);
        }
    },
    //Total
    Total("Other Corporate Overhead", "Total", null) {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.setTotal(value);
        }
    },
    //Includes Ethics and/or Workforce Spending
    IncludesEthicsAndOrWorkforceSpending("Includes Ethics and/or Workforce Spending", null, null) {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.setIncludesEthicsAndOrWorkforceSpending(value);
        }
    },
    //Allocation Percentages-----------------         N.A.
    AllocationPercentagesNA("Allocation Percentages-----------------", null, "N.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica)
                    .setAllocationPercentagesOverall(new Percent(value, PercentScale.PERCENT));
        }
    },
    //(overall)         E-A
    AllocationPercentagesEA("(overall)", null, "E-A") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica)
                    .setAllocationPercentagesOverall(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    A-P
    AllocationPercentagesAP("(overall)", null, "A-P") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific)
                    .setAllocationPercentagesOverall(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    L.A.
    AllocationPercentagesLA("(overall)", null, "L.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica)
                    .setAllocationPercentagesOverall(new Percent(value, PercentScale.PERCENT));
        }
    },

    //G and A Allocation--------------------          N.A.
    GAndAAllocationNA("G and A Allocation--------------------", null, "N.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setGAndAAllocation(value);
        }
    },
    //    E-A
    GAndAAllocationEA("G and A Allocation--------------------", null, "E-A") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setGAndAAllocation(value);
        }
    },
    //    A-P
    GAndAAllocationAP("G and A Allocation--------------------", null, "A-P") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setGAndAAllocation(value);
        }
    },
    //    L.A.
    GAndAAllocationLA("G and A Allocation--------------------", null, "L.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setGAndAAllocation(value);
        }
    },
    //Other Overhead Allocation--------------------           N.A.
    OtherOverheadAllocationNA("Other Overhead Allocation--------------------", null, "N.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.NorthAmerica).setOtherOverheadAllocation(value);
        }
    },
    //    E-A
    OtherOverheadAllocationEA("Other Overhead Allocation--------------------", null, "E-A") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.EuropeAfrica).setOtherOverheadAllocation(value);
        }
    },
    //    A-P
    OtherOverheadAllocationAP("Other Overhead Allocation--------------------", null, "A-P") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.AsiaPacific).setOtherOverheadAllocation(value);
        }
    },
    //    L.A.
    OtherOverheadAllocationLA("Other Overhead Allocation--------------------", null, "L.A.") {
        @Override
        public void parse(final AdminExpenses report, final Decimal value) {
            report.getPerRegion(CompanyRegion.LatinAmerica).setOtherOverheadAllocation(value);
        }
    };

    private String category1;
    private String category2;
    private String title2;

    AdminExpensesSubReportParserRow(final String category1, final String category2, final String title2) {
        this.category1 = category1;
        this.category2 = category2;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category1 + "_" + category2 + "_" + title2;
    }

    public static AdminExpensesSubReportParserRow valueOfTitle(final String category1, final String category2,
            final String title2) {
        final String trimmedTitle = Strings.trim(category1) + "_" + Strings.trim(category2) + "_"
                + Strings.trim(title2);
        for (final AdminExpensesSubReportParserRow r : AdminExpensesSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(AdminExpenses report, Decimal value);

}
