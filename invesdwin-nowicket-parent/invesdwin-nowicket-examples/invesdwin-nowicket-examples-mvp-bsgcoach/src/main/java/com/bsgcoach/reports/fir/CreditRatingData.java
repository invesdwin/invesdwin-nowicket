package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class CreditRatingData extends AValueObject {

    //    Interest Coverage
    private Percent interestCoverage;
    //    Debt Asstes Ratio
    private Percent debtAssetsRatio;

    public Percent getInterestCoverage() {
        return interestCoverage;
    }

    public void setInterestCoverage(final Percent interestCoverage) {
        this.interestCoverage = interestCoverage;
    }

    public Percent getDebtAssetsRatio() {
        return debtAssetsRatio;
    }

    public void setDebtAssetsRatio(final Percent debtAssetsRatio) {
        this.debtAssetsRatio = debtAssetsRatio;
    }

}
