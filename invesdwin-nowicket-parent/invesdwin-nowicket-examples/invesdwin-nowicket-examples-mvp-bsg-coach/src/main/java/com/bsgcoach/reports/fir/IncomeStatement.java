package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class IncomeStatement extends AValueObject {

    //    Net Revenues
    private Decimal netRevenues;
    //    Cost of Pairs
    private Decimal costOfPairs;
    //    Warehouse
    private Decimal warehouse;
    //    Marketing
    private Decimal marketing;
    //    Administrative
    private Decimal administrative;
    //    Operating Profit
    private Decimal operatingProfit;
    //    Interest Expense
    private Decimal interestExpense;
    //    Income Taxes
    private Decimal incomeTaxes;
    //    Net Profit
    private Decimal netProfit;
    //    Dividends
    private Decimal dividends;
    //    Stock Shares
    private Decimal stockShares;

    public Decimal getNetRevenues() {
        return netRevenues;
    }

    public void setNetRevenues(final Decimal netRevenues) {
        this.netRevenues = netRevenues;
    }

    public Decimal getCostOfPairs() {
        return costOfPairs;
    }

    public void setCostOfPairs(final Decimal costOfPairs) {
        this.costOfPairs = costOfPairs;
    }

    public Decimal getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(final Decimal warehouse) {
        this.warehouse = warehouse;
    }

    public Decimal getMarketing() {
        return marketing;
    }

    public void setMarketing(final Decimal marketing) {
        this.marketing = marketing;
    }

    public Decimal getAdministrative() {
        return administrative;
    }

    public void setAdministrative(final Decimal administrative) {
        this.administrative = administrative;
    }

    public Decimal getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(final Decimal operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public Decimal getInterestExpense() {
        return interestExpense;
    }

    public void setInterestExpense(final Decimal interestExpense) {
        this.interestExpense = interestExpense;
    }

    public Decimal getIncomeTaxes() {
        return incomeTaxes;
    }

    public void setIncomeTaxes(final Decimal incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }

    public Decimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(final Decimal netProfit) {
        this.netProfit = netProfit;
    }

    public Decimal getDividends() {
        return dividends;
    }

    public void setDividends(final Decimal dividends) {
        this.dividends = dividends;
    }

    public Decimal getStockShares() {
        return stockShares;
    }

    public void setStockShares(final Decimal stockShares) {
        this.stockShares = stockShares;
    }

}
