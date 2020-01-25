package com.bsgcoach.reports.cor.privatelabeloperations.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions.ShippedInFromPlant;
import com.bsgcoach.reports.cor.privatelabeloperations.PrivateLabelOperations;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum PrivateLabelOperationsSubReportParserRow {
    //  Shipped in from------------     Pairs
    ShippedInFromPairs("Shipped in from------------", null, "Pairs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
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
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.NorthAmerica).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromNAExchange("N.A. Plant", null, "Exchange") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromNAFreight("N.A. Plant", null, "Freight") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setFreight(value);
        }
    },
    //           Tariffs
    ShippedInFromNATariffs("N.A. Plant", null, "Tariffs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setTariffs(value);
        }
    },
    //           Tariffs
    ShippedInFromNATarrifs("N.A. Plant", null, "Tarrifs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromNATotCost("N.A. Plant", null, "Tot Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromNAModels("N.A. Plant", null, "Models") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromNASQ("N.A. Plant", null, "S/Q") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.NorthAmerica).setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    //     E-A Plant     Prod Cost
    ShippedInFromEAProdCost("E-A Plant", null, "Prod Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromEAExchange("E-A Plant", null, "Exchange") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromEAFreight("E-A Plant", null, "Freight") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setFreight(value);
        }
    },
    //           Tariffs
    ShippedInFromEATariffs("E-A Plant", null, "Tariffs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setTariffs(value);
        }
    },
    //  Tarrifs
    ShippedInFromEATarrifs("E-A Plant", null, "Tarrifs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromEATotCost("E-A Plant", null, "Tot Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromEAModels("E-A Plant", null, "Models") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromEASQ("E-A Plant", null, "S/Q") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.EuropeAfrica).setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    //     A-P Plant     Prod Cost
    ShippedInFromAPProdCost("A-P Plant", null, "Prod Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            //            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.AsiaPacific).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromAPExchange("A-P Plant", null, "Exchange") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromAPFreight("A-P Plant", null, "Freight") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setFreight(value);
        }
    },
    //           Tariffs
    ShippedInFromAPTariffs("A-P Plant", null, "Tariffs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setTariffs(value);
        }
    },
    //  Tarrifs
    ShippedInFromAPTarrifs("A-P Plant", null, "Tarrifs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromAPTotCost("A-P Plant", null, "Tot Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromAPModels("A-P Plant", null, "Models") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromAPSQ("A-P Plant", null, "S/Q") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.AsiaPacific).setSQ(value);
        }
    },
    //   Shipped in from------------     Pairs
    //     L.A. Plant        Prod Cost
    ShippedInFromLAProdCost("L.A. Plant", null, "Prod Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            Assertions.assertThat(report.getShippedInFromPlant(CompanyRegion.LatinAmerica).getPairs()).isNotNull();
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setProdCost(value);
        }
    },
    //           Exchange
    ShippedInFromLAExchange("L.A. Plant", null, "Exchange") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setExchange(value);
        }
    },
    //           Freight
    ShippedInFromLAFreight("L.A. Plant", null, "Freight") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setFreight(value);
        }
    },
    //           Tariffs
    ShippedInFromLATariffs("L.A. Plant", null, "Tariffs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setTariffs(value);
        }
    },
    //  Tarrifs
    ShippedInFromLATarrifs("L.A. Plant", null, "Tarrifs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setTariffs(value);
        }
    },
    //             Tot Cost
    ShippedInFromLATotCost("L.A. Plant", null, "Tot Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setTotCost(value);
        }
    },
    //           Models
    ShippedInFromLAModels("L.A. Plant", null, "Models") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setModels(value);
        }
    },
    //           S/Q
    ShippedInFromLASQ("L.A. Plant", null, "S/Q") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getShippedInFromPlant(CompanyRegion.LatinAmerica).setSQ(value);
        }
    },
    //   Available for Sale---------     Pairs
    AvailableForSalePairs("Available for Sale---------", null, "Pairs") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getAvailableForSale().setPairs(value);
        }
    },
    //           Cost
    AvailableForSaleCost("Available for Sale---------", null, "Cost") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getAvailableForSale().setCost(value);
        }
    },
    //           Models
    AvailableForSaleModels("Available for Sale---------", null, "Models") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getAvailableForSale().setModels(value);
        }
    },
    //           S/Q
    AvailableForSaleSQ("Available for Sale---------", null, "S/Q") {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getAvailableForSale().setSQ(value);
        }
    },
    //  Gross Revenues
    GrossRevenues("Gross Revenues", null, null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.setGrossRevenues(value);
        }
    },
    //  Exchange Rate Adjustment
    ExchangeRateAdjustment("Exchange Rate Adjustment", null, null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.setExchangeRateAdjustment(value);
        }
    },
    //  Net Revenues
    NetRevenues("Net Revenues", null, null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.setNetRevenues(value);
        }
    },
    //  Direct--------- Production Costs
    DirectCostsProductionCosts("Direct---------", "Production Costs", null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getDirectCosts().setProductionCosts(value);
        }
    },
    //    Costs Exchange Rate Adj
    DirectCostsExchangeRateAdj("Costs", "Exchange Rate Adj", null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getDirectCosts().setExchangeRateAdj(value);
        }
    },
    //      Freight
    DirectCostsFreight("Costs", "Freight", null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getDirectCosts().setFreight(value);
        }
    },
    //      Tariffs
    DirectCostsTariffs("Costs", "Tariffs", null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getDirectCosts().setTariffs(value);
        }
    },
    //      Packing/Shipping
    DirectCostsPackingShipping("Costs", "Packing/Shipping", null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.getDirectCosts().setPackingShipping(value);
        }
    },
    //  Margin Over Direct Costs
    MarginOverDirectCosts("Margin Over Direct Costs", null, null) {
        @Override
        public void parse(final PrivateLabelOperations report, final Decimal value) {
            report.setMarginOverDirectCosts(value);
        }
    };

    private String category2;
    private String title1;
    private String title2;

    PrivateLabelOperationsSubReportParserRow(final String category2, final String title1, final String title2) {
        this.category2 = category2;
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category2 + "_" + title1 + "_" + title2;
    }

    public static PrivateLabelOperationsSubReportParserRow valueOfTitle(final String category2, final String title1,
            final String title2) {
        final String trimmedTitle = Strings.trim(category2) + "_" + Strings.trim(title1) + "_" + Strings.trim(title2);
        for (final PrivateLabelOperationsSubReportParserRow r : PrivateLabelOperationsSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        return null;
    }

    public abstract void parse(PrivateLabelOperations report, Decimal value);

}
