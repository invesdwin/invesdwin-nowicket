package com.bsgcoach.rules.economiesofscale.plantcapacity;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class EconomiesOfScalePlantCapacityEA extends AEconomiesOfScalePlantCapacity {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.EuropeAfrica;
    }

}
