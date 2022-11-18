package com.bsgcoach.rules.permanentcostsavings.bestpracticestraining;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class PermanentCostSavingsBestPracticesTrainingNA extends APermanentCostSavingsBestPracticesTraining {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.NorthAmerica;
    }

}
