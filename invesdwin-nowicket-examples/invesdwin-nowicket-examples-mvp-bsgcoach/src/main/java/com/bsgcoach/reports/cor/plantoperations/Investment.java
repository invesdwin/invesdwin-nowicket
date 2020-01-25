package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class Investment extends AValueObject {

    //  Gross at Beg of Yr
    private Decimal grossAtBegOfYr;
    //    + Upgrade (last yr)
    private Decimal plusUpgradeLastYr;
    //    + Const (last yr)
    private Decimal plusConstLastYr;
    //    + Purch (this yr)
    private Decimal plusPurchThisYr;
    //    – Sold (this yr)
    private Decimal sumSoldThisYr;
    //  Gross Invest This Yr
    private Decimal grossInvestThisYr;
    //  Accum Dep (begin)
    private Decimal accumDepBegin;
    //  Depreciation this Yr
    private Decimal depreciationThisYr;
    //  Net Invest (this yr)
    private Decimal netInvestThisYr;
    //    EE Initiatives
    private Decimal eeInitiatives;
    //  Work in Progress
    private Decimal workInProgress;

    public Decimal getGrossAtBegOfYr() {
        return grossAtBegOfYr;
    }

    public void setGrossAtBegOfYr(final Decimal grossAtBegOfYr) {
        this.grossAtBegOfYr = grossAtBegOfYr;
    }

    public Decimal getPlusUpgradeLastYr() {
        return plusUpgradeLastYr;
    }

    public void setPlusUpgradeLastYr(final Decimal plusUpgradeLastYr) {
        this.plusUpgradeLastYr = plusUpgradeLastYr;
    }

    public Decimal getPlusConstLastYr() {
        return plusConstLastYr;
    }

    public void setPlusConstLastYr(final Decimal plusConstLastYr) {
        this.plusConstLastYr = plusConstLastYr;
    }

    public Decimal getPlusPurchThisYr() {
        return plusPurchThisYr;
    }

    public void setPlusPurchThisYr(final Decimal plusPurchThisYr) {
        this.plusPurchThisYr = plusPurchThisYr;
    }

    public Decimal getSumSoldThisYr() {
        return sumSoldThisYr;
    }

    public void setSumSoldThisYr(final Decimal sumSoldThisYr) {
        this.sumSoldThisYr = sumSoldThisYr;
    }

    public Decimal getGrossInvestThisYr() {
        return grossInvestThisYr;
    }

    public void setGrossInvestThisYr(final Decimal grossInvestThisYr) {
        this.grossInvestThisYr = grossInvestThisYr;
    }

    public Decimal getAccumDepBegin() {
        return accumDepBegin;
    }

    public void setAccumDepBegin(final Decimal accumDepBegin) {
        this.accumDepBegin = accumDepBegin;
    }

    public Decimal getDepreciationThisYr() {
        return depreciationThisYr;
    }

    public void setDepreciationThisYr(final Decimal depreciationThisYr) {
        this.depreciationThisYr = depreciationThisYr;
    }

    public Decimal getNetInvestThisYr() {
        return netInvestThisYr;
    }

    public void setNetInvestThisYr(final Decimal netInvestThisYr) {
        this.netInvestThisYr = netInvestThisYr;
    }

    public Decimal getEeInitiatives() {
        return eeInitiatives;
    }

    public void setEeInitiatives(final Decimal eeInitiatives) {
        this.eeInitiatives = eeInitiatives;
    }

    public Decimal getWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(final Decimal workInProgress) {
        this.workInProgress = workInProgress;
    }

}
