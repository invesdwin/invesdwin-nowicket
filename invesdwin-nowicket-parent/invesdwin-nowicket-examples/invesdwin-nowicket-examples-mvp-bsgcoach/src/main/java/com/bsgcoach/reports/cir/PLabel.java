package com.bsgcoach.reports.cir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class PLabel extends AValueObject {

    //    Price
    private Decimal price;
    //    S/Q
    private Decimal sQ;
    //    Offered
    private Decimal offered;
    //    Sold
    private Decimal sold;
    //    % Share
    private Percent percentShare;

    public Decimal getPrice() {
        return price;
    }

    public void setPrice(final Decimal price) {
        this.price = price;
    }

    public Decimal getSQ() {
        return sQ;
    }

    public void setSQ(final Decimal sQ) {
        this.sQ = sQ;
    }

    public Decimal getOffered() {
        return offered;
    }

    public void setOffered(final Decimal offered) {
        this.offered = offered;
    }

    public Decimal getSold() {
        return sold;
    }

    public void setSold(final Decimal sold) {
        this.sold = sold;
    }

    public Percent getPercentShare() {
        return percentShare;
    }

    public void setPercentShare(final Percent percentShare) {
        this.percentShare = percentShare;
    }

}
