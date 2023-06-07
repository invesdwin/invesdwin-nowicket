package com.bsgcoach.rules.permanentcostsavings.tqm;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.IRule;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class APermanentCostSavingsTqm implements IRule {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Total Quality Management (TQM) is a concept which aims at improving the long-term production performance of a company. Spendings on TQM can be treated as an investment which causes initial investment costs and results in permanent cost savings. Out of a maximum of $ 2.50, your company currently spends $ "
                + FeedbackFormatter.highlightVariable(getTqmPair(reports).toFormattedString())
                + " per pair for Total Quality Management (TQM) in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + ". When making decision on TQM, you should consider the classical investment theory which says that e.g. decisions on investments need to take investment costs, potential payoffs and the duration of the payoff period into account.";
    }

    private Decimal getTqmPair(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getBrandedProduction().getTqmPerPair();
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.TotalQualityManagement;
    }

    protected abstract CompanyRegion getCompanyRegion();

    @Override
    public Significance getSignificance(final Reports reports) {
        final Significance s = internalGetSignificance(reports);
        if (s == null) {
            return null;
        }

        //            if GAME STAGE = -1 then OUT_TQM_PAIR(NA) -5 and OUT_TQM_PAIR(LA) -5 and OUT_TQM_PAIR(EA) -5 and OUT_TQM_PAIR(LA) -5
        if (reports.getRoundsLeftToPlay() == 1) {
            return s.minus(5);
        }
        //            elseif GAME STAGE = -2 then OUT_TQM_PAIR(NA) -4 and OUT_TQM_PAIR(LA) -4 and OUT_TQM_PAIR(EA) -4 and OUT_TQM_PAIR(LA) -4
        if (reports.getRoundsLeftToPlay() == 2) {
            return s.minus(4);
        }
        //            elseif GAME STAGE = -3 then OUT_TQM_PAIR(NA) -3 and OUT_TQM_PAIR(LA) -3 and OUT_TQM_PAIR(EA) -3 and OUT_TQM_PAIR(LA) -3
        if (reports.getRoundsLeftToPlay() == 3) {
            return s.minus(3);
        }
        //            elseif GAME STAGE = -4 then OUT_TQM_PAIR(NA) -2 and OUT_TQM_PAIR(LA) -2 and OUT_TQM_PAIR(EA) -2 and OUT_TQM_PAIR(LA) -2
        if (reports.getRoundsLeftToPlay() == 4) {
            return s.minus(2);
        }
        //            elseif GAME STAGE = -5 then OUT_TQM_PAIR(NA) -1 and OUT_TQM_PAIR(LA) -1 and OUT_TQM_PAIR(EA) -1 and OUT_TQM_PAIR(LA) -1
        if (reports.getRoundsLeftToPlay() == 5) {
            return s.minus(1);
        }

        return s;
    }

    private Significance internalGetSignificance(final Reports reports) {
        final Decimal capacity = getCapacity(reports);
        if (capacity != null && capacity.isPositiveNonZero()) {
            final Decimal tqmPairInUnits = getTqmPair(reports);
            //            <1.0
            if (tqmPairInUnits.isLessThan(new Decimal("1"))) {
                return Significance._10;
            }
            //            1.0-1.4
            if (tqmPairInUnits.isLessThan(new Decimal("1.5"))) {
                return Significance._9;
            }
            //            1.5-1.6
            if (tqmPairInUnits.isLessThan(new Decimal("1.7"))) {
                return Significance._8;
            }
            //            1.7-1.8
            if (tqmPairInUnits.isLessThan(new Decimal("1.9"))) {
                return Significance._7;
            }
            //            1.9-2.0
            if (tqmPairInUnits.isLessThan(new Decimal("2.1"))) {
                return Significance._6;
            }
            //            2.1
            if (tqmPairInUnits.isLessThan(new Decimal("2.2"))) {
                return Significance._5;
            }
            //            2.2
            if (tqmPairInUnits.isLessThan(new Decimal("2.3"))) {
                return Significance._4;
            }
            //            2.3
            if (tqmPairInUnits.isLessThan(new Decimal("2.4"))) {
                return Significance._3;
            }
            //            2.4
            if (tqmPairInUnits.isLessThan(new Decimal("2.5"))) {
                return Significance._2;
            }
            //            2.5
            return Significance._1;
        } else {
            return null;
        }
    }

    private Decimal getCapacity(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getPlantCapacity().getCapacity();
    }

}
