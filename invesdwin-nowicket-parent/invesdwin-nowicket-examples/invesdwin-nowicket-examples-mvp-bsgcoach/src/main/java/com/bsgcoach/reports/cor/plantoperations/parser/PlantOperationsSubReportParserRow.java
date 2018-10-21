package com.bsgcoach.reports.cor.plantoperations.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.plantoperations.OptLetter;
import com.bsgcoach.reports.cor.plantoperations.PlantOperations;
import com.bsgcoach.reports.cor.plantoperations.UpgradeOptions.Online;
import com.bsgcoach.util.Err;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum PlantOperationsSubReportParserRow {

    //  Age of the Plant (in years)
    AgeOfThePlantInYears("Age of the Plant (in years)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.setAgeOfThePlantInYears(value);
        }
    },
    //    Beginning of Yr
    BeginningOfYr("Beginning of Yr", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setBeginningOfYr(value);
        }
    },
    //    Const (from last yr)
    ConstFromLastYr("Const (from last yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setConstFromLastYr(value);
        }
    },
    //    Purchased (this yr)
    PurchasedThisYr("Purchased (this yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setPurchasedThisYr(value);
        }
    },
    //    Sold (this yr)
    //        ﾖ Sold (this yr)
    SoldThisYr("Sold (this yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            if (report.getPlantCapacity().getSoldThisYr() == null) {
                report.getPlantCapacity().setSoldThisYr(value);
            } else {
                Assertions.assertThat(report.getInvestment().getSumSoldThisYr()).isNull();
                report.getInvestment().setSumSoldThisYr(value);
            }
        }
    },
    //    Available This Yr
    AvailableThisYr("Available This Yr", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setAvailableThisYr(value);
        }
    },
    //      % of Total Capacity
    PercentOfTotalCapacity("% of Total Capacity", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setPercentOfTotalCapacity(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    Const (for next yr)
    ConstForNextYr("Const (for next yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setConstForNextYr(value);
        }
    },
    //      Cost ($000s)
    CostDolar000s("Cost ($000s)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getPlantCapacity().setCostDollar000s(value);
        }
    },

    //    Online--------  Opt Letter
    //        Opt Letter
    OnlineOptLetter("Online--------", "Opt Letter") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            final Online online = report.getUpgradeOptions().getOnline();
            final OptLetter optLetter = OptLetter.values()[value.intValue()];
            if (online.getOptLetter1() == null) {
                online.setOptLetter1(optLetter);
            } else {
                Assertions.assertThat(online.getOptLetter2()).isNull();
                online.setOptLetter2(optLetter);
            }
        }
    },
    //    Ordered-------  Opt Letter
    OrderedOptLetter("Ordered-------", "Opt Letter") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            final OptLetter optLetter = OptLetter.values()[value.intValue()];
            report.getUpgradeOptions().getOrdered().setOptLetter(optLetter);
        }
    },
    //        Cost
    OrderedCost("Ordered-------", "Cost") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getUpgradeOptions().getOrdered().setCost(value);
        }
    },

    //    Gross at Beg of Yr
    GrossAtBegOfYr("Gross at Beg of Yr", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setGrossAtBegOfYr(value);
        }
    },
    //      + Upgrade (last yr)
    PlusUpgradeLastYr("+ Upgrade (last yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setPlusUpgradeLastYr(value);
        }
    },
    //      + Const (last yr)
    PlusConstLastYr("+ Const (last yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setPlusConstLastYr(value);
        }
    },
    //      + Purch (this yr)
    PlusPurchThisYr("+ Purch (this yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setPlusPurchThisYr(value);
        }
    },
    //    Gross Invest This Yr
    GrossInvestThisYr("Gross Invest This Yr", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setGrossInvestThisYr(value);
        }
    },
    //    Accum Dep (begin)
    AccumDepBegin("Accum Dep (begin)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setAccumDepBegin(value);
        }
    },
    //    Depreciation this Yr
    DepreciationThisYr("Depreciation this Yr", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setDepreciationThisYr(value);
        }
    },
    //    Net Invest (this yr)
    NetInvestThisYr("Net Invest (this yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setNetInvestThisYr(value);
        }
    },
    //      EE Initiatives
    EeInitiatives("EE Initiatives", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setEeInitiatives(value);
        }
    },
    //    Work in Progress
    WorkInProgress("Work in Progress", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getInvestment().setWorkInProgress(value);
        }
    },

    //    Base Wages
    BaseWages("Base Wages", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setBaseWages(value);
        }
    },
    //    Incentive Pay
    IncentivePay("Incentive Pay", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setIncentivePay(value);
        }
    },
    //      Total Comp
    TotalComp("Total Comp", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setTotalComp(value);
        }
    },
    //    Last Year-- Wages
    LastYearWages("Last Year--", "Wages") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setBaseWagesLastYr(value);
        }
    },
    //        Incentive
    LastYearIncentive("Last Year--", "Incentive") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setIncentivePayLastYr(value);
        }
    },
    //        Total
    LastYearTotal("Last Year--", "Total") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setTotalCompLastYr(value);
        }
    },

    //    Incentive Pay Per Pair
    IncentivePayPerPair("Incentive Pay Per Pair", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setIncentivePayPerPair(value);
        }
    },
    //        Last Year
    IncentivePayPerPairLastYear("Incentive Pay Per Pair", "Last Year") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setIncentivePayPerPairLastYr(value);
        }
    },

    //    Ind Avg Base Wage
    IndAvgBaseWage("Ind Avg Base Wage", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setIndAvgBaseWage(value);
        }
    },
    //    Ind Avg Total Comp
    IndAvgTotalComp("Ind Avg Total Comp", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setIndAvgTotalComp(value);
        }
    },

    //    % Chng from Last Yr
    PercentChngFromLastYr("% Chng from Last Yr", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCompensationDollarsPerWorker().setPercentChngFromLastYr(new Percent(value, PercentScale.PERCENT));
        }
    },

    //    Wage Change (%)
    WageChangePercent("Wage Change (%)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityFactors().setWageChangePercent(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    Expected Incent Pay
    ExpectedIncentPay("Expected Incent Pay", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityFactors().setExpectedIncentPay(value);
        }
    },
    //    Incentive Pay (%)
    IncentivePayPercent("Incentive Pay (%)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityFactors().setIncentivePayPercent(value);
        }
    },
    //    Total Comp (% of avg)
    TotalCompPercentOfAvg("Total Comp (% of avg)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityFactors().setTotalCompPercentOfAvg(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    Best Pract (per wkr)
    BestPractPerWkr("Best Pract (per wkr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityFactors().setBestPractPerWkr(value);
        }
    },

    //    Ind Avg Base Prod
    IndAvgBaseProd("Ind Avg Base Prod", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setIndAvgBaseProd(value);
        }
    },
    //    Beginning Base
    BeginningBase("Beginning Base", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setBeginningBase(value);
        }
    },
    //      % Chng--  Wage
    PercentChngWage("% Chng--", "Wage") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setPercentChngWage(new Percent(value, PercentScale.PERCENT));
        }
    },
    //        BestP
    PercentChngBestP("% Chng--", "BestP") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setPercentChngBestP(new Percent(value, PercentScale.PERCENT));
        }
    },
    //        Opt D
    PercentChngOptD("% Chng--", "Opt D") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setPercentChngOptD(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    New Base (this yr)
    NewBaseThisYr("New Base (this yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setNewBaseThisYr(value);
        }
    },
    //      % Chng--  Incentive
    PercentChngIncentive("% Chng--", "Incentive") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setPercentChngIncentive(new Percent(value, PercentScale.PERCENT));
        }
    },
    //        Tot Comp
    PercentChngTotComp("% Chng--", "Tot Comp") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setPercentChngTotComp(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    Productivity (this yr)
    ProductivityThisYr("Productivity (this yr)", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setProductivityThisYr(value);
        }
    },
    //        Last Year
    ProductivityLastYr("Productivity (this yr)", "Last Year") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getProductivityCalc().setProductivityLastYr(value);
        }
    },

    //    Branded Production
    BrandedProduction("Branded Production", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getWorkersEmployed().setBrandedProduction(value);
        }
    },
    //    P-Label Production
    PLabelProduction("P-Label Production", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getWorkersEmployed().setpLabelProduction(value);
        }
    },
    //      Total Workers Emp
    TotalWorkersEmp("Total Workers Emp", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getWorkersEmployed().setTotalWorkersEmp(value);
        }
    },
    //        Last Year
    TotalWorkersEmpLastYr("Total Workers Emp", "Last Year") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getWorkersEmployed().setTotalWorkersEmpLastYr(value);
        }
    },

    //    TQM----------   $000s
    TqmDollar000s("TQM----------", "$000s") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getTqm().setDollar000s(value);
        }
    },
    //        Capacity
    TqmCapacity("TQM----------", "Capacity") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getTqm().setCapacity(value);
        }
    },
    //        $/pair Cap
    TqmDollarPerPairCap("TQM----------", "$/pair Cap") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getTqm().setDollarPerPairCap(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    BestP---------  $000s
    BestPDollar000s("BestP---------", "$000s") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getBestP().setDollar000s(value);
        }
    },
    //        Pairs
    BestPPairs("BestP---------", "Pairs") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getBestP().setPairs(value);
        }
    },
    //        $/pair Prod
    BestPDollarPerPairProd("BestP---------", "$/pair Prod") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getBestP().setDollarPerPairCap(new Percent(value, PercentScale.PERCENT));
        }
    },
    //        Employees
    BestPEmployees("BestP---------", "Employees") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getCumulatives().getBestP().setEmployees(value);
        }
    },
    //    BestP Effect %
    BestPEffectPercent("BestP Effect %", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getMaterialsCosts().setBestPEffectPercent(new Percent(value, PercentScale.PERCENT));
        }
    },
    //    Per Pair------  Normal
    PerPairNormal("Per Pair------", "Normal") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getMaterialsCosts().setPerPairNormal(value);
        }
    },
    //        Long
    PerPairLong("Per Pair------", "Long") {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getMaterialsCosts().setPerPairLong(value);
        }
    },
    //    Savings Per Pair
    SavingsPerPair("Savings Per Pair", null) {
        @Override
        public void parse(final PlantOperations report, final Decimal value) {
            report.getMaterialsCosts().setSavingsPerPair(value);
        }
    };

    private String title1;
    private String title2;

    PlantOperationsSubReportParserRow(final String title1, final String title2) {
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return title1 + "_" + title2;
    }

    public static PlantOperationsSubReportParserRow valueOfTitle(final String title1, final String title2) {
        final String trimmedTitle = Strings.trim(Strings.stripNonAscii(title1)) + "_"
                + Strings.trim(Strings.stripNonAscii(title2));
        for (final PlantOperationsSubReportParserRow r : PlantOperationsSubReportParserRow.values()) {
            final String rStr = r.toString();
            if (rStr.equals(trimmedTitle) || trimmedTitle.endsWith(rStr)) {
                return r;
            }
        }
        Err.process(UnknownArgumentException.newInstance(String.class, trimmedTitle));
        return null;
    }

    public abstract void parse(PlantOperations report, Decimal value);

}
