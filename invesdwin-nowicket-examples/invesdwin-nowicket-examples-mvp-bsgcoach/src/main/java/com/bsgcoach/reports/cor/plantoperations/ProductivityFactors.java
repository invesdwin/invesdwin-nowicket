package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class ProductivityFactors extends AValueObject {

    //    Wage Change (%)
    private Percent wageChangePercent;
    //    Expected Incent Pay
    private Decimal expectedIncentPay;
    //    Incentive Pay (%)
    private Decimal incentivePayPercent;
    //    Total Percent (% of avg)
    private Percent totalCompPercentOfAvg;
    //    Best Pract (per wkr)
    private Decimal bestPractPerWkr;

    public Percent getWageChangePercent() {
        return wageChangePercent;
    }

    public void setWageChangePercent(final Percent wageChangePercent) {
        this.wageChangePercent = wageChangePercent;
    }

    public Decimal getExpectedIncentPay() {
        return expectedIncentPay;
    }

    public void setExpectedIncentPay(final Decimal expectedIncentPay) {
        this.expectedIncentPay = expectedIncentPay;
    }

    public Decimal getIncentivePayPercent() {
        return incentivePayPercent;
    }

    public void setIncentivePayPercent(final Decimal incentivePayPercent) {
        this.incentivePayPercent = incentivePayPercent;
    }

    public Percent getTotalCompPercentOfAvg() {
        return totalCompPercentOfAvg;
    }

    public void setTotalCompPercentOfAvg(final Percent totalCompPercentOfAvg) {
        this.totalCompPercentOfAvg = totalCompPercentOfAvg;
    }

    public Decimal getBestPractPerWkr() {
        return bestPractPerWkr;
    }

    public void setBestPractPerWkr(final Decimal bestPractPerWkr) {
        this.bestPractPerWkr = bestPractPerWkr;
    }

}
