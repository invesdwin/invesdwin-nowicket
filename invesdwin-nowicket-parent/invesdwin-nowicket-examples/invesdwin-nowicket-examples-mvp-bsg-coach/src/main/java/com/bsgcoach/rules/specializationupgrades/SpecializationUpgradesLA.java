package com.bsgcoach.rules.specializationupgrades;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class SpecializationUpgradesLA extends ASpecializationUpgrades {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.LatinAmerica;
    }

}
