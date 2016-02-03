package com.bsgcoach.reports.cor.internetmarketperformance;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.reports.cor.whslemarketperformance.WhsleMarketPerformance;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class InternetMarketPerformance extends WhsleMarketPerformance {

    //    Customer-Paid Fees  
    private Decimal customerPaidFees;
    //    Gross Revenues  
    private Decimal grossRevenues;

    public InternetMarketPerformance(final CompanyRegion companyRegion) {
        super(companyRegion);
    }

    public Decimal getCustomerPaidFees() {
        return customerPaidFees;
    }

    public void setCustomerPaidFees(final Decimal customerPaidFees) {
        this.customerPaidFees = customerPaidFees;
    }

    public Decimal getGrossRevenues() {
        return grossRevenues;
    }

    public void setGrossRevenues(final Decimal grossRevenues) {
        this.grossRevenues = grossRevenues;
    }

}
