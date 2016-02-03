package com.bsgcoach.reports.cor.whslemarketperformance;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class WhsleMarketPerformance extends AValueObject {

    private final CompanyRegion companyRegion;

    //    Revenues from Sales 
    private Decimal revenuesFromSales;
    //      Exchange Rate Adj 
    private Decimal exchangeRateAdj;
    //    Net Revenues    
    private Decimal netRevenues;

    public class Costs {
        //    Costs--------   COBPS
        private Decimal cobps;
        //        Whse
        private Decimal whse;
        //        Mktng
        private Decimal mktng;
        //        Admin
        private Decimal admin;

        public Decimal getCobps() {
            return cobps;
        }

        public void setCobps(final Decimal cobps) {
            this.cobps = cobps;
        }

        public Decimal getWhse() {
            return whse;
        }

        public void setWhse(final Decimal whse) {
            this.whse = whse;
        }

        public Decimal getMktng() {
            return mktng;
        }

        public void setMktng(final Decimal mktng) {
            this.mktng = mktng;
        }

        public Decimal getAdmin() {
            return admin;
        }

        public void setAdmin(final Decimal admin) {
            this.admin = admin;
        }

    }

    private final Costs costs = new Costs();

    //    Operating Profit    
    private Decimal operatingProfit;

    //    % of Pairs Sold 
    private Percent percentOfPairsSld;
    //    This Yr-------  Pairs Sold
    private Decimal pairsSoldThisYr;
    //        Mkt Share
    private Decimal mktShareThisYr;
    //        OpMargin
    private Decimal opMarginThisYr;
    //    Last Yr-------  Pairs Sold
    private Decimal pairsSoldLastYr;
    //        Mkt Share
    private Decimal mktShareLastYr;
    //        OpMargin
    private Decimal opMarginLastYr;

    public WhsleMarketPerformance(final CompanyRegion companyRegion) {
        this.companyRegion = companyRegion;
    }

    public Decimal getRevenuesFromSales() {
        return revenuesFromSales;
    }

    public void setRevenuesFromSales(final Decimal revenuesFromSales) {
        this.revenuesFromSales = revenuesFromSales;
    }

    public Decimal getExchangeRateAdj() {
        return exchangeRateAdj;
    }

    public void setExchangeRateAdj(final Decimal exchangeRateAdj) {
        this.exchangeRateAdj = exchangeRateAdj;
    }

    public Decimal getNetRevenues() {
        return netRevenues;
    }

    public void setNetRevenues(final Decimal netRevenues) {
        this.netRevenues = netRevenues;
    }

    public Decimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(final Decimal operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public Percent getPercentOfPairsSld() {
        return percentOfPairsSld;
    }

    public void setPercentOfPairsSld(final Percent percentOfPairsSld) {
        this.percentOfPairsSld = percentOfPairsSld;
    }

    public Decimal getPairsSoldThisYr() {
        return pairsSoldThisYr;
    }

    public void setPairsSoldThisYr(final Decimal pairsSoldThisYr) {
        this.pairsSoldThisYr = pairsSoldThisYr;
    }

    public Decimal getMktShareThisYr() {
        return mktShareThisYr;
    }

    public void setMktShareThisYr(final Decimal mktShareThisYr) {
        this.mktShareThisYr = mktShareThisYr;
    }

    public Decimal getOpMarginThisYr() {
        return opMarginThisYr;
    }

    public void setOpMarginThisYr(final Decimal opMarginThisYr) {
        this.opMarginThisYr = opMarginThisYr;
    }

    public Decimal getPairsSoldLastYr() {
        return pairsSoldLastYr;
    }

    public void setPairsSoldLastYr(final Decimal pairsSoldLastYr) {
        this.pairsSoldLastYr = pairsSoldLastYr;
    }

    public Decimal getMktShareLastYr() {
        return mktShareLastYr;
    }

    public void setMktShareLastYr(final Decimal mktShareLastYr) {
        this.mktShareLastYr = mktShareLastYr;
    }

    public Decimal getOpMarginLastYr() {
        return opMarginLastYr;
    }

    public void setOpMarginLastYr(final Decimal opMarginLastYr) {
        this.opMarginLastYr = opMarginLastYr;
    }

    public CompanyRegion getCompanyRegion() {
        return companyRegion;
    }

    public Costs getCosts() {
        return costs;
    }

}
