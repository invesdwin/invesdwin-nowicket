package com.bsgcoach.reports.cor.privatelabeloperations;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions.Inventory;
import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions.ShippedInFromPlant;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class PrivateLabelOperations extends AValueObject {

    private final CompanyRegion companyRegion;
    private final ALoadingCache<CompanyRegion, ShippedInFromPlant> shippedInFromPlant = new ALoadingCache<CompanyRegion, ShippedInFromPlant>() {
        @Override
        protected ShippedInFromPlant loadValue(final CompanyRegion key) {
            return new ShippedInFromPlant(key);
        }
    };
    private final Inventory availableForSale = new Inventory();
    //    Gross Revenues
    private Decimal grossRevenues;
    //    Exchange Rate Adjustment
    private Decimal exchangeRateAdjustment;
    //    Net Revenues
    private Decimal netRevenues;

    public static class DirectCosts extends AValueObject {
        //    Direct--------- Production Costs
        private Decimal productionCosts;
        //      Costs Exchange Rate Adj
        private Decimal exchangeRateAdj;
        //        Freight
        private Decimal freight;
        //        Tariffs
        private Decimal tariffs;
        //        Packing/Shipping
        private Decimal packingShipping;

        public Decimal getProductionCosts() {
            return productionCosts;
        }

        public void setProductionCosts(final Decimal productionCosts) {
            this.productionCosts = productionCosts;
        }

        public Decimal getExchangeRateAdj() {
            return exchangeRateAdj;
        }

        public void setExchangeRateAdj(final Decimal exchangeRateAdj) {
            this.exchangeRateAdj = exchangeRateAdj;
        }

        public Decimal getFreight() {
            return freight;
        }

        public void setFreight(final Decimal freight) {
            this.freight = freight;
        }

        public Decimal getTariffs() {
            return tariffs;
        }

        public void setTariffs(final Decimal tariffs) {
            this.tariffs = tariffs;
        }

        public Decimal getPackingShipping() {
            return packingShipping;
        }

        public void setPackingShipping(final Decimal packingShipping) {
            this.packingShipping = packingShipping;
        }

    }

    private final DirectCosts directCosts = new DirectCosts();

    //    Margin Over Direct Costs
    private Decimal marginOverDirectCosts;

    public PrivateLabelOperations(final CompanyRegion companyRegion) {
        this.companyRegion = companyRegion;
    }

    public CompanyRegion getCompanyRegion() {
        return companyRegion;
    }

    public DirectCosts getDirectCosts() {
        return directCosts;
    }

    public Decimal getGrossRevenues() {
        return grossRevenues;
    }

    public void setGrossRevenues(final Decimal grossRevenues) {
        this.grossRevenues = grossRevenues;
    }

    public Decimal getExchangeRateAdjustment() {
        return exchangeRateAdjustment;
    }

    public void setExchangeRateAdjustment(final Decimal exchangeRateAdjustment) {
        this.exchangeRateAdjustment = exchangeRateAdjustment;
    }

    public Decimal getNetRevenues() {
        return netRevenues;
    }

    public void setNetRevenues(final Decimal netRevenues) {
        this.netRevenues = netRevenues;
    }

    public Decimal getMarginOverDirectCosts() {
        return marginOverDirectCosts;
    }

    public void setMarginOverDirectCosts(final Decimal marginOverDirectCosts) {
        this.marginOverDirectCosts = marginOverDirectCosts;
    }

    public ShippedInFromPlant getShippedInFromPlant(final CompanyRegion companyRegion) {
        return shippedInFromPlant.get(companyRegion);
    }

    public Inventory getAvailableForSale() {
        return availableForSale;
    }

}
