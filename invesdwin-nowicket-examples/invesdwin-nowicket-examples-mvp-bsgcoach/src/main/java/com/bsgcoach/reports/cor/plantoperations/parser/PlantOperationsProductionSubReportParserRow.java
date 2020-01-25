package com.bsgcoach.reports.cor.plantoperations.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.plantoperations.Production;
import com.bsgcoach.util.Err;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum PlantOperationsProductionSubReportParserRow {

    //Number of Models        Current
    NumberOfModelsCurrent("Number of Models", null, "Current") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setNumberOfModelsCurrent(value);
        }
    },
    //Branded Number of Models        Curretn
    NumberOfModelsCurretn("Number of Models", null, "Curretn") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setNumberOfModelsCurrent(value);
        }
    },
    //Last Year
    NumberOfModelsLastYr("Number of Models", null, "Last Year") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setNumberOfModesLastYr(value);
        }
    },
    //PLabel Number of Models
    NumberOfModels("Number of Models", null, null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setNumberOfModelsCurrent(value);
        }
    },

    //Reject--------  Base Reject Rate %
    BaseRejectRatePercent("Reject--------", "Base Reject Rate %", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().setBaseRejectRatePercent(new Percent(value, PercentScale.PERCENT));
        }
    },
    //Calcs   Adders------    Models
    Models("Calcs", "Adders------", "Models") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().getAdders().setModels(value);
        }
    },
    //    Avg TQM
    AvgTqm("Calcs", "Adders------", "Avg TQM") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().getAdders().setAvgTqm(value);
        }
    },
    //    Incentive
    Incentive("Calcs", "Adders------", "Incentive") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().getAdders().setIncentive(value);
        }
    },
    //    Avg BP
    AvgBP("Calcs", "Adders------", "Avg BP") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().getAdders().setAvgBp(value);
        }
    },
    //Initial Reject Rate
    InitialRejectRate("Calcs", "Initial Reject Rate", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().setInitialRejectRate(new Percent(value, PercentScale.PERCENT));
        }
    },
    //Upgrade Opt A Mult
    UpgradeOptAMult("Calcs", "Upgrade Opt A Mult", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().setUpgradeOptAMult(value);
        }
    },
    //Reject Rate (this yr)
    RejectRateThisYr("Calcs", "Reject Rate (this yr)", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().setRejectRateThisYr(value);
        }
    },
    //    Last Year
    RejectRateLastYr("Calcs", "Reject Rate (this yr)", "Last Year") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getRejectCalcs().setRejectRateLastYr(value);
        }
    },

    //S/Q------------ Points--------- L-W %
    SqPointsLwPercent("S/Q------------", "Points---------", "L-W %") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsLWPercent(value);
        }
    },
    //Calcs       Styling
    PointsStyling("Calcs", null, "Styling") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsStyling(value);
        }
    },
    //    Cur TQM
    PointsCurTqm("Calcs", null, "Cur TQM") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsCurTqm(value);
        }
    },
    //    Avg TQM
    PointsAvgTqm("Calcs", null, "Avg TQM") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsAvgTqm(value);
        }
    },
    //    BestP
    PointsBestP("Calcs", null, "BestP") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsBestP(value);
        }
    },
    //    Opt C
    PointsOptC("Calcs", null, "Opt C") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsOptC(value);
        }
    },
    //      Total
    PointsTotal("Calcs", null, "Total") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getSqCalcs().setPointsTotal(value);
        }
    },

    //S/Q Rating (number of stars)
    SqRatingNumerOfStarsThisYr("S/Q Rating (number of stars)", null, null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setSqRatingNumberOfStarsThisYr(value);
        }
    },
    //    Last Year
    SqRatingNumerOfStarsLastYr("S/Q Rating (number of stars)", null, "Last Year") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setSqRatingNumberOfStarsLastYr(value);
        }
    },

    //Pairs Produced-----------       Regular
    PairsProducedRegular("Pairs Produced-----------", null, "Regular") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setRegular(value);
        }
    },
    //(000s)     Overtime
    PairsProducedOvertime("(000s)", null, "Overtime") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setOvertime(value);
        }
    },
    //      Total
    PairsProducedTotal("(000s)", null, "Total") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setTotal(value);
        }
    },
    //    Pairs Produced-----------_null_Total
    PairsProducedTotal2("Pairs Produced-----------", null, "Total") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setTotal(value);
        }
    },
    //Pairs Rejected
    PairsRejected("(000s)", "Pairs Rejected", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setPairsRejected(value);
        }
    },
    //Net Production
    NetProduction("(000s)", "Net Production", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setNetProduction(value);
        }
    },
    //    Pairs Produced-----------_null_Total
    NetProduction2("Pairs Produced-----------", "Net Production", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setNetProduction(value);
        }
    },
    //Last-Year------------------       Produced
    LastYearProduced("Last-Year------------------", null, "Produced") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setProducedLastYr(value);
        }
    },
    //    Rejected
    LastYearRejected("Last-Year------------------", null, "Rejected") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setRejectedLastYr(value);
        }
    },
    //    Net
    LastYearNet("Last-Year------------------", null, "Net") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getPairsProduced000s().setNetLastYr(value);
        }
    },
    //
    //Branded Cost Allocation %
    BrandedCostAllocationPercent("Branded Cost Allocation %", null, null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.setBrandedCostAllocationPercent(new Percent(value, PercentScale.PERCENT));
        }
    },
    //
    //Costs---------- Materials-----  Standard
    MaterialsStandard("Costs----------", "Materials-----", "Standard") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setMaterialsStandardOrNormal(value);
        }
    },
    //    Superior
    MaterialsSuperior("Costs----------", "Materials-----", "Superior") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setMaterialsSuperiorOrLong(value);
        }
    },
    //   Normal
    MaterialsNormal("Costs----------", "Materials-----", "Normal") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setMaterialsStandardOrNormal(value);
        }
    },
    //    Long
    MaterialsLong("Costs----------", "Materials-----", "Long") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setMaterialsSuperiorOrLong(value);
        }
    },
    //Labor---------- Wage
    LaborWage("Costs----------", "Labor----------", "Wage") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setLaborWage(value);
        }
    },
    //    Incentive
    LaborIncentive("Costs----------", "Labor----------", "Incentive") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setLaborIncentive(value);
        }
    },
    //    Overtime
    LaborOvertime("Costs----------", "Labor----------", "Overtime") {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setLaborOvertime(value);
        }
    },
    //Best Practices
    BestPractices("Costs----------", "Best Practices", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setBestPractices(value);
        }
    },
    //Plant Supervision
    PlantSupervision("Costs----------", "Plant Supervision", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setPlantSupervision(value);
        }
    },
    //Styling/Features
    StylingFeatures("Costs----------", "Styling/Features", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setStylingFeatures(value);
        }
    },
    //TQM 6 Sigma
    Tqm6Sigma("Costs----------", "TQM 6 Sigma", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setTqm6Sigma(value);
        }
    },
    //Prod Run Set-up
    ProdRunSetup("Costs----------", "Prod Run Set-up", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setProdRunSetup(value);
        }
    },
    //Maintenance
    Maintenance("Costs----------", "Maintenance", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setMainenance(value);
        }
    },
    //Depreciation
    Depreciation("Costs----------", "Depreciation", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setDeprecation(value);
        }
    },
    //  Total
    Total("Costs----------", "Total", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCosts().setTotal(value);
        }
    },

    //Cost/Pr------   Before Rejects
    BeforeRejects("Cost/Pr------", "Before Rejects", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCostPrProd().setBeforeRejects(value);
        }
    },
    //Prod    After Rejects
    AfterRejects("Prod", "After Rejects", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCostPrProd().setAfterRejects(value);
        }
    },
    //Cost of Rejects
    CostOfRejects("Prod", "Cost of Rejects", null) {
        @Override
        public void parse(final Production report, final Decimal value) {
            report.getCostPrProd().setCostOfRejects(value);
        }
    };

    private String category2;
    private String title1;
    private String title2;

    PlantOperationsProductionSubReportParserRow(final String category2, final String title1, final String title2) {
        this.category2 = category2;
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category2 + "_" + title1 + "_" + title2;
    }

    public static PlantOperationsProductionSubReportParserRow valueOfTitle(final String category2, final String title1,
            final String title2) {
        final String trimmedTitle = Strings.trim(category2) + "_" + Strings.trim(title1) + "_" + Strings.trim(title2);
        for (final PlantOperationsProductionSubReportParserRow r : PlantOperationsProductionSubReportParserRow
                .values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(Production report, Decimal value);

}
