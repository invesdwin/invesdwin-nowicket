package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class Cumulatives extends AValueObject {

    public class Tqm {
        //    TQM----------   $000s
        private Decimal dollar000s;
        //    Capacity
        private Decimal capacity;
        //    $/pair Cap
        private Percent dollarPerPairCap;

        public Decimal getDollar000s() {
            return dollar000s;
        }

        public void setDollar000s(final Decimal dollar000s) {
            this.dollar000s = dollar000s;
        }

        public Decimal getCapacity() {
            return capacity;
        }

        public void setCapacity(final Decimal capacity) {
            this.capacity = capacity;
        }

        public Percent getDollarPerPairCap() {
            return dollarPerPairCap;
        }

        public void setDollarPerPairCap(final Percent dollarPerPairCap) {
            this.dollarPerPairCap = dollarPerPairCap;
        }

    }

    public class BestP {
        //    BestP---------  $000s
        private Decimal dollar000s;
        //    Pairs
        private Decimal pairs;
        //    $/pair Prod
        private Percent dollarPerPairCap;
        //    Employees
        private Decimal employees;

        public Decimal getDollar000s() {
            return dollar000s;
        }

        public void setDollar000s(final Decimal dollar000s) {
            this.dollar000s = dollar000s;
        }

        public Decimal getPairs() {
            return pairs;
        }

        public void setPairs(final Decimal pairs) {
            this.pairs = pairs;
        }

        public Percent getDollarPerPairCap() {
            return dollarPerPairCap;
        }

        public void setDollarPerPairCap(final Percent dollarPerPairCap) {
            this.dollarPerPairCap = dollarPerPairCap;
        }

        public Decimal getEmployees() {
            return employees;
        }

        public void setEmployees(final Decimal employees) {
            this.employees = employees;
        }

    }

    private final Tqm tqm = new Tqm();
    private final BestP bestP = new BestP();

    public Tqm getTqm() {
        return tqm;
    }

    public BestP getBestP() {
        return bestP;
    }

}
