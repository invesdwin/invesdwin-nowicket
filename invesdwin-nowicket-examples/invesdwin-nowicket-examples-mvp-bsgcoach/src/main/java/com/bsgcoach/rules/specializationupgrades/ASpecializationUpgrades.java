package com.bsgcoach.rules.specializationupgrades;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.resources.correspondingtheories.CorrespondingTheories;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.FeedbackFormatter;
import com.bsgcoach.rules.IRule;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class ASpecializationUpgrades implements IRule {

    @Override
    public String getFeedbackText(final Reports reports) {
        return "Companies can install plant upgrades in order to specialize their production towards a desired type of product. Specialized production plants produce specific types of products more effecient in comparison to non-specialized production plants. A higher production efficiency can lead to competetitive advantages over rival companies. Your production plant in "
                + FeedbackFormatter.highlightVariable(getCompanyRegion().getShortTitle())
                + " is not yet fully specialized. You should consider plant upgrades for the long-term strategy of your company.";
    }

    private Decimal getNumberOfUpgrades(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getUpgradeOptions().getNumberOfUpgrades();
    }

    @Override
    public CorrespondingTheories getCorrespondingTheories() {
        return CorrespondingTheories.PlantUpgrades;
    }

    protected abstract CompanyRegion getCompanyRegion();

    @Override
    public Significance getSignificance(final Reports reports) {
        final Decimal capacity = getCapacity(reports);
        if (capacity != null && capacity.isPositiveNonZero()) {
            final Decimal numberOfUpgrades = getNumberOfUpgrades(reports);
            //          0
            if (numberOfUpgrades.isLessThan(new Decimal("1"))) {
                return Significance._10;
            }
            //          1
            if (numberOfUpgrades.isLessThan(new Decimal("2"))) {
                return Significance._8;
            }
            //          2
            return Significance._1;
        } else {
            return null;
        }
    }

    private Decimal getCapacity(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getPlantCapacity().getCapacity();
    }

}
