package com.bsgcoach.rules.mismatchofmarketingandproduction.numberofmodels;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.reports.cor.plantoperations.OptLetter;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.mismatchofmarketingandproduction.AMismatchOfMarketingAndProduction;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class AMismatchOfMarketingAndProductionNumberOfModelsExists extends AMismatchOfMarketingAndProduction {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can install plant upgrades in order to specialize their production towards a desired type of product. Depending on the type of upgrades, specialized production plants produce specific types of products more effecient in comparison to non-specialized production plants. Your production plant in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " has Upgrade B installed and currently operates at "
                + FeedbackFormatter.highlightVariable(getNumberOfModels(reports).toFormattedString())
                + " models. You should try to match your market position with your plant specialization. Consider that Upgrade B achieves highest cost saving effects at high number of models.";
    }

    @Override
    protected OptLetter getOptLetter() {
        return OptLetter.B;
    }

    @Override
    protected boolean shouldOptLetterExist() {
        return true;
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.NumberOfModels;
    }

    @Override
    protected Significance internalGetSignificance(final Reports reports) {
        final Decimal numberOfModels = getNumberOfModels(reports);
        //        50-100
        if (numberOfModels.isLessThanOrEqualTo(100)) {
            return Significance._10;
        }
        //        150
        if (numberOfModels.isLessThanOrEqualTo(150)) {
            return Significance._9;
        }
        //        200
        if (numberOfModels.isLessThanOrEqualTo(200)) {
            return Significance._8;
        }
        //        250
        if (numberOfModels.isLessThanOrEqualTo(250)) {
            return Significance._7;
        }
        //        300
        if (numberOfModels.isLessThanOrEqualTo(300)) {
            return Significance._6;
        }
        //        350
        if (numberOfModels.isLessThanOrEqualTo(350)) {
            return Significance._3;
        }
        //        500
        if (numberOfModels.isLessThanOrEqualTo(500)) {
            return Significance._1;
        }

        return null;
    }

    private Decimal getNumberOfModels(final Reports reports) {
        return reports.getCor()
                .getPlantOperations(getCompanyRegion())
                .getBrandedProduction()
                .getNumberOfModelsCurrent();
    }

}
