package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class DividendData extends AValueObject {

    //    Year X Dividend ($)
    private Decimal yearXDividendDollars;
    //    No.of Div Increases
    private Decimal noOfDivIncreases;
    //    No.of Div Decreases
    private Decimal noOfDivDecreases;

    public Decimal getYearXDividendDollars() {
        return yearXDividendDollars;
    }

    public void setYearXDividendDollars(final Decimal yearXDividendDollars) {
        this.yearXDividendDollars = yearXDividendDollars;
    }

    public Decimal getNoOfDivIncreases() {
        return noOfDivIncreases;
    }

    public void setNoOfDivIncreases(final Decimal noOfDivIncreases) {
        this.noOfDivIncreases = noOfDivIncreases;
    }

    public Decimal getNoOfDivDecreases() {
        return noOfDivDecreases;
    }

    public void setNoOfDivDecreases(final Decimal noOfDivDecreases) {
        this.noOfDivDecreases = noOfDivDecreases;
    }

}
