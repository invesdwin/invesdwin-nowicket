package com.bsgcoach.rules.economiesofscale.plantcapacity;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class EconomiesOfScalePlantCapacityNA extends AEconomiesOfScalePlantCapacity {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.NorthAmerica;
    }

}
