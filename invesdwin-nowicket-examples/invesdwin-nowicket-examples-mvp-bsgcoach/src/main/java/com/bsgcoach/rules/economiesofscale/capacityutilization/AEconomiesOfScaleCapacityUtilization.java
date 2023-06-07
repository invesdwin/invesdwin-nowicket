package com.bsgcoach.rules.economiesofscale.capacityutilization;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.IRule;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public abstract class AEconomiesOfScaleCapacityUtilization implements IRule {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Your production plant in " + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " operates at a capacity utilization of  "
                + FeedbackFormatter.highlightVariable(getUtilisation(reports).toString(PercentScale.PERCENT))
                + ". The theory of scale economies says that the higher the capacity utilization of a production plant is the lower the variable costs of production become. As a results, companies making use of scale effects operate at lower production costs per output unit and achieve a cost advantage on the market.";
    }

    private Percent getUtilisation(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getUtilisation();
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.CapacityUtilization;
    }

    protected abstract CompanyRegion getCompanyRegion();

    @Override
    public Significance getSignificance(final Reports reports) {
        final Decimal capacity = getCapacity(reports);
        if (capacity != null && capacity.isPositiveNonZero()) {
            final Decimal utilisationPercent = new Decimal(getUtilisation(reports).getValue(PercentScale.PERCENT));
            //        < 60
            if (utilisationPercent.isLessThan(new Decimal("60"))) {
                return Significance._10;
            }
            //        60-69
            if (utilisationPercent.isLessThan(new Decimal("70"))) {
                return Significance._9;
            }
            //        70-79
            if (utilisationPercent.isLessThan(new Decimal("80"))) {
                return Significance._8;
            }
            //        80-89
            if (utilisationPercent.isLessThan(new Decimal("90"))) {
                return Significance._7;
            }
            //        90-94
            if (utilisationPercent.isLessThan(new Decimal("95"))) {
                return Significance._6;
            }
            //        95-99
            if (utilisationPercent.isLessThan(new Decimal("100"))) {
                return Significance._5;
            }
            //        100-104
            if (utilisationPercent.isLessThan(new Decimal("105"))) {
                return Significance._4;
            }
            //        105-109
            if (utilisationPercent.isLessThan(new Decimal("110"))) {
                return Significance._3;
            }
            //        110-114
            if (utilisationPercent.isLessThan(new Decimal("114"))) {
                return Significance._2;
            }
            //        > 114
            return Significance._1;
        } else {
            return null;
        }
    }

    private Decimal getCapacity(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getPlantCapacity().getCapacity();
    }

}
