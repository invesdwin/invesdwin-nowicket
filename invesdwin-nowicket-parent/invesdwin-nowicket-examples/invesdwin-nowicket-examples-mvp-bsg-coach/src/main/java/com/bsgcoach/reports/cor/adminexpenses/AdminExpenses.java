package com.bsgcoach.reports.cor.adminexpenses;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class AdminExpenses extends AValueObject {

    //General Administrative Expenses
    private Decimal generalAdministrativeExpenses;
    //Other Corporate Overhead
    private Decimal otherCorporateOverhead;
    //Total
    private Decimal total;
    //Includes Ethics and/or Workforce Spending
    private Decimal includesEthicsAndOrWorkforceSpending;

    public class PerRegion {
        private final CompanyRegion companyRegion;

        //    Fixed Cost of Unused Plant-----------           N.A.
        //    E-A
        //    A-P
        //    L.A.
        private Decimal fixedCostOfUnusedPlant;

        //Allocation Percentages-----------------         N.A.
        //(overall)         E-A
        //    A-P
        //    L.A.
        private Percent allocationPercentagesOverall;

        //G and A Allocation--------------------          N.A.
        //    E-A
        //    A-P
        //    L.A.
        private Decimal gAndAAllocation;
        //Other Overhead Allocation--------------------           N.A.
        //    E-A
        //    A-P
        //    L.A.
        private Decimal otherOverheadAllocation;

        public PerRegion(final CompanyRegion companyRegion) {
            this.companyRegion = companyRegion;
        }

        public CompanyRegion getCompanyRegion() {
            return companyRegion;
        }

        public Decimal getFixedCostOfUnusedPlant() {
            return fixedCostOfUnusedPlant;
        }

        public void setFixedCostOfUnusedPlant(final Decimal fixedCostOfUnusedPlant) {
            this.fixedCostOfUnusedPlant = fixedCostOfUnusedPlant;
        }

        public Percent getAllocationPercentagesOverall() {
            return allocationPercentagesOverall;
        }

        public void setAllocationPercentagesOverall(final Percent allocationPercentagesOverall) {
            this.allocationPercentagesOverall = allocationPercentagesOverall;
        }

        public Decimal getGAndAAllocation() {
            return gAndAAllocation;
        }

        public void setGAndAAllocation(final Decimal gAndAAllocation) {
            this.gAndAAllocation = gAndAAllocation;
        }

        public Decimal getOtherOverheadAllocation() {
            return otherOverheadAllocation;
        }

        public void setOtherOverheadAllocation(final Decimal otherOverheadAllocation) {
            this.otherOverheadAllocation = otherOverheadAllocation;
        }

    }

    private final ALoadingCache<CompanyRegion, PerRegion> perRegion = new ALoadingCache<CompanyRegion, AdminExpenses.PerRegion>() {
        @Override
        protected PerRegion loadValue(final CompanyRegion key) {
            return new PerRegion(key);
        }
    };

    public Decimal getGeneralAdministrativeExpenses() {
        return generalAdministrativeExpenses;
    }

    public void setGeneralAdministrativeExpenses(final Decimal generalAdministrativeExpenses) {
        this.generalAdministrativeExpenses = generalAdministrativeExpenses;
    }

    public Decimal getOtherCorporateOverhead() {
        return otherCorporateOverhead;
    }

    public void setOtherCorporateOverhead(final Decimal otherCorporateOverhead) {
        this.otherCorporateOverhead = otherCorporateOverhead;
    }

    public Decimal getTotal() {
        return total;
    }

    public void setTotal(final Decimal total) {
        this.total = total;
    }

    public Decimal getIncludesEthicsAndOrWorkforceSpending() {
        return includesEthicsAndOrWorkforceSpending;
    }

    public void setIncludesEthicsAndOrWorkforceSpending(final Decimal includesEthicsAndOrWorkforceSpending) {
        this.includesEthicsAndOrWorkforceSpending = includesEthicsAndOrWorkforceSpending;
    }

    public PerRegion getPerRegion(final CompanyRegion companyRegion) {
        return perRegion.get(companyRegion);
    }

}
