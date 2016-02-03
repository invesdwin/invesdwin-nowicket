package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class CompensationDollarsPerWorker extends AValueObject {

    //    Base Wages  
    private Decimal baseWages;
    //    Incentive Pay   
    private Decimal incentivePay;
    //      Total Comp    
    private Decimal totalComp;
    //    Last Year-- Wages
    private Decimal baseWagesLastYr;
    //        Incentive
    private Decimal incentivePayLastYr;
    //        Total
    private Decimal totalCompLastYr;
    //    Incentive Pay Per Pair  
    private Decimal incentivePayPerPair;
    //        Last Year
    private Decimal incentivePayPerPairLastYr;
    //    Ind Avg Base Wage   
    private Decimal indAvgBaseWage;
    //    Ind Avg Total Comp  
    private Decimal indAvgTotalComp;
    //    % Chng from Last Yr 
    private Percent percentChngFromLastYr;

    public Decimal getBaseWages() {
        return baseWages;
    }

    public void setBaseWages(final Decimal baseWages) {
        this.baseWages = baseWages;
    }

    public Decimal getIncentivePay() {
        return incentivePay;
    }

    public void setIncentivePay(final Decimal incentivePay) {
        this.incentivePay = incentivePay;
    }

    public Decimal getTotalComp() {
        return totalComp;
    }

    public void setTotalComp(final Decimal totalComp) {
        this.totalComp = totalComp;
    }

    public Decimal getBaseWagesLastYr() {
        return baseWagesLastYr;
    }

    public void setBaseWagesLastYr(final Decimal baseWagesLastYr) {
        this.baseWagesLastYr = baseWagesLastYr;
    }

    public Decimal getIncentivePayLastYr() {
        return incentivePayLastYr;
    }

    public void setIncentivePayLastYr(final Decimal incentivePayLastYr) {
        this.incentivePayLastYr = incentivePayLastYr;
    }

    public Decimal getTotalCompLastYr() {
        return totalCompLastYr;
    }

    public void setTotalCompLastYr(final Decimal totalCompLastYr) {
        this.totalCompLastYr = totalCompLastYr;
    }

    public Decimal getIncentivePayPerPair() {
        return incentivePayPerPair;
    }

    public void setIncentivePayPerPair(final Decimal incentivePayPerPair) {
        this.incentivePayPerPair = incentivePayPerPair;
    }

    public Decimal getIncentivePayPerPairLastYr() {
        return incentivePayPerPairLastYr;
    }

    public void setIncentivePayPerPairLastYr(final Decimal incentivePayPerPairLastYr) {
        this.incentivePayPerPairLastYr = incentivePayPerPairLastYr;
    }

    public Decimal getIndAvgBaseWage() {
        return indAvgBaseWage;
    }

    public void setIndAvgBaseWage(final Decimal indAvgBaseWage) {
        this.indAvgBaseWage = indAvgBaseWage;
    }

    public Decimal getIndAvgTotalComp() {
        return indAvgTotalComp;
    }

    public void setIndAvgTotalComp(final Decimal indAvgTotalComp) {
        this.indAvgTotalComp = indAvgTotalComp;
    }

    public Percent getPercentChngFromLastYr() {
        return percentChngFromLastYr;
    }

    public void setPercentChngFromLastYr(final Percent percentChngFromLastYr) {
        this.percentChngFromLastYr = percentChngFromLastYr;
    }

}
