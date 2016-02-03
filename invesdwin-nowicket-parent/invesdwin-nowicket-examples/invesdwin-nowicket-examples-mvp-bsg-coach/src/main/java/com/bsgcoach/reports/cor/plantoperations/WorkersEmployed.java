package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class WorkersEmployed extends AValueObject {

    //    Branded Production  
    private Decimal brandedProduction;
    //    P-Label Production  
    private Decimal pLabelProduction;
    //      Total Workers Emp 
    private Decimal totalWorkersEmp;
    //        Last Year
    private Decimal totalWorkersEmpLastYr;

    public Decimal getBrandedProduction() {
        return brandedProduction;
    }

    public void setBrandedProduction(final Decimal brandedProduction) {
        this.brandedProduction = brandedProduction;
    }

    public Decimal getpLabelProduction() {
        return pLabelProduction;
    }

    public void setpLabelProduction(final Decimal pLabelProduction) {
        this.pLabelProduction = pLabelProduction;
    }

    public Decimal getTotalWorkersEmp() {
        return totalWorkersEmp;
    }

    public void setTotalWorkersEmp(final Decimal totalWorkersEmp) {
        this.totalWorkersEmp = totalWorkersEmp;
    }

    public Decimal getTotalWorkersEmpLastYr() {
        return totalWorkersEmpLastYr;
    }

    public void setTotalWorkersEmpLastYr(final Decimal totalWorkersEmpLastYr) {
        this.totalWorkersEmpLastYr = totalWorkersEmpLastYr;
    }

}
