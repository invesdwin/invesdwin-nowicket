package com.bsgcoach.reports.cir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class Internet extends AValueObject {

    //    Price
    private Decimal price;
    //    S/Q
    private Decimal sQ;
    //    Models
    private Decimal models;
    //    Shipping
    private Decimal shipping;
    //    Advertising
    private Decimal advertising;
    //    Appeal
    private Decimal appeal;
    //    Orders
    private Decimal orders;
    //    Pairs Sold
    private Decimal pairsSold;
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

    public Decimal getModels() {
        return models;
    }

    public void setModels(final Decimal models) {
        this.models = models;
    }

    public Decimal getShipping() {
        return shipping;
    }

    public void setShipping(final Decimal shipping) {
        this.shipping = shipping;
    }

    public Decimal getAdvertising() {
        return advertising;
    }

    public void setAdvertising(final Decimal advertising) {
        this.advertising = advertising;
    }

    public Decimal getAppeal() {
        return appeal;
    }

    public void setAppeal(final Decimal appeal) {
        this.appeal = appeal;
    }

    public Decimal getOrders() {
        return orders;
    }

    public void setOrders(final Decimal orders) {
        this.orders = orders;
    }

    public Decimal getPairsSold() {
        return pairsSold;
    }

    public void setPairsSold(final Decimal pairsSold) {
        this.pairsSold = pairsSold;
    }

    public Percent getPercentShare() {
        return percentShare;
    }

    public void setPercentShare(final Percent percentShare) {
        this.percentShare = percentShare;
    }

}
