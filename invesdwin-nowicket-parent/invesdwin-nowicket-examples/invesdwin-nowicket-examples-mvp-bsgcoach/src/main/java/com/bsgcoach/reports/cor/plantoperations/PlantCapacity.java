package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class PlantCapacity extends AValueObject {

    //    Beginning of Yr
    private Decimal beginningOfYr;
    //    Const (from last yr)
    private Decimal constFromLastYr;
    //    Purchased (this yr)
    private Decimal purchasedThisYr;
    //    Sold (this yr)
    private Decimal soldThisYr;
    //    Available This Yr
    private Decimal availableThisYr;
    //      % of Total Capacity
    private Percent percentOfTotalCapacity;
    //    Const (for next yr)
    private Decimal constForNextYr;
    //      Cost ($000s)
    private Decimal costDollar000s;

    public Decimal getBeginningOfYr() {
        return beginningOfYr;
    }

    public void setBeginningOfYr(final Decimal beginningOfYr) {
        this.beginningOfYr = beginningOfYr;
    }

    public Decimal getConstFromLastYr() {
        return constFromLastYr;
    }

    public void setConstFromLastYr(final Decimal constFromLastYr) {
        this.constFromLastYr = constFromLastYr;
    }

    public Decimal getPurchasedThisYr() {
        return purchasedThisYr;
    }

    public void setPurchasedThisYr(final Decimal purchasedThisYr) {
        this.purchasedThisYr = purchasedThisYr;
    }

    public Decimal getSoldThisYr() {
        return soldThisYr;
    }

    public void setSoldThisYr(final Decimal soldThisYr) {
        this.soldThisYr = soldThisYr;
    }

    public Decimal getAvailableThisYr() {
        return availableThisYr;
    }

    public void setAvailableThisYr(final Decimal availableThisYr) {
        this.availableThisYr = availableThisYr;
    }

    public Percent getPercentOfTotalCapacity() {
        return percentOfTotalCapacity;
    }

    public void setPercentOfTotalCapacity(final Percent percentOfTotalCapacity) {
        this.percentOfTotalCapacity = percentOfTotalCapacity;
    }

    public Decimal getConstForNextYr() {
        return constForNextYr;
    }

    public void setConstForNextYr(final Decimal constForNextYr) {
        this.constForNextYr = constForNextYr;
    }

    public Decimal getCostDollar000s() {
        return costDollar000s;
    }

    public void setCostDollar000s(final Decimal costDollar000s) {
        this.costDollar000s = costDollar000s;
    }

    public Decimal getCapacity() {
        //                Regular capacity in the region  COR/region Plant Operations/Plant Capacity/Beginning of Yr
        final Decimal regularCapacity = getBeginningOfYr();
        return regularCapacity;
    }

    public Decimal getMaxCapacity() {
        //                Regular capacity in the region  COR/region Plant Operations/Plant Capacity/Beginning of Yr
        final Decimal regularCapacity = getBeginningOfYr();
        if (regularCapacity == null) {
            return null;
        }
        //        Maximum capacity in the region  =CAPACITY(region)*1.2
        final Decimal maximumCapacity = regularCapacity.multiply(new Decimal("1.2"));
        return maximumCapacity;
    }

}
