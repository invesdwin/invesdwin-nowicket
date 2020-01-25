package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class BalanceSheet extends AValueObject {

    //    Cash On Hand
    private Decimal cashOnHand;
    //    Current Assets
    private Decimal currentAssets;
    //    Total Assets
    private Decimal totalAssets;
    //    Current Liabilities
    private Decimal currentLiabilities;
    //    Long-Term Debt
    private Decimal longTermDebt;
    //    Beginning Equity
    private Decimal beginningEquity;
    //    Stock Sale (Purch)
    private Decimal stockSalePurch;
    //    Earnings Retained
    private Decimal earningsRetained;
    //    Ending Equity
    private Decimal endingEquity;

    public Decimal getCashOnHand() {
        return cashOnHand;
    }

    public void setCashOnHand(final Decimal cashOnHand) {
        this.cashOnHand = cashOnHand;
    }

    public Decimal getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(final Decimal currentAssets) {
        this.currentAssets = currentAssets;
    }

    public Decimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(final Decimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Decimal getCurrentLiabilities() {
        return currentLiabilities;
    }

    public void setCurrentLiabilities(final Decimal currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    public Decimal getLongTermDebt() {
        return longTermDebt;
    }

    public void setLongTermDebt(final Decimal longTermDebt) {
        this.longTermDebt = longTermDebt;
    }

    public Decimal getBeginningEquity() {
        return beginningEquity;
    }

    public void setBeginningEquity(final Decimal beginningEquity) {
        this.beginningEquity = beginningEquity;
    }

    public Decimal getStockSalePurch() {
        return stockSalePurch;
    }

    public void setStockSalePurch(final Decimal stockSalePurch) {
        this.stockSalePurch = stockSalePurch;
    }

    public Decimal getEarningsRetained() {
        return earningsRetained;
    }

    public void setEarningsRetained(final Decimal earningsRetained) {
        this.earningsRetained = earningsRetained;
    }

    public Decimal getEndingEquity() {
        return endingEquity;
    }

    public void setEndingEquity(final Decimal endingEquity) {
        this.endingEquity = endingEquity;
    }

}
