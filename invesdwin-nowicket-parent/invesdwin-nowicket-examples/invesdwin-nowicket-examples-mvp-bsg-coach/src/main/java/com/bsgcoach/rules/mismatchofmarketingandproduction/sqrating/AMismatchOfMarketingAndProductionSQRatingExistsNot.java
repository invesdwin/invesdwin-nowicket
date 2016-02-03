package com.bsgcoach.rules.mismatchofmarketingandproduction.sqrating;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.reports.cor.plantoperations.OptLetter;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.mismatchofmarketingandproduction.AMismatchOfMarketingAndProduction;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class AMismatchOfMarketingAndProductionSQRatingExistsNot extends AMismatchOfMarketingAndProduction {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can install plant upgrades in order to specialize their production towards a desired type of product. Specialized production plants produce specific types of products more effecient in comparison to non-specialized production plants. Your production plant in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " currently operates at "
                + FeedbackFormatter.highlightVariable(getQuality(reports).toFormattedString())
                + " S/Q rating although this plant is not specialised for this type of production set-up. You should try to match your market position with your plant specialization.";
    }

    private Decimal getQuality(final Reports reports) {
        //        S/Q Rating  COR/Plant Operations/Branded Production/S/Q Rating (number of stars)
        return reports.getCor()
                .getPlantOperations(getCompanyRegion())
                .getBrandedProduction()
                .getSqRatingNumberOfStarsThisYr();
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.SQRating;
    }

    @Override
    protected OptLetter getOptLetter() {
        return OptLetter.C;
    }

    @Override
    protected boolean shouldOptLetterExist() {
        return false;
    }

    //CHECKSTYLE:OFF
    @Override
    protected Significance internalGetSignificance(final Reports reports) {
        //CHECKSTYLE:ON
        final Decimal quality = getQuality(reports);
        //        1
        if (quality.isLessThanOrEqualTo(new Decimal("1"))) {
            return Significance._10;
        }
        //        2
        if (quality.isLessThanOrEqualTo(new Decimal("2"))) {
            return Significance._9;
        }
        //        3
        if (quality.isLessThanOrEqualTo(new Decimal("3"))) {
            return Significance._8;
        }
        //        4
        if (quality.isLessThanOrEqualTo(new Decimal("4"))) {
            return Significance._7;
        }
        //        5
        if (quality.isLessThanOrEqualTo(new Decimal("5"))) {
            return Significance._6;
        }
        //         6
        if (quality.isLessThanOrEqualTo(new Decimal("6"))) {
            return Significance._5;
        }
        //         7
        if (quality.isLessThanOrEqualTo(new Decimal("7"))) {
            return Significance._4;
        }
        //         8
        if (quality.isLessThanOrEqualTo(new Decimal("8"))) {
            return Significance._3;
        }
        //         9
        if (quality.isLessThanOrEqualTo(new Decimal("9"))) {
            return Significance._2;
        }
        //         10
        if (quality.isLessThanOrEqualTo(new Decimal("10"))) {
            return Significance._1;
        }

        return null;
    }

}
