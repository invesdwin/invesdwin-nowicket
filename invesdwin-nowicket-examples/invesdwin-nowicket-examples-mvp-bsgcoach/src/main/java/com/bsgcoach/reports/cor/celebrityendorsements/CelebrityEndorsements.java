package com.bsgcoach.reports.cor.celebrityendorsements;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class CelebrityEndorsements extends AValueObject {

    public static class Celebs extends AValueObject {

        private final CompanyRegion companyRegion;

        private Decimal celeb1;
        private Decimal celeb2;
        private Decimal celeb3;
        private Decimal celeb4;
        private Decimal celeb5;
        private Decimal celeb6;
        private Decimal celeb7;
        private Decimal celeb8;
        private Decimal celeb9;
        private Decimal celeb10;
        private Decimal celeb11;
        private Decimal celeb12;
        private Decimal total;

        public Celebs(final CompanyRegion companyRegion) {
            this.companyRegion = companyRegion;
        }

        public Decimal getCeleb1() {
            return celeb1;
        }

        public void setCeleb1(final Decimal celeb1) {
            this.celeb1 = celeb1;
        }

        public Decimal getCeleb2() {
            return celeb2;
        }

        public void setCeleb2(final Decimal celeb2) {
            this.celeb2 = celeb2;
        }

        public Decimal getCeleb3() {
            return celeb3;
        }

        public void setCeleb3(final Decimal celeb3) {
            this.celeb3 = celeb3;
        }

        public Decimal getCeleb4() {
            return celeb4;
        }

        public void setCeleb4(final Decimal celeb4) {
            this.celeb4 = celeb4;
        }

        public Decimal getCeleb5() {
            return celeb5;
        }

        public void setCeleb5(final Decimal celeb5) {
            this.celeb5 = celeb5;
        }

        public Decimal getCeleb6() {
            return celeb6;
        }

        public void setCeleb6(final Decimal celeb6) {
            this.celeb6 = celeb6;
        }

        public Decimal getCeleb7() {
            return celeb7;
        }

        public void setCeleb7(final Decimal celeb7) {
            this.celeb7 = celeb7;
        }

        public Decimal getCeleb8() {
            return celeb8;
        }

        public void setCeleb8(final Decimal celeb8) {
            this.celeb8 = celeb8;
        }

        public Decimal getCeleb9() {
            return celeb9;
        }

        public void setCeleb9(final Decimal celeb9) {
            this.celeb9 = celeb9;
        }

        public Decimal getCeleb10() {
            return celeb10;
        }

        public void setCeleb10(final Decimal celeb10) {
            this.celeb10 = celeb10;
        }

        public Decimal getCeleb11() {
            return celeb11;
        }

        public void setCeleb11(final Decimal celeb11) {
            this.celeb11 = celeb11;
        }

        public Decimal getCeleb12() {
            return celeb12;
        }

        public void setCeleb12(final Decimal celeb12) {
            this.celeb12 = celeb12;
        }

        public Decimal getTotal() {
            return total;
        }

        public void setTotal(final Decimal total) {
            this.total = total;
        }

        public CompanyRegion getCompanyRegion() {
            return companyRegion;
        }

    }

    private final Celebs currentContractDollar000s = new Celebs(null);
    private final ALoadingCache<CompanyRegion, Celebs> regionAppeal = new ALoadingCache<CompanyRegion, Celebs>() {

        @Override
        protected Celebs loadValue(final CompanyRegion key) {
            return new Celebs(key);
        }
    };

    public Celebs getCurrentContractDollar000s() {
        return currentContractDollar000s;
    }

    public Celebs getRegionAppeal(final CompanyRegion companyRegion) {
        return regionAppeal.get(companyRegion);
    }

}
