package com.bsgcoach.rules.economiesofscale.plantcapacity;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.IRule;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class AEconomiesOfScalePlantCapacity implements IRule {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Your plant in " + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " has a regular production capacity of "
                + FeedbackFormatter.highlightVariable(getCapacityInMillions(reports).toFormattedString())
                + "M pairs. The theory of scale economies says that the higher the capacity of a production plant and its capacity utilization the lower the variable production costs become. As a results, companies making use of scale effects operate at lower production costs per output unit and achieve a cost advantage on the market.";
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.PlantCapacity;
    }

    protected abstract CompanyRegion getCompanyRegion();

    @Override
    public Significance getSignificance(final Reports reports) {
        final Decimal capacity = getCapacityInMillions(reports);
        if (capacity != null && capacity.isPositiveNonZero()) {
            //        <1.5M
            if (capacity.isLessThan(new Decimal("1.5"))) {
                return Significance._10;
            }
            //        1.5M-1.9M
            if (capacity.isLessThanOrEqualTo(new Decimal("2"))) {
                return Significance._9;
            }
            //        2M-2.4M
            if (capacity.isLessThan(new Decimal("2.5"))) {
                return Significance._8;
            }
            //        2.5M-2.9M
            if (capacity.isLessThan(new Decimal("3"))) {
                return Significance._7;
            }
            //        3M-3.4M
            if (capacity.isLessThan(new Decimal("3.5"))) {
                return Significance._6;
            }
            //        3.5M-3.9M
            if (capacity.isLessThan(new Decimal("4"))) {
                return Significance._5;
            }
            //        4M-4.4M
            if (capacity.isLessThan(new Decimal("4.5"))) {
                return Significance._4;
            }
            //        4.5M-4.9M
            if (capacity.isLessThan(new Decimal("5"))) {
                return Significance._3;
            }
            //        5M-5.4M
            if (capacity.isLessThan(new Decimal("5.5"))) {
                return Significance._2;
            }
            return Significance._1;
        } else {
            return null;
        }
    }

    private Decimal getCapacityInMillions(final Reports reports) {
        final Decimal maximumCapacity = reports.getCor()
                .getPlantOperations(getCompanyRegion())
                .getPlantCapacity()
                .getMaxCapacity();
        if (maximumCapacity == null) {
            return null;
        }
        return maximumCapacity.divide(1000);
    }

}
