package com.bsgcoach.reports.cor.stockprice;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class StockPrice extends AValueObject {

    //    Current Stock Price
    private Decimal currentStockPrice;
    //    Beginning Shares Outstanding
    private Decimal beginningSharesOutstanding;
    //    Shares Issued
    private Decimal sharesIssued;
    //    Shares Retired
    private Decimal sharesRetired;
    //    Ending Shares Outstanding
    private Decimal endingSharesOutstanding;
    //    Stock Issue/Retire Price
    private Decimal stockIssueRetirePrice;

    public Decimal getCurrentStockPrice() {
        return currentStockPrice;
    }

    public void setCurrentStockPrice(final Decimal currentStockPrice) {
        this.currentStockPrice = currentStockPrice;
    }

    public Decimal getBeginningSharesOutstanding() {
        return beginningSharesOutstanding;
    }

    public void setBeginningSharesOutstanding(final Decimal beginningSharesOutstanding) {
        this.beginningSharesOutstanding = beginningSharesOutstanding;
    }

    public Decimal getSharesIssued() {
        return sharesIssued;
    }

    public void setSharesIssued(final Decimal sharesIssued) {
        this.sharesIssued = sharesIssued;
    }

    public Decimal getSharesRetired() {
        return sharesRetired;
    }

    public void setSharesRetired(final Decimal sharesRetired) {
        this.sharesRetired = sharesRetired;
    }

    public Decimal getEndingSharesOutstanding() {
        return endingSharesOutstanding;
    }

    public void setEndingSharesOutstanding(final Decimal endingSharesOutstanding) {
        this.endingSharesOutstanding = endingSharesOutstanding;
    }

    public Decimal getStockIssueRetirePrice() {
        return stockIssueRetirePrice;
    }

    public void setStockIssueRetirePrice(final Decimal stockIssueRetirePrice) {
        this.stockIssueRetirePrice = stockIssueRetirePrice;
    }

}
