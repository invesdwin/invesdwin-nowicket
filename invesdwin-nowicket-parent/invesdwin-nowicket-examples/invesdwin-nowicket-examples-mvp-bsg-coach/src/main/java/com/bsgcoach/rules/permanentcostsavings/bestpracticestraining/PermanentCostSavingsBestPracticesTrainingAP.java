package com.bsgcoach.rules.permanentcostsavings.bestpracticestraining;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class PermanentCostSavingsBestPracticesTrainingAP extends APermanentCostSavingsBestPracticesTraining {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.AsiaPacific;
    }

}
