package com.bsgcoach.reports.cor.internetmarketingexpenses;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class InternetMarketingExpenses extends AValueObject {

    //    Web Site Maintenance and Support                2010
    private Decimal webSiteMaintenanceAndSupport;

    //    Allocation Percentages-----------------         N.A.
    //    (within region)           E-A
    //              A-P
    //              L.A.
    //              Global
    private Percent globalAllocationPercentageWithinRegion;

    public class PerRegion {

        private final CompanyRegion companyRegion;

        private Percent allocationPercentageWithinRegion;
        //  Allocation Percentages-----------------         N.A.
        //    (internet only)           E-A
        //              A-P
        //              L.A.
        private Percent allocationPercentageInternetOnly;

        //        Advertising Expense Allocation
        private Decimal advertisingExpenseAllocation;
        //        Web Site Maintenance and Sup
        private Decimal webSiteMaintenanceAndSup;
        //        Order Processing and Delivery
        private Decimal orderProcessingAndDelivery;
        //        Celebrity Endorsements
        private Decimal celebrityEndorsements;
        //            Total
        private Decimal total;

        public PerRegion(final CompanyRegion companyRegion) {
            this.companyRegion = companyRegion;
        }

        public Percent getAllocationPercentageWithinRegion() {
            return allocationPercentageWithinRegion;
        }

        public void setAllocationPercentageWithinRegion(final Percent allocationPercentageWithinRegion) {
            this.allocationPercentageWithinRegion = allocationPercentageWithinRegion;
        }

        public Percent getAllocationPercentageInternetOnly() {
            return allocationPercentageInternetOnly;
        }

        public void setAllocationPercentageInternetOnly(final Percent allocationPercentageInternetOnly) {
            this.allocationPercentageInternetOnly = allocationPercentageInternetOnly;
        }

        public Decimal getAdvertisingExpenseAllocation() {
            return advertisingExpenseAllocation;
        }

        public void setAdvertisingExpenseAllocation(final Decimal advertisingExpenseAllocation) {
            this.advertisingExpenseAllocation = advertisingExpenseAllocation;
        }

        public Decimal getWebSiteMaintenanceAndSup() {
            return webSiteMaintenanceAndSup;
        }

        public void setWebSiteMaintenanceAndSup(final Decimal webSiteMaintenanceAndSup) {
            this.webSiteMaintenanceAndSup = webSiteMaintenanceAndSup;
        }

        public Decimal getOrderProcessingAndDelivery() {
            return orderProcessingAndDelivery;
        }

        public void setOrderProcessingAndDelivery(final Decimal orderProcessingAndDelivery) {
            this.orderProcessingAndDelivery = orderProcessingAndDelivery;
        }

        public Decimal getCelebrityEndorsements() {
            return celebrityEndorsements;
        }

        public void setCelebrityEndorsements(final Decimal celebrityEndorsements) {
            this.celebrityEndorsements = celebrityEndorsements;
        }

        public Decimal getTotal() {
            return total;
        }

        public void setTotal(final Decimal total) {
            this.total = total;
        }

        public CompanyRegion getCompanyRegion() {
            return companyRegion;
        }

    }

    private final ALoadingCache<CompanyRegion, PerRegion> perRegion = new ALoadingCache<CompanyRegion, PerRegion>() {

        @Override
        protected PerRegion loadValue(final CompanyRegion key) {
            return new PerRegion(key);
        }
    };

    public Decimal getWebSiteMaintenanceAndSupport() {
        return webSiteMaintenanceAndSupport;
    }

    public void setWebSiteMaintenanceAndSupport(final Decimal webSiteMaintenanceAndSupport) {
        this.webSiteMaintenanceAndSupport = webSiteMaintenanceAndSupport;
    }

    public Percent getGlobalAllocationPercentageWithinRegion() {
        return globalAllocationPercentageWithinRegion;
    }

    public void setGlobalAllocationPercentageWithinRegion(final Percent globalAllocationPercentageWithinRegion) {
        this.globalAllocationPercentageWithinRegion = globalAllocationPercentageWithinRegion;
    }

    public PerRegion getPerRegion(final CompanyRegion companyRegion) {
        return perRegion.get(companyRegion);
    }

}
