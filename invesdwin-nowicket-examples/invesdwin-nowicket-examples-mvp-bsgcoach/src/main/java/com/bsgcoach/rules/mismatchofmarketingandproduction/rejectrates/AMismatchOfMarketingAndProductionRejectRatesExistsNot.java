package com.bsgcoach.rules.mismatchofmarketingandproduction.rejectrates;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.reports.cor.plantoperations.OptLetter;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.mismatchofmarketingandproduction.AMismatchOfMarketingAndProduction;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class AMismatchOfMarketingAndProductionRejectRatesExistsNot extends AMismatchOfMarketingAndProduction {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can install plant upgrades in order to specialize their production towards a desired type of product. Specialized production plants produce specific types of products more effecient in comparison to non-specialized production plants. Your production plant in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " currently operates at relatively high reject costs of $ "
                + FeedbackFormatter.highlightVariable(getRejectCostsPerPair(reports).toFormattedString())
                + " per pair although which is a result of mismatch between plant specialization and production set-up. You should consider either installing Upgrade A to reduce the costs of rejects (if possible) or matching your production set-up with your plant specialization.";
    }

    private Decimal getRejectCostsPerPair(final Reports reports) {
        //        Reject costs per pair   COR/Plant Operations/Branded Production/Cost/Pr------/Cost of Rejects
        return reports.getCor()
                .getPlantOperations(getCompanyRegion())
                .getBrandedProduction()
                .getCostPrProd()
                .getCostOfRejects();
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.CostsOfRejects;
    }

    @Override
    protected OptLetter getOptLetter() {
        return OptLetter.A;
    }

    @Override
    protected boolean shouldOptLetterExist() {
        return false;
    }

    //CHECKSTYLE:OFF
    @Override
    protected Significance internalGetSignificance(final Reports reports) {
        //CHECKSTYLE:ON
        final Decimal rejectCostsPerPair = getRejectCostsPerPair(reports);
        //        >2.5
        if (rejectCostsPerPair.isGreaterThan(new Decimal("2.5"))) {
            return Significance._10;
        }
        //        2.1-2.5
        if (rejectCostsPerPair.isGreaterThan(new Decimal("2.09"))) {
            return Significance._9;
        }
        //        1.9-2.09
        if (rejectCostsPerPair.isGreaterThan(new Decimal("1.89"))) {
            return Significance._8;
        }
        //        1.7-1.89
        if (rejectCostsPerPair.isGreaterThan(new Decimal("1.69"))) {
            return Significance._7;
        }
        //        1.5-1.69
        if (rejectCostsPerPair.isGreaterThan(new Decimal("1.49"))) {
            return Significance._6;
        }
        //        1.2-1.49
        if (rejectCostsPerPair.isGreaterThan(new Decimal("1.19"))) {
            return Significance._5;
        }
        //        1-1.19
        if (rejectCostsPerPair.isGreaterThan(new Decimal("0.99"))) {
            return Significance._4;
        }
        //        0.7-0.99
        if (rejectCostsPerPair.isGreaterThan(new Decimal("0.69"))) {
            return Significance._3;
        }
        //        0.5-0.69
        if (rejectCostsPerPair.isGreaterThan(new Decimal("0.5"))) {
            return Significance._2;
        }
        //        <0.5
        return Significance._1;
    }

}
