package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class Production extends AValueObject {

    //Number of Models        Current
    private Decimal numberOfModelsCurrent;
    //Last Year
    private Decimal numberOfModesLastYr;

    public static class RejectCalcs {
        //Reject--------  Base Reject Rate %
        private Percent baseRejectRatePercent;

        public class Adders {
            //   Adders------    Models
            private Decimal models;
            //    Avg TQM
            private Decimal avgTqm;
            //    Incentive
            private Decimal incentive;
            //    Avg BP
            private Decimal avgBp;

            public Decimal getModels() {
                return models;
            }

            public void setModels(final Decimal models) {
                this.models = models;
            }

            public Decimal getAvgTqm() {
                return avgTqm;
            }

            public void setAvgTqm(final Decimal avgTqm) {
                this.avgTqm = avgTqm;
            }

            public Decimal getIncentive() {
                return incentive;
            }

            public void setIncentive(final Decimal incentive) {
                this.incentive = incentive;
            }

            public Decimal getAvgBp() {
                return avgBp;
            }

            public void setAvgBp(final Decimal avgBp) {
                this.avgBp = avgBp;
            }

        }

        private final Adders adders = new Adders();

        //Initial Reject Rate 
        private Percent initialRejectRate;
        //Upgrade Opt A Mult  
        private Decimal upgradeOptAMult;
        //Reject Rate (this yr)   
        private Decimal rejectRateThisYr;
        //    Last Year
        private Decimal rejectRateLastYr;

        public Percent getBaseRejectRatePercent() {
            return baseRejectRatePercent;
        }

        public void setBaseRejectRatePercent(final Percent baseRejectRatePercent) {
            this.baseRejectRatePercent = baseRejectRatePercent;
        }

        public Percent getInitialRejectRate() {
            return initialRejectRate;
        }

        public void setInitialRejectRate(final Percent initialRejectRate) {
            this.initialRejectRate = initialRejectRate;
        }

        public Decimal getUpgradeOptAMult() {
            return upgradeOptAMult;
        }

        public void setUpgradeOptAMult(final Decimal upgradeOptAMult) {
            this.upgradeOptAMult = upgradeOptAMult;
        }

        public Decimal getRejectRateThisYr() {
            return rejectRateThisYr;
        }

        public void setRejectRateThisYr(final Decimal rejectRateThisYr) {
            this.rejectRateThisYr = rejectRateThisYr;
        }

        public Decimal getRejectRateLastYr() {
            return rejectRateLastYr;
        }

        public void setRejectRateLastYr(final Decimal rejectRateLastYr) {
            this.rejectRateLastYr = rejectRateLastYr;
        }

        public Adders getAdders() {
            return adders;
        }

    }

    private final RejectCalcs rejectCalcs = new RejectCalcs();

    public static class SqCalcs {
        //S/Q------------ Points--------- L-W %
        private Decimal pointsLWPercent;
        //Calcs       Styling
        private Decimal pointsStyling;
        //    Cur TQM
        private Decimal pointsCurTqm;
        //    Avg TQM
        private Decimal pointsAvgTqm;
        //    BestP
        private Decimal pointsBestP;
        //    Opt C
        private Decimal pointsOptC;
        //      Total
        private Decimal pointsTotal;

        public Decimal getPointsLWPercent() {
            return pointsLWPercent;
        }

        public void setPointsLWPercent(final Decimal pointsLWPercent) {
            this.pointsLWPercent = pointsLWPercent;
        }

        public Decimal getPointsStyling() {
            return pointsStyling;
        }

        public void setPointsStyling(final Decimal pointsStyling) {
            this.pointsStyling = pointsStyling;
        }

        public Decimal getPointsCurTqm() {
            return pointsCurTqm;
        }

        public void setPointsCurTqm(final Decimal pointsCurTqm) {
            this.pointsCurTqm = pointsCurTqm;
        }

        public Decimal getPointsAvgTqm() {
            return pointsAvgTqm;
        }

        public void setPointsAvgTqm(final Decimal pointsAvgTqm) {
            this.pointsAvgTqm = pointsAvgTqm;
        }

        public Decimal getPointsBestP() {
            return pointsBestP;
        }

        public void setPointsBestP(final Decimal pointsBestP) {
            this.pointsBestP = pointsBestP;
        }

        public Decimal getPointsOptC() {
            return pointsOptC;
        }

        public void setPointsOptC(final Decimal pointsOptC) {
            this.pointsOptC = pointsOptC;
        }

        public Decimal getPointsTotal() {
            return pointsTotal;
        }

        public void setPointsTotal(final Decimal pointsTotal) {
            this.pointsTotal = pointsTotal;
        }

    }

    private final SqCalcs sqCalcs = new SqCalcs();
    //S/Q Rating (number of stars)        
    private Decimal sqRatingNumberOfStarsThisYr;
    //    Last Year
    private Decimal sqRatingNumberOfStarsLastYr;

    public static class PairsProduced000s {
        //Pairs Produced-----------       Regular
        private Decimal regular;
        //(000s)     Overtime
        private Decimal overtime;
        //      Total
        private Decimal total;
        //Pairs Rejected  
        private Decimal pairsRejected;
        //Net Production  
        private Decimal netProduction;
        //Last-Year------------------       Produced
        private Decimal producedLastYr;
        //    Rejected
        private Decimal rejectedLastYr;
        //    Net
        private Decimal netLastYr;

        public Decimal getRegular() {
            return regular;
        }

        public void setRegular(final Decimal regular) {
            this.regular = regular;
        }

        public Decimal getOvertime() {
            return overtime;
        }

        public void setOvertime(final Decimal overtime) {
            this.overtime = overtime;
        }

        public Decimal getTotal() {
            return total;
        }

        public void setTotal(final Decimal total) {
            this.total = total;
        }

        public Decimal getPairsRejected() {
            return pairsRejected;
        }

        public void setPairsRejected(final Decimal pairsRejected) {
            this.pairsRejected = pairsRejected;
        }

        public Decimal getNetProduction() {
            return netProduction;
        }

        public void setNetProduction(final Decimal netProduction) {
            this.netProduction = netProduction;
        }

        public Decimal getProducedLastYr() {
            return producedLastYr;
        }

        public void setProducedLastYr(final Decimal producedLastYr) {
            this.producedLastYr = producedLastYr;
        }

        public Decimal getRejectedLastYr() {
            return rejectedLastYr;
        }

        public void setRejectedLastYr(final Decimal rejectedLastYr) {
            this.rejectedLastYr = rejectedLastYr;
        }

        public Decimal getNetLastYr() {
            return netLastYr;
        }

        public void setNetLastYr(final Decimal netLastYr) {
            this.netLastYr = netLastYr;
        }

    }

    private final PairsProduced000s pairsProduced000s = new PairsProduced000s();

    //Branded Cost Allocation %    
    private Percent brandedCostAllocationPercent;

    public static class Costs {

        //Costs---------- Materials-----  Standard
        private Decimal materialsStandard;
        //    Superior
        private Decimal materialsSuperior;
        //Labor---------- Wage
        private Decimal laborWage;
        //    Incentive
        private Decimal laborIncentive;
        //    Overtime
        private Decimal laborOvertime;
        //Best Practices  
        private Decimal bestPractices;
        //Plant Supervision   
        private Decimal plantSupervision;
        //Styling/Features    
        private Decimal stylingFeatures;
        //TQM 6 Sigma 
        private Decimal tqm6Sigma;
        //Prod Run Set-up 
        private Decimal prodRunSetup;
        //Maintenance 
        private Decimal mainenance;
        //Depreciation    
        private Decimal deprecation;
        //  Total 
        private Decimal total;

        public Decimal getMaterialsStandard() {
            return materialsStandard;
        }

        public void setMaterialsStandardOrNormal(final Decimal materialsStandard) {
            this.materialsStandard = materialsStandard;
        }

        public Decimal getMaterialsSuperior() {
            return materialsSuperior;
        }

        public void setMaterialsSuperiorOrLong(final Decimal materialsSuperior) {
            this.materialsSuperior = materialsSuperior;
        }

        public Decimal getLaborWage() {
            return laborWage;
        }

        public void setLaborWage(final Decimal laborWage) {
            this.laborWage = laborWage;
        }

        public Decimal getLaborIncentive() {
            return laborIncentive;
        }

        public void setLaborIncentive(final Decimal laborIncentive) {
            this.laborIncentive = laborIncentive;
        }

        public Decimal getLaborOvertime() {
            return laborOvertime;
        }

        public void setLaborOvertime(final Decimal laborOvertime) {
            this.laborOvertime = laborOvertime;
        }

        public Decimal getBestPractices() {
            return bestPractices;
        }

        public void setBestPractices(final Decimal bestPractices) {
            this.bestPractices = bestPractices;
        }

        public Decimal getPlantSupervision() {
            return plantSupervision;
        }

        public void setPlantSupervision(final Decimal plantSupervision) {
            this.plantSupervision = plantSupervision;
        }

        public Decimal getStylingFeatures() {
            return stylingFeatures;
        }

        public void setStylingFeatures(final Decimal stylingFeatures) {
            this.stylingFeatures = stylingFeatures;
        }

        public Decimal getTqm6Sigma() {
            return tqm6Sigma;
        }

        public void setTqm6Sigma(final Decimal tqm6Sigma) {
            this.tqm6Sigma = tqm6Sigma;
        }

        public Decimal getProdRunSetup() {
            return prodRunSetup;
        }

        public void setProdRunSetup(final Decimal prodRunSetup) {
            this.prodRunSetup = prodRunSetup;
        }

        public Decimal getMainenance() {
            return mainenance;
        }

        public void setMainenance(final Decimal mainenance) {
            this.mainenance = mainenance;
        }

        public Decimal getDeprecation() {
            return deprecation;
        }

        public void setDeprecation(final Decimal deprecation) {
            this.deprecation = deprecation;
        }

        public Decimal getTotal() {
            return total;
        }

        public void setTotal(final Decimal total) {
            this.total = total;
        }

    }

    private final Costs costs = new Costs();

    public static class CostPrProd {
        //Cost/Pr------   Before Rejects  
        private Decimal beforeRejects;
        //Prod    After Rejects   
        private Decimal afterRejects;
        //Cost of Rejects
        private Decimal costOfRejects;

        public Decimal getBeforeRejects() {
            return beforeRejects;
        }

        public void setBeforeRejects(final Decimal beforeRejects) {
            this.beforeRejects = beforeRejects;
        }

        public Decimal getAfterRejects() {
            return afterRejects;
        }

        public void setAfterRejects(final Decimal afterRejects) {
            this.afterRejects = afterRejects;
        }

        public Decimal getCostOfRejects() {
            return costOfRejects;
        }

        public void setCostOfRejects(final Decimal costOfRejects) {
            this.costOfRejects = costOfRejects;
        }

    }

    private final CostPrProd costPrProd = new CostPrProd();

    public Decimal getNumberOfModelsCurrent() {
        return numberOfModelsCurrent;
    }

    public void setNumberOfModelsCurrent(final Decimal numberOfModelsCurrent) {
        this.numberOfModelsCurrent = numberOfModelsCurrent;
    }

    public Decimal getNumberOfModesLastYr() {
        return numberOfModesLastYr;
    }

    public void setNumberOfModesLastYr(final Decimal numberOfModesLastYr) {
        this.numberOfModesLastYr = numberOfModesLastYr;
    }

    public Decimal getSqRatingNumberOfStarsThisYr() {
        return sqRatingNumberOfStarsThisYr;
    }

    public void setSqRatingNumberOfStarsThisYr(final Decimal sqRatingNumberOfStarsThisYr) {
        this.sqRatingNumberOfStarsThisYr = sqRatingNumberOfStarsThisYr;
    }

    public Decimal getSqRatingNumberOfStarsLastYr() {
        return sqRatingNumberOfStarsLastYr;
    }

    public void setSqRatingNumberOfStarsLastYr(final Decimal sqRatingNumberOfStarsLastYr) {
        this.sqRatingNumberOfStarsLastYr = sqRatingNumberOfStarsLastYr;
    }

    public Percent getBrandedCostAllocationPercent() {
        return brandedCostAllocationPercent;
    }

    public void setBrandedCostAllocationPercent(final Percent brandedCostAllocationPercent) {
        this.brandedCostAllocationPercent = brandedCostAllocationPercent;
    }

    public RejectCalcs getRejectCalcs() {
        return rejectCalcs;
    }

    public SqCalcs getSqCalcs() {
        return sqCalcs;
    }

    public PairsProduced000s getPairsProduced000s() {
        return pairsProduced000s;
    }

    public Costs getCosts() {
        return costs;
    }

    public CostPrProd getCostPrProd() {
        return costPrProd;
    }

    public Decimal getTqmPerPair() {
        //      Sum of total quality management COR/Plant Operations/Branded Production/Costs/TQM 6 Sigma
        final Decimal sumOfTOtalQualityManagement = getCosts().getTqm6Sigma();
        //                Net production  COR/Plant Operations/Branded Production/Pairs Produced/Net Production
        final Decimal netProduction = getPairsProduced000s().getNetProduction();
        //        Total quality management per pair   =TQM(NA)/NET_PRODUCTION(NA)
        final Decimal tqmPerPair = sumOfTOtalQualityManagement.divide(netProduction);
        return tqmPerPair;
    }

    public Decimal getBestPracticesTrainingPerPair() {
        //                Sum of best practices training  COR/Plant Operations/Branded Production/Costs/Best Practices
        final Decimal sumOfBestPracticesTraining = getCosts().getBestPractices();
        final Decimal netProduction = getPairsProduced000s().getNetProduction();
        //        Best practices training per pair    =BPT(NA)/NET_PRODUCTION(NA)
        final Decimal bestPracticesTrainingPerPair = sumOfBestPracticesTraining.divide(netProduction);
        return bestPracticesTrainingPerPair;
    }

}
