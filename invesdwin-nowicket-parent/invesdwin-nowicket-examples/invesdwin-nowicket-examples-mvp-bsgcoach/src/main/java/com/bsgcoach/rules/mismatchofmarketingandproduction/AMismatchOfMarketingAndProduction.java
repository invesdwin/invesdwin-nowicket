package com.bsgcoach.rules.mismatchofmarketingandproduction;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.Reports;
import com.bsgcoach.reports.cor.plantoperations.OptLetter;
import com.bsgcoach.resources.significance.Significance;
import com.bsgcoach.rules.IRule;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public abstract class AMismatchOfMarketingAndProduction implements IRule {

    protected abstract OptLetter getOptLetter();

    protected abstract boolean shouldOptLetterExist();

    @Override
    public Significance getSignificance(final Reports reports) {
        final Decimal capacity = getCapacity(reports);
        if (capacity != null && capacity.isPositiveNonZero()) {
            if (shouldOptLetterExist() && hasOptLetterOnline(reports)) {
                internalGetSignificance(reports);
            } else if (!shouldOptLetterExist() && !hasOptLetterOnline(reports)) {
                internalGetSignificance(reports);
            }
        }
        return null;
    }

    protected abstract Significance internalGetSignificance(Reports reports);

    private boolean hasOptLetterOnline(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getUpgradeOptions().getOnline().hasOptLetter(
                getOptLetter());
    }

    private Decimal getCapacity(final Reports reports) {
        return reports.getCor().getPlantOperations(getCompanyRegion()).getPlantCapacity().getCapacity();
    }

    protected abstract CompanyRegion getCompanyRegion();

}
