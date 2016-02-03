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
public abstract class AMismatchOfMarketingAndProductionNumberOfModelsExistsNot extends
AMismatchOfMarketingAndProduction {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can install plant upgrades in order to specialize their production towards a desired type of product. Specialized production plants produce corresponding types of products more effecient in comparison to non-specialized production plants. Your production plant in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " currently operates at "
                + FeedbackFormatter.highlightVariable(getNumberOfModels(reports).toFormattedString())
                + " models although this plant is not specialised for this type of production set-up. You should try to match your market position with your plant specialization. ";
    }

    @Override
    protected OptLetter getOptLetter() {
        return OptLetter.B;
    }

    @Override
    protected boolean shouldOptLetterExist() {
        return false;
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.NumberOfModels;
    }

    //CHECKSTYLE:OFF
    @Override
    protected Significance internalGetSignificance(final Reports reports) {
        //CHECKSTYLE:ON
        final Decimal numberOfModels = getNumberOfModels(reports);
        //        500
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("500"))) {
            return Significance._10;
        }
        //        350
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("350"))) {
            return Significance._8;
        }
        //        300
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("300"))) {
            return Significance._6;
        }
        //        250
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("250"))) {
            return Significance._5;
        }
        //        200
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("200"))) {
            return Significance._4;
        }
        //        150
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("150"))) {
            return Significance._3;
        }
        //        100
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("100"))) {
            return Significance._2;
        }
        //        50
        if (numberOfModels.isGreaterThanOrEqualTo(new Decimal("50"))) {
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
