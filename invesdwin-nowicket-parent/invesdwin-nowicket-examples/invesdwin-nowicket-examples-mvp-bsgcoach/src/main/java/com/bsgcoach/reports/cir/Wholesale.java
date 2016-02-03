package com.bsgcoach.reports.cir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class Wholesale extends AValueObject {

    //    Price
    private Decimal price;
    //    S/Q
    private Decimal sQ;
    //    Models
    private Decimal models;
    //    Advertising
    private Decimal advertising;
    //    Rebates
    private Decimal rebates;
    //    Retailers
    private Decimal retailers;
    //    Support
    private Decimal support;
    //    Delivery
    private Decimal delivery;
    //    Appeal
    private Decimal appeal;
    //    Demand
    private Decimal demand;
    //    Gn(StkOut)
    private Decimal gnStkOut;
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

    public Decimal getAdvertising() {
        return advertising;
    }

    public void setAdvertising(final Decimal advertising) {
        this.advertising = advertising;
    }

    public Decimal getRebates() {
        return rebates;
    }

    public void setRebates(final Decimal rebates) {
        this.rebates = rebates;
    }

    public Decimal getRetailers() {
        return retailers;
    }

    public void setRetailers(final Decimal retailers) {
        this.retailers = retailers;
    }

    public Decimal getSupport() {
        return support;
    }

    public void setSupport(final Decimal support) {
        this.support = support;
    }

    public Decimal getDelivery() {
        return delivery;
    }

    public void setDelivery(final Decimal delivery) {
        this.delivery = delivery;
    }

    public Decimal getAppeal() {
        return appeal;
    }

    public void setAppeal(final Decimal appeal) {
        this.appeal = appeal;
    }

    public Decimal getDemand() {
        return demand;
    }

    public void setDemand(final Decimal demand) {
        this.demand = demand;
    }

    public Decimal getGnStkOut() {
        return gnStkOut;
    }

    public void setGnStkOut(final Decimal gnStkOut) {
        this.gnStkOut = gnStkOut;
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
