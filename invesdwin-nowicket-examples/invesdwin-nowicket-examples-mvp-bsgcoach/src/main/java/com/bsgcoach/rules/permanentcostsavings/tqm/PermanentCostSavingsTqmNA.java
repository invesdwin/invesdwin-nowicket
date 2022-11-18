package com.bsgcoach.rules.permanentcostsavings.tqm;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class PermanentCostSavingsTqmNA extends APermanentCostSavingsTqm {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.NorthAmerica;
    }

}
