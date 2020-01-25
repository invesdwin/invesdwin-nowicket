package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class UpgradeOptions extends AValueObject {

    public class Online {
        private OptLetter optLetter1;
        private OptLetter optLetter2;

        public OptLetter getOptLetter1() {
            return optLetter1;
        }

        public void setOptLetter1(final OptLetter optLetter1) {
            this.optLetter1 = optLetter1;
        }

        public OptLetter getOptLetter2() {
            return optLetter2;
        }

        public void setOptLetter2(final OptLetter optLetter2) {
            this.optLetter2 = optLetter2;
        }

        public boolean hasOptLetter(final OptLetter optLetter) {
            return getOptLetter1() == optLetter || getOptLetter2() == optLetter;
        }

    }

    public class Ordered {
        private OptLetter optLetter;
        private Decimal cost;

        public OptLetter getOptLetter() {
            return optLetter;
        }

        public void setOptLetter(final OptLetter optLetter) {
            this.optLetter = optLetter;
        }

        public Decimal getCost() {
            return cost;
        }

        public void setCost(final Decimal cost) {
            this.cost = cost;
        }

    }

    private final Online online = new Online();
    private final Ordered ordered = new Ordered();

    public Online getOnline() {
        return online;
    }

    public Ordered getOrdered() {
        return ordered;
    }

    public Decimal getNumberOfUpgrades() {
        //        Number of upgrades  COR/Plant Operations/Upgrade Options/Online NO_UPGRADES(NA) = Count ≠ 0
        int numberOfUpgrades = 0;
        if (getOnline().getOptLetter1() != OptLetter.NONE) {
            numberOfUpgrades++;
        }
        if (getOnline().getOptLetter2() != OptLetter.NONE) {
            numberOfUpgrades++;
        }
        if (getOrdered().getOptLetter() != OptLetter.NONE) {
            numberOfUpgrades++;
        }
        return Decimal.valueOf(numberOfUpgrades);
    }

}
