package com.bsgcoach.reports.cor.brandedwarehouseproductions.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions;
import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions.ShippedInFromPlant;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum BrandedWarehouseProductionsSubReportParserRow {

    //    Inventory-------------------        Pairs
    InventoryFromLastYrPairs("Inventory-------------------", null, "Pairs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryFromLastYr().setPairs(value);
        }
    },
    //    (from lst yr)      Cost
    InventoryFromLastYrCost("(from lst yr)", null, "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryFromLastYr().setCost(value);
        }
    },
    //           Models
    InventoryFromLastYrModels("(from lst yr)", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryFromLastYr().setModels(value);
        }
    },
    //           S/Q
    InventoryFromLastYrSq("(from lst yr)", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryFromLastYr().setSQ(value);
        }
    },
    //   Invemtory Cleared--------       Pairs
    InventoryClearedPairs("Invemtory Cleared--------", null, "Pairs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryCleared().setPairs(value);
        }
    },
    //           Cost
    InventoryClearedCost("Invemtory Cleared--------", null, "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryCleared().setCost(value);
        }
    },
    //   Beginning Inventory-----        Pairs
    BeginningInventoryPairs("Beginning Inventory-----", null, "Pairs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getBeginningInventory().setPairs(value);
        }
    },
    //           Cost
    BeginningInventoryCost("Beginning Inventory-----", null, "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getBeginningInventory().setCost(value);
        }
    },
    //           Models
    BeginningInventoryModels("Beginning Inventory-----", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getBeginningInventory().setModels(value);
        }
    },
    //           S/Q
    BeginningInventorySQ("Beginning Inventory-----", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getBeginningInventory().setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    ShippedInFromPairs("Shipped in from------------", null, "Pairs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            //regions already have the right order of values
            boolean valueSet = false;
            for (final CompanyRegion r : CompanyRegion.values()) {
                final ShippedInFromPlant shippedInFromPlant = report.getShippedInFromPlant(r);
                if (shippedInFromPlant.getPairs() == null) {
                    shippedInFromPlant.setPairs(value);
                    valueSet = true;
                    break;
                }
            }
            Assertions.assertThat(valueSet).isTrue();
        }
    },
    //     N.A. Plant        Prod Cost
    ShippedInFromNAProdCost("N.A. Plant", null, "Prod Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.NorthAmerica).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromNAExchange("N.A. Plant", null, "Exchange") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromNAFreight("N.A. Plant", null, "Freight") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setFreight(value);
        }
    },
    //           Tariffs
    ShippedInFromNATariffs("N.A. Plant", null, "Tariffs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setTariffs(value);
        }
    },
    //  Tarrifs
    ShippedInFromNATarrifs("N.A. Plant", null, "Tarrifs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromNATotCost("N.A. Plant", null, "Tot Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromNAModels("N.A. Plant", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromNASQ("N.A. Plant", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    //     E-A Plant     Prod Cost
    ShippedInFromEAProdCost("E-A Plant", null, "Prod Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromEAExchange("E-A Plant", null, "Exchange") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromEAFreight("E-A Plant", null, "Freight") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setFreight(value);
        }
    },
    //           Tariffs
    ShippedInFromEATarrifs("E-A Plant", null, "Tarrifs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromEATotCost("E-A Plant", null, "Tot Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromEAModels("E-A Plant", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromEASQ("E-A Plant", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    //     A-P Plant     Prod Cost
    ShippedInFromAPProdCost("A-P Plant", null, "Prod Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            //            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.AsiaPacific).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromAPExchange("A-P Plant", null, "Exchange") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromAPFreight("A-P Plant", null, "Freight") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setFreight(value);
        }
    },
    //           Tarrifs
    ShippedInFromAPTariffs("A-P Plant", null, "Tarrifs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromAPTotCost("A-P Plant", null, "Tot Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromAPModels("A-P Plant", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromAPSQ("A-P Plant", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    //     L.A. Plant        Prod Cost
    ShippedInFromLAProdCost("L.A. Plant", null, "Prod Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.LatinAmerica).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromLAExchange("L.A. Plant", null, "Exchange") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromLAFreight("L.A. Plant", null, "Freight") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setFreight(value);
        }
    },
    //           Tarrifs
    ShippedInFromLATariffs("L.A. Plant", null, "Tarrifs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromLATotCost("L.A. Plant", null, "Tot Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromLAModels("L.A. Plant", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromLASQ("L.A. Plant", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setSQ(value);
        }
    },
    //   Available for Sale---------     Pairs
    AvailableForSalePairs("Available for Sale---------", null, "Pairs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getAvailableForSale().setPairs(value);
        }
    },
    //           Cost
    AvailableForSaleCost("Available for Sale---------", null, "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getAvailableForSale().setCost(value);
        }
    },
    //           Models
    AvailableForSaleModels("Available for Sale---------", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getAvailableForSale().setModels(value);
        }
    },
    //           S/Q
    AvailableForSaleSQ("Available for Sale---------", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getAvailableForSale().setSQ(value);
        }
    },
    //   Pairs Demanded----------        Internet
    PairsDemandedInternet("Pairs Demanded----------", null, "Internet") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getPairsDemanded().setInternet(value);
        }
    },
    //           Wholesale
    PairsDemandedWholesale("Pairs Demanded----------", null, "Wholesale") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getPairsDemanded().setWholesale(value);
        }
    },
    //             Total
    PairsDemandedTotal("Pairs Demanded----------", null, "Total") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getPairsDemanded().setTotal(value);
        }
    },
    //   Pairs Sold--------------------      Internet
    PairsSoldInternet("Pairs Sold--------------------", null, "Internet") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getPairsSold().setInternet(value);
        }
    },
    //           Wholesale
    PairsSoldWholesale("Pairs Sold--------------------", null, "Wholesale") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getPairsSold().setWholesale(value);
        }
    },
    //             Total
    PairsSoldTotal("Pairs Sold--------------------", null, "Total") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getPairsSold().setTotal(value);
        }
    },
    //   Inventory Requirement
    InventoryRequirement("Inventory Requirement", null, null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.setInventoryRequirement(value);
        }
    },
    //   Inventory Surplus (shortage)
    InventorySurplusShortage("Inventory Surplus (shortage)", null, null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.setInventorySurplusShortage(value);
        }
    },
    //   Ending Inventory--------        Pairs
    EndingInventoryPairs("Ending Inventory--------", null, "Pairs") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getEndingInventory().setPairs(value);
        }
    },
    //           Cost
    EndingInventoryCost("Ending Inventory--------", null, "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getEndingInventory().setCost(value);
        }
    },
    //           Models
    EndingInventoryModels("Ending Inventory--------", null, "Models") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getEndingInventory().setModels(value);
        }
    },
    //           S/Q
    EndingInventorySQ("Ending Inventory--------", null, "S/Q") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getEndingInventory().setSQ(value);
        }
    },

    //       Wholesale Price
    WholesalePrice("Ending Inventory--------", "Wholesale Price", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.setWholesalePrice(value);
        }
    },
    //       Industry Total Inv
    IndustryTotalInv("Ending Inventory--------", "Industry Total Inv", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.setIndustryTotalInv(value);
        }
    },

    //   Market-----------   Demand----  Internet
    MarketDemandInternet("Market-----------", "Demand----", "Internet") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getMarket().setDemandInternet(value);
        }
    },
    //           Wholesale
    MarketDemandWholesale("Market-----------", "Demand----", "Wholesale") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getMarket().setDemandWholesale(value);
        }
    },
    //       Sold----------- Internet
    MarketSoldInternet("Market-----------", "Sold-----------", "Internet") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getMarket().setSoldInternet(value);
        }
    },
    //           Wholesale
    MarketSoldWholesale("Market-----------", "Sold-----------", "Wholesale") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getMarket().setSoldWholesale(value);
        }
    },

    //   Whse ------------   Inventory Storage
    WhseInventoryStorage("Whse ------------", "Inventory Storage", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getWhseOperExp().setInventoryStorage(value);
        }
    },
    //     Oper  Pack/Ship---    Internet
    WhsePackShipInternet("Oper", "Pack/Ship---", "Internet") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getWhseOperExp().setPackShipInternet(value);
        }
    },
    //     Exp       Wholesale
    WhsePackShipWholesale("Exp", null, "Wholesale") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getWhseOperExp().setPackShipWholesale(value);
        }
    },
    //       Lease and Maint.
    WhseLeaseAndMaint("Exp", "Lease and Maint.", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getWhseOperExp().setLeaseAndMaint(value);
        }
    },
    //         Total Whse Expene
    WhseTotalWhseExpene("Exp", "Total Whse Expene", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getWhseOperExp().setTotalWhseExpene(value);
        }
    },

    //   Total Exchange Rate Adjustment
    TotalExchangeRateAdjustment("Total Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.setTotalExchangeRateAdjustment(value);
        }
    },
    //   Total Freight and Tariffs
    TotalFreightAndTariffs("Total Freight and Tariffs", null, null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.setTotalFreightAndTariffs(value);
        }
    },
    //
    //   Inventory--------   Ind Inv / Ind Demand
    InventoryIndInvIndDemand("Inventory--------", "Ind Inv / Ind Demand", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setIndInvPerIndDemand(value);
        }
    },
    //   Clearance   Co Cleared / Ind Inv
    InventoryCoClearedIndInv("Clearance", "Co Cleared / Ind Inv", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setCoClearedPerIndInv(value);
        }
    },
    //       Co Cleared / Sales
    InventoryCoClearedSales("Clearance", "Co Cleared / Sales", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setCoClearedPerSales(value);
        }
    },
    //       Price � S/Q Rating
    InventoryPrice_q_SQRating("Clearance", "Price ? S/Q Rating", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setPriceSqRating(value);
        }
    },
    InventoryPrice_s_SQRating("Clearance", "Price  S/Q Rating", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setPriceSqRating(value);
        }
    },
    InventoryPriceSQRating("Clearance", "Price S/Q Rating", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setPriceSqRating(value);
        }
    },
    //       Clearance Points
    InventoryClearancePoints("Clearance", "Clearance Points", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setClearancePoints(value);
        }
    },
    //       Margin Reduction %
    InventoryMarginReductionPercent("Clearance", "Margin Reduction %", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance()
                    .setMarginReductionPercent(new Percent(value.doubleValue(), PercentScale.PERCENT));
        }
    },
    //       Intended Price
    InventoryIntendedPrice("Clearance", "Intended Price", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setIntendedPrice(value);
        }
    },
    //       Indended Margin $
    InventoryIndendedMarginDollars("Clearance", "Indended Margin $", null) {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().setIntendedMarginDollars(value);
        }
    },
    //       Per Pair------- Price
    PerPairPrice("Clearance", "Per Pair-------", "Price") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getPerPairCleared().setPrice(value);
        }
    },
    //       Cleared Cost
    PerPairCost("Clearance", "Cleared", "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getPerPairCleared().setCost(value);
        }
    },
    //           Storage
    PerPairStorage("Clearance", "Cleared", "Storage") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getPerPairCleared().setStorage(value);
        }
    },
    //           Packing
    PerPairPacking("Clearance", "Cleared", "Packing") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getPerPairCleared().setPacking(value);
        }
    },
    //             Margin
    PerPairMargin("Clearance", "Cleared", "Margin") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getPerPairCleared().setMargin(value);
        }
    },
    //       Total $-------- Revenue
    TotalDollarsRevenue("Clearance", "Total $--------", "Revenue") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getTotalDollars().setRevenue(value);
        }
    },
    //           Cost
    TotalDollarsCost("Clearance", "Total $--------", "Cost") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getTotalDollars().setCost(value);
        }
    },
    //           Storage
    TotalDollarsStorage("Clearance", "Total $--------", "Storage") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getTotalDollars().setStorage(value);
        }
    },
    //           Packing
    TotalDollarsPacking("Clearance", "Total $--------", "Packing") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getTotalDollars().setPacking(value);
        }
    },
    //             Margin
    TotalDollarsMargin("Clearance", "Total $--------", "Margin") {
        @Override
        public void parse(final BrandedWarehouseProductions report, final Decimal value) {
            report.getInventoryClearance().getTotalDollars().setMargin(value);
        }
    };

    private String category2;
    private String title1;
    private String title2;

    BrandedWarehouseProductionsSubReportParserRow(final String category2, final String title1, final String title2) {
        this.category2 = category2;
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category2 + "_" + title1 + "_" + title2;
    }

    public static BrandedWarehouseProductionsSubReportParserRow valueOfTitle(final String category2,
            final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(Strings.stripNonAscii(category2)) + "_"
                + Strings.trim(Strings.stripNonAscii(title1)) + "_" + Strings.trim(Strings.stripNonAscii(title2));
        for (final BrandedWarehouseProductionsSubReportParserRow r : BrandedWarehouseProductionsSubReportParserRow
                .values()) {
            final String rStr = r.toString();
            if (rStr.contains("?")) {
                final String[] split = Strings.split(rStr, "?");
                for (final String s : split) {
                    if (!trimmedTitle.contains(s)) {
                        continue;
                    }
                }
                return r;
            } else {
                if (rStr.equals(trimmedTitle)) {
                    return r;
                }
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(BrandedWarehouseProductions report, Decimal value);

}
