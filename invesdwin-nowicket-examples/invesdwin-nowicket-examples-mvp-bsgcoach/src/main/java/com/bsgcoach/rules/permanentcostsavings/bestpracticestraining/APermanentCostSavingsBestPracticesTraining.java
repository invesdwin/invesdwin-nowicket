package com.bsgcoach.rules.permanentcostsavings.bestpracticestraining;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.IRule;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class APermanentCostSavingsBestPracticesTraining implements IRule {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Best practice is an accredited management standard which aims at improving the efficiency of a company by developing and following the best way of doing things. In the BSG your company can spend an annual amount of up to $ 5,000 per worker for Best Practices Training (BPT) to increase their productivity. Your company currently spends a total of $ "
                + FeedbackFormatter.highlightVariable(getBestPracticesTrainingPerPair(reports).toFormattedString())
                + " per output unit for BPT in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + ". As BPT also leads to cumulative long-term costs savings it can be considered as an investment. Classical investment theor says that e.g. decisions on investments need to take investment costs, potential payoffs and the duration of the payoff period into account.";
    }

    private Decimal getBestPracticesTrainingPerPair(final Reports reports) {
        return reports.getCor()
                .getPlantOperations(getCompanyRegion())
                .getBrandedProduction()
                .getBestPracticesTrainingPerPair();
    }

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

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.BestPracticesTraining;
    }

    protected abstract CompanyRegion getCompanyRegion();

    private Significance internalGetSignificance(final Reports reports) {
        final Decimal capacity = getCapacity(reports);
        if (capacity != null && capacity.isPositiveNonZero()) {
            final Decimal bptPerPair = getBestPracticesTrainingPerPair(reports);
            //            <500
            if (bptPerPair.isLessThan(new Decimal("500"))) {
                return Significance._10;
            }
            //            500-999
            if (bptPerPair.isLessThan(new Decimal("1000"))) {
                return Significance._8;
            }
            //            1000-1499
            if (bptPerPair.isLessThan(new Decimal("1500"))) {
                return Significance._8;
            }
            //            1500-1999
            if (bptPerPair.isLessThan(new Decimal("2000"))) {
                return Significance._7;
            }
            //            2000-2499
            if (bptPerPair.isLessThan(new Decimal("2500"))) {
                return Significance._6;
            }
            //            2500-2999
            if (bptPerPair.isLessThan(new Decimal("3000"))) {
                return Significance._5;
            }
            //            3000-3499
            if (bptPerPair.isLessThan(new Decimal("3500"))) {
                return Significance._4;
            }
            //            3500-3999
            if (bptPerPair.isLessThan(new Decimal("4000"))) {
                return Significance._3;
            }
            //            4000-4499
            if (bptPerPair.isLessThan(new Decimal("4500"))) {
                return Significance._2;
            }
            //            >4499
            return Significance._1;
        } else {
            return null;
        }
    }

    private Decimal getCapacity(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getPlantCapacity().getCapacity();
    }

}
