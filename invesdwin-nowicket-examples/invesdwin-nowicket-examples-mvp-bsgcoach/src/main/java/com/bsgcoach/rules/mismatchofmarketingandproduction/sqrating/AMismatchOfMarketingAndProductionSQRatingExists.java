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
public abstract class AMismatchOfMarketingAndProductionSQRatingExists extends AMismatchOfMarketingAndProduction {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can install plant upgrades in order to specialize their production towards a desired type of product. Specialized production plants produce specific types of products more effecient in comparison to non-specialized production plants. Your production plant in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " has Upgrade C installed and currently operates at "
                + FeedbackFormatter.highlightVariable(getQuality(reports).toFormattedString())
                + " S/Q rating. You should try to match your market position with your plant specialization. Consider that Upgrade C achieves highest cost saving effects at low S/Q ratings.";
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
        return true;
    }

    @Override
    protected Significance internalGetSignificance(final Reports reports) {
        final Decimal quality = getQuality(reports);
        //        10
        if (quality.isGreaterThanOrEqualTo(new Decimal("10"))) {
            return Significance._10;
        }
        //        9
        if (quality.isGreaterThanOrEqualTo(new Decimal("9"))) {
            return Significance._9;
        }
        //        8
        if (quality.isGreaterThanOrEqualTo(new Decimal("8"))) {
            return Significance._8;
        }
        //        7
        if (quality.isGreaterThanOrEqualTo(new Decimal("7"))) {
            return Significance._7;
        }
        //        6
        if (quality.isGreaterThanOrEqualTo(new Decimal("6"))) {
            return Significance._6;
        }
        //         5
        if (quality.isGreaterThanOrEqualTo(new Decimal("5"))) {
            return Significance._5;
        }
        //         4
        if (quality.isGreaterThanOrEqualTo(new Decimal("4"))) {
            return Significance._4;
        }
        //         3
        if (quality.isGreaterThanOrEqualTo(new Decimal("3"))) {
            return Significance._3;
        }
        //         2
        if (quality.isGreaterThanOrEqualTo(new Decimal("2"))) {
            return Significance._2;
        }
        //         1
        if (quality.isGreaterThanOrEqualTo(new Decimal("1"))) {
            return Significance._1;
        }
        return null;
    }

}
