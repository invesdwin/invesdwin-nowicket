package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class ProductivityCalc extends AValueObject {

    //    Ind Avg Base Prod   
    private Decimal indAvgBaseProd;
    //    Beginning Base  
    private Decimal beginningBase;
    //      % Chng--  Wage
    private Percent percentChngWage;
    //        BestP
    private Percent percentChngBestP;
    //        Opt D
    private Percent percentChngOptD;
    //    New Base (this yr)  
    private Decimal newBaseThisYr;
    //      % Chng--  Incentive
    private Percent percentChngIncentive;
    //        Tot Comp
    private Percent percentChngTotComp;
    //    Productivity (this yr)  
    private Decimal productivityThisYr;
    //        Last Year
    private Decimal productivityLastYr;

    public Decimal getIndAvgBaseProd() {
        return indAvgBaseProd;
    }

    public void setIndAvgBaseProd(final Decimal indAvgBaseProd) {
        this.indAvgBaseProd = indAvgBaseProd;
    }

    public Decimal getBeginningBase() {
        return beginningBase;
    }

    public void setBeginningBase(final Decimal beginningBase) {
        this.beginningBase = beginningBase;
    }

    public Percent getPercentChngWage() {
        return percentChngWage;
    }

    public void setPercentChngWage(final Percent percentChngWage) {
        this.percentChngWage = percentChngWage;
    }

    public Percent getPercentChngBestP() {
        return percentChngBestP;
    }

    public void setPercentChngBestP(final Percent percentChngBestP) {
        this.percentChngBestP = percentChngBestP;
    }

    public Percent getPercentChngOptD() {
        return percentChngOptD;
    }

    public void setPercentChngOptD(final Percent percentChngOptD) {
        this.percentChngOptD = percentChngOptD;
    }

    public Decimal getNewBaseThisYr() {
        return newBaseThisYr;
    }

    public void setNewBaseThisYr(final Decimal newBaseThisYr) {
        this.newBaseThisYr = newBaseThisYr;
    }

    public Percent getPercentChngIncentive() {
        return percentChngIncentive;
    }

    public void setPercentChngIncentive(final Percent percentChngIncentive) {
        this.percentChngIncentive = percentChngIncentive;
    }

    public Percent getPercentChngTotComp() {
        return percentChngTotComp;
    }

    public void setPercentChngTotComp(final Percent percentChngTotComp) {
        this.percentChngTotComp = percentChngTotComp;
    }

    public Decimal getProductivityThisYr() {
        return productivityThisYr;
    }

    public void setProductivityThisYr(final Decimal productivityThisYr) {
        this.productivityThisYr = productivityThisYr;
    }

    public Decimal getProductivityLastYr() {
        return productivityLastYr;
    }

    public void setProductivityLastYr(final Decimal productivityLastYr) {
        this.productivityLastYr = productivityLastYr;
    }

}
