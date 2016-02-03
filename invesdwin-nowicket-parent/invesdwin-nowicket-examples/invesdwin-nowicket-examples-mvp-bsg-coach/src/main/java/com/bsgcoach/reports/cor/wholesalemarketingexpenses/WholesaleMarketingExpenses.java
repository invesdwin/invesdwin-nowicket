package com.bsgcoach.reports.cor.wholesalemarketingexpenses;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class WholesaleMarketingExpenses extends AValueObject {

    private final CompanyRegion companyRegion;

    //    Allocation Percentages-----------------         N.A.
    //    (wholesale only)          E-A
    //              A-P
    //              L.A.
    private Percent allocationPercentagesWholesaleOnly;

    //    Advertising Expense Allocation      
    private Decimal advertisingExpenseAllocation;
    //    Rebate Redemption Expenses      
    private Decimal rebateRedemptionExpenses;
    //    Retailers Availabel-------      Current
    private Decimal retailersAvailableCurrent;
    //            Next Year
    private Decimal retailersAvailableNextYr;
    //    Number of Retialers Utilized      
    private Decimal numberOfRetailersUtilized;
    //    Retailer Support Expenses       
    private Decimal retailerSupportExpenses;
    //    On-Time Delivery Expenses       
    private Decimal onTimeDeliveryExpenses;
    //    Celebrity Endorsements      
    private Decimal celebrityEndorsements;
    //        Total   
    private Decimal total;

    public WholesaleMarketingExpenses(final CompanyRegion companyRegion) {
        this.companyRegion = companyRegion;
    }

    public Percent getAllocationPercentagesWholesaleOnly() {
        return allocationPercentagesWholesaleOnly;
    }

    public void setAllocationPercentagesWholesaleOnly(final Percent allocationPercentagesWholesaleOnly) {
        this.allocationPercentagesWholesaleOnly = allocationPercentagesWholesaleOnly;
    }

    public Decimal getAdvertisingExpenseAllocation() {
        return advertisingExpenseAllocation;
    }

    public void setAdvertisingExpenseAllocation(final Decimal advertisingExpenseAllocation) {
        this.advertisingExpenseAllocation = advertisingExpenseAllocation;
    }

    public Decimal getRebateRedemptionExpenses() {
        return rebateRedemptionExpenses;
    }

    public void setRebateRedemptionExpenses(final Decimal rebateRedemptionExpenses) {
        this.rebateRedemptionExpenses = rebateRedemptionExpenses;
    }

    public Decimal getRetailersAvailableCurrent() {
        return retailersAvailableCurrent;
    }

    public void setRetailersAvailableCurrent(final Decimal retailersAvailableCurrent) {
        this.retailersAvailableCurrent = retailersAvailableCurrent;
    }

    public Decimal getRetailersAvailableNextYr() {
        return retailersAvailableNextYr;
    }

    public void setRetailersAvailableNextYr(final Decimal retailersAvailableNextYr) {
        this.retailersAvailableNextYr = retailersAvailableNextYr;
    }

    public Decimal getNumberOfRetailersUtilized() {
        return numberOfRetailersUtilized;
    }

    public void setNumberOfRetailersUtilized(final Decimal numberOfRetailersUtilized) {
        this.numberOfRetailersUtilized = numberOfRetailersUtilized;
    }

    public Decimal getRetailerSupportExpenses() {
        return retailerSupportExpenses;
    }

    public void setRetailerSupportExpenses(final Decimal retailerSupportExpenses) {
        this.retailerSupportExpenses = retailerSupportExpenses;
    }

    public Decimal getOnTimeDeliveryExpenses() {
        return onTimeDeliveryExpenses;
    }

    public void setOnTimeDeliveryExpenses(final Decimal onTimeDeliveryExpenses) {
        this.onTimeDeliveryExpenses = onTimeDeliveryExpenses;
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
