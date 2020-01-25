package com.bsgcoach.rules.permanentcostsavings.tqm;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class PermanentCostSavingsTqmAP extends APermanentCostSavingsTqm {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.AsiaPacific;
    }

}
