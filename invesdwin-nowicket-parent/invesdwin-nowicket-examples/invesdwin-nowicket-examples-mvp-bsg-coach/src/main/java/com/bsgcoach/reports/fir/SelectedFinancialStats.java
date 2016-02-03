package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class SelectedFinancialStats extends AValueObject {

    //    Cost %
    private Percent costPercent;
    //    Whse %
    private Percent whsePercent;
    //    Mktng %
    private Percent mktngPercent;
    //    Admin %
    private Percent adminPercent;
    //    OpProf %
    private Percent opProfPercent;
    //    NetProf %
    private Percent netProfPercent;
    //    Cur Ratio
    private Percent curRatio;
    //    InvDays
    private Decimal invDays;

    public Percent getCostPercent() {
        return costPercent;
    }

    public void setCostPercent(final Percent costPercent) {
        this.costPercent = costPercent;
    }

    public Percent getWhsePercent() {
        return whsePercent;
    }

    public void setWhsePercent(final Percent whsePercent) {
        this.whsePercent = whsePercent;
    }

    public Percent getMktngPercent() {
        return mktngPercent;
    }

    public void setMktngPercent(final Percent mktngPercent) {
        this.mktngPercent = mktngPercent;
    }

    public Percent getAdminPercent() {
        return adminPercent;
    }

    public void setAdminPercent(final Percent adminPercent) {
        this.adminPercent = adminPercent;
    }

    public Percent getOpProfPercent() {
        return opProfPercent;
    }

    public void setOpProfPercent(final Percent opProfPercent) {
        this.opProfPercent = opProfPercent;
    }

    public Percent getNetProfPercent() {
        return netProfPercent;
    }

    public void setNetProfPercent(final Percent netProfPercent) {
        this.netProfPercent = netProfPercent;
    }

    public Percent getCurRatio() {
        return curRatio;
    }

    public void setCurRatio(final Percent curRatio) {
        this.curRatio = curRatio;
    }

    public Decimal getInvDays() {
        return invDays;
    }

    public void setInvDays(final Decimal invDays) {
        this.invDays = invDays;
    }

}
