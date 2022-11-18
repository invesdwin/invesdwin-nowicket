package com.bsgcoach.rules.economiesofscale.capacityutilization;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class EconomiesOfScaleCapacityUtilizationNA extends AEconomiesOfScaleCapacityUtilization {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.NorthAmerica;
    }

}
