package com.bsgcoach.rules.specializationupgrades;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class SpecializationUpgradesEA extends ASpecializationUpgrades {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.EuropeAfrica;
    }

}
