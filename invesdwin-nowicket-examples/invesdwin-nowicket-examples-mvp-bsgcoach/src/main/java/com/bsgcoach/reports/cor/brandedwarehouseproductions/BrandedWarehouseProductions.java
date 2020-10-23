package com.bsgcoach.reports.cor.brandedwarehouseproductions;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class BrandedWarehouseProductions extends AValueObject {

    private final CompanyRegion companyRegion;

    public static class Inventory {
        //        Pairs
        private Decimal pairs;
        //        Cost
        private Decimal cost;
        //        Models
        private Decimal models;
        //        S/Q
        private Decimal sQ;

        public Decimal getPairs() {
            return pairs;
        }

        public void setPairs(final Decimal pairs) {
            this.pairs = pairs;
        }

        public Decimal getCost() {
            return cost;
        }

        public void setCost(final Decimal cost) {
            this.cost = cost;
        }

        public Decimal getModels() {
            return models;
        }

        public void setModels(final Decimal models) {
            this.models = models;
        }

        public Decimal getSQ() {
            return sQ;
        }

        public void setSQ(final Decimal sQ) {
            this.sQ = sQ;
        }

    }

    private final Inventory inventoryFromLastYr = new Inventory();

    public static class InventoryCleared {
        //        Pairs
        private Decimal pairs;
        //        Cost
        private Decimal cost;

        public Decimal getPairs() {
            return pairs;
        }

        public void setPairs(final Decimal pairs) {
            this.pairs = pairs;
        }

        public Decimal getCost() {
            return cost;
        }

        public void setCost(final Decimal cost) {
            this.cost = cost;
        }

    }

    private final InventoryCleared inventoryCleared = new InventoryCleared();

    private final Inventory beginningInventory = new Inventory();

    public static class ShippedInFromPlant {

        private final CompanyRegion companyRegion;

        //        Pairs
        private Decimal pairs;
        //        Prod Cost
        private Decimal prodCost;
        //        Exchange
        private Decimal exchange;
        //        Freight
        private Decimal freight;
        //        Tariffs
        private Decimal tariffs;
        //          Tot Cost
        private Decimal totCost;
        //        Models
        private Decimal models;
        //        S/Q
        private Decimal sQ;

        public ShippedInFromPlant(final CompanyRegion companyRegion) {
            this.companyRegion = companyRegion;
        }

        public Decimal getPairs() {
            return pairs;
        }

        public void setPairs(final Decimal pairs) {
            this.pairs = pairs;
        }

        public Decimal getProdCost() {
            return prodCost;
        }

        public void setProdCost(final Decimal prodCost) {
            this.prodCost = prodCost;
        }

        public Decimal getExchange() {
            return exchange;
        }

        public void setExchange(final Decimal exchange) {
            this.exchange = exchange;
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

        public Decimal getTotCost() {
            return totCost;
        }

        public void setTotCost(final Decimal totCost) {
            this.totCost = totCost;
        }

        public Decimal getModels() {
            return models;
        }

        public void setModels(final Decimal models) {
            this.models = models;
        }

        public Decimal getSQ() {
            return sQ;
        }

        public void setSQ(final Decimal sQ) {
            this.sQ = sQ;
        }

        public CompanyRegion getCompanyRegion() {
            return companyRegion;
        }

    }

    private final ALoadingCache<CompanyRegion, ShippedInFromPlant> shippedInFromPlant = new ALoadingCache<CompanyRegion, ShippedInFromPlant>() {
        @Override
        protected ShippedInFromPlant loadValue(final CompanyRegion key) {
            return new ShippedInFromPlant(key);
        }
    };

    private final Inventory availableForSale = new Inventory();

    public static class Pairs {
        //        Internet
        private Decimal internet;
        //        Wholesale
        private Decimal wholesale;
        //          Total
        private Decimal total;

        public Decimal getInternet() {
            return internet;
        }

        public void setInternet(final Decimal internet) {
            this.internet = internet;
        }

        public Decimal getWholesale() {
            return wholesale;
        }

        public void setWholesale(final Decimal wholesale) {
            this.wholesale = wholesale;
        }

        public Decimal getTotal() {
            return total;
        }

        public void setTotal(final Decimal total) {
            this.total = total;
        }

    }

    private final Pairs pairsDemanded = new Pairs();

    private final Pairs pairsSold = new Pairs();

    //    Inventory Requirement
    private Decimal inventoryRequirement;
    //    Inventory Surplus (shortage)
    private Decimal inventorySurplusShortage;

    private final Inventory endingInventory = new Inventory();

    //    Wholesale Price
    private Decimal wholesalePrice;
    //    Industry Total Inv
    private Decimal industryTotalInv;

    public static class Market {
        //    Demand----  Internet
        private Decimal demandInternet;
        //        Wholesale
        private Decimal demandWholesale;
        //    Sold----------- Internet
        private Decimal soldInternet;
        //        Wholesale
        private Decimal soldWholesale;

        public Decimal getDemandInternet() {
            return demandInternet;
        }

        public void setDemandInternet(final Decimal demandInternet) {
            this.demandInternet = demandInternet;
        }

        public Decimal getDemandWholesale() {
            return demandWholesale;
        }

        public void setDemandWholesale(final Decimal demandWholesale) {
            this.demandWholesale = demandWholesale;
        }

        public Decimal getSoldInternet() {
            return soldInternet;
        }

        public void setSoldInternet(final Decimal soldInternet) {
            this.soldInternet = soldInternet;
        }

        public Decimal getSoldWholesale() {
            return soldWholesale;
        }

        public void setSoldWholesale(final Decimal soldWholesale) {
            this.soldWholesale = soldWholesale;
        }

    }

    private final Market market = new Market();

    public static class WhseOperExp {
        //        Inventory Storage
        private Decimal inventoryStorage;
        //        Pack/Ship---    Internet
        private Decimal packShipInternet;
        //            Wholesale
        private Decimal packShipWholesale;
        //        Lease and Maint.
        private Decimal leaseAndMaint;
        //          Total Whse Expene
        private Decimal totalWhseExpene;

        public Decimal getInventoryStorage() {
            return inventoryStorage;
        }

        public void setInventoryStorage(final Decimal inventoryStorage) {
            this.inventoryStorage = inventoryStorage;
        }

        public Decimal getPackShipInternet() {
            return packShipInternet;
        }

        public void setPackShipInternet(final Decimal packShipInternet) {
            this.packShipInternet = packShipInternet;
        }

        public Decimal getPackShipWholesale() {
            return packShipWholesale;
        }

        public void setPackShipWholesale(final Decimal packShipWholesale) {
            this.packShipWholesale = packShipWholesale;
        }

        public Decimal getLeaseAndMaint() {
            return leaseAndMaint;
        }

        public void setLeaseAndMaint(final Decimal leaseAndMaint) {
            this.leaseAndMaint = leaseAndMaint;
        }

        public Decimal getTotalWhseExpene() {
            return totalWhseExpene;
        }

        public void setTotalWhseExpene(final Decimal totalWhseExpene) {
            this.totalWhseExpene = totalWhseExpene;
        }

    }

    private final WhseOperExp whseOperExp = new WhseOperExp();

    //    Total Exchange Rate Adjustment
    private Decimal totalExchangeRateAdjustment;
    //    Total Freight and Tariffs
    private Decimal totalFreightAndTariffs;

    public static class InventoryClearance {
        //        Ind Inv / Ind Demand
        private Decimal indInvPerIndDemand;
        //        Co Cleared / Ind Inv
        private Decimal coClearedPerIndInv;
        //        Co Cleared / Sales
        private Decimal coClearedPerSales;
        //        Price � S/Q Rating
        private Decimal priceSqRating;
        //        Clearance Points
        private Decimal clearancePoints;
        //        Margin Reduction %
        private Percent marginReductionPercent;
        //        Intended Price
        private Decimal intendedPrice;
        //        Indended Margin $
        private Decimal intendedMarginDollars;

        public class PerPairCleared extends ACleared {
            //            Price
            private Decimal price;

            public Decimal getPrice() {
                return price;
            }

            public void setPrice(final Decimal price) {
                this.price = price;
            }

        }

        private final PerPairCleared perPairCleared = new PerPairCleared();

        public class TotalDollars extends ACleared {
            //            Revenue
            private Decimal revenue;

            public Decimal getRevenue() {
                return revenue;
            }

            public void setRevenue(final Decimal revenue) {
                this.revenue = revenue;
            }

        }

        public abstract class ACleared extends AValueObject {
            //            Cost
            private Decimal cost;
            //            Storage
            private Decimal storage;
            //            Packing
            private Decimal packing;
            //              Margin
            private Decimal margin;

            public Decimal getCost() {
                return cost;
            }

            public void setCost(final Decimal cost) {
                this.cost = cost;
            }

            public Decimal getStorage() {
                return storage;
            }

            public void setStorage(final Decimal storage) {
                this.storage = storage;
            }

            public Decimal getPacking() {
                return packing;
            }

            public void setPacking(final Decimal packing) {
                this.packing = packing;
            }

            public Decimal getMargin() {
                return margin;
            }

            public void setMargin(final Decimal margin) {
                this.margin = margin;
            }

        }

        private final TotalDollars totalDollars = new TotalDollars();

        public Decimal getIndInvPerIndDemand() {
            return indInvPerIndDemand;
        }

        public void setIndInvPerIndDemand(final Decimal indInvPerIndDemand) {
            this.indInvPerIndDemand = indInvPerIndDemand;
        }

        public Decimal getCoClearedPerIndInv() {
            return coClearedPerIndInv;
        }

        public void setCoClearedPerIndInv(final Decimal coClearedPerIndInv) {
            this.coClearedPerIndInv = coClearedPerIndInv;
        }

        public Decimal getCoClearedPerSales() {
            return coClearedPerSales;
        }

        public void setCoClearedPerSales(final Decimal coClearedPerSales) {
            this.coClearedPerSales = coClearedPerSales;
        }

        public Decimal getPriceSqRating() {
            return priceSqRating;
        }

        public void setPriceSqRating(final Decimal priceSqRating) {
            this.priceSqRating = priceSqRating;
        }

        public Decimal getClearancePoints() {
            return clearancePoints;
        }

        public void setClearancePoints(final Decimal clearancePoints) {
            this.clearancePoints = clearancePoints;
        }

        public Percent getMarginReductionPercent() {
            return marginReductionPercent;
        }

        public void setMarginReductionPercent(final Percent marginReductionPercent) {
            this.marginReductionPercent = marginReductionPercent;
        }

        public Decimal getIntendedPrice() {
            return intendedPrice;
        }

        public void setIntendedPrice(final Decimal intendedPrice) {
            this.intendedPrice = intendedPrice;
        }

        public Decimal getIntendedMarginDollars() {
            return intendedMarginDollars;
        }

        public void setIntendedMarginDollars(final Decimal intendedMarginDollars) {
            this.intendedMarginDollars = intendedMarginDollars;
        }

        public PerPairCleared getPerPairCleared() {
            return perPairCleared;
        }

        public TotalDollars getTotalDollars() {
            return totalDollars;
        }

    }

    private final InventoryClearance inventoryClearance = new InventoryClearance();

    public BrandedWarehouseProductions(final CompanyRegion companyRegion) {
        this.companyRegion = companyRegion;
    }

    public Decimal getInventoryRequirement() {
        return inventoryRequirement;
    }

    public void setInventoryRequirement(final Decimal inventoryRequirement) {
        this.inventoryRequirement = inventoryRequirement;
    }

    public Decimal getInventorySurplusShortage() {
        return inventorySurplusShortage;
    }

    public void setInventorySurplusShortage(final Decimal inventorySurplusShortage) {
        this.inventorySurplusShortage = inventorySurplusShortage;
    }

    public Decimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(final Decimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public Decimal getIndustryTotalInv() {
        return industryTotalInv;
    }

    public void setIndustryTotalInv(final Decimal industryTotalInv) {
        this.industryTotalInv = industryTotalInv;
    }

    public Decimal getTotalExchangeRateAdjustment() {
        return totalExchangeRateAdjustment;
    }

    public void setTotalExchangeRateAdjustment(final Decimal totalExchangeRateAdjustment) {
        this.totalExchangeRateAdjustment = totalExchangeRateAdjustment;
    }

    public Decimal getTotalFreightAndTariffs() {
        return totalFreightAndTariffs;
    }

    public void setTotalFreightAndTariffs(final Decimal totalFreightAndTariffs) {
        this.totalFreightAndTariffs = totalFreightAndTariffs;
    }

    public CompanyRegion getCompanyRegion() {
        return companyRegion;
    }

    public Inventory getInventoryFromLastYr() {
        return inventoryFromLastYr;
    }

    public InventoryCleared getInventoryCleared() {
        return inventoryCleared;
    }

    public Inventory getBeginningInventory() {
        return beginningInventory;
    }

    public ShippedInFromPlant getShippedInFromPlant(final CompanyRegion companyRegion) {
        return shippedInFromPlant.get(companyRegion);
    }

    public Inventory getAvailableForSale() {
        return availableForSale;
    }

    public Pairs getPairsDemanded() {
        return pairsDemanded;
    }

    public Pairs getPairsSold() {
        return pairsSold;
    }

    public Inventory getEndingInventory() {
        return endingInventory;
    }

    public Market getMarket() {
        return market;
    }

    public WhseOperExp getWhseOperExp() {
        return whseOperExp;
    }

    public InventoryClearance getInventoryClearance() {
        return inventoryClearance;
    }

}
