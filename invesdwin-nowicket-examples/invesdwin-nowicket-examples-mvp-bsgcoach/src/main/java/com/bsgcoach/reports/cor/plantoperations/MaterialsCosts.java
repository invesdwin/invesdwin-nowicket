package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class MaterialsCosts extends AValueObject {

    //    BestP Effect %  
    private Percent bestPEffectPercent;
    //    Per Pair------  Normal
    private Decimal perPairNormal;
    //        Long
    private Decimal perPairLong;
    //    Savings Per Pair    
    private Decimal savingsPerPair;

    public Percent getBestPEffectPercent() {
        return bestPEffectPercent;
    }

    public void setBestPEffectPercent(final Percent bestPEffectPercent) {
        this.bestPEffectPercent = bestPEffectPercent;
    }

    public Decimal getPerPairNormal() {
        return perPairNormal;
    }

    public void setPerPairNormal(final Decimal perPairNormal) {
        this.perPairNormal = perPairNormal;
    }

    public Decimal getPerPairLong() {
        return perPairLong;
    }

    public void setPerPairLong(final Decimal perPairLong) {
        this.perPairLong = perPairLong;
    }

    public Decimal getSavingsPerPair() {
        return savingsPerPair;
    }

    public void setSavingsPerPair(final Decimal savingsPerPair) {
        this.savingsPerPair = savingsPerPair;
    }

}
