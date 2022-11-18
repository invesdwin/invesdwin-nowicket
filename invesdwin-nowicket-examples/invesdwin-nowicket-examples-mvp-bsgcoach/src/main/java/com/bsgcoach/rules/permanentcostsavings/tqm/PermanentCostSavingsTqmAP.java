package com.bsgcoach.rules.permanentcostsavings.tqm;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class PermanentCostSavingsTqmAP extends APermanentCostSavingsTqm {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.AsiaPacific;
    }

}
