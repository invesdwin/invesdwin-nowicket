package com.bsgcoach.rules.economiesofscale.capacityutilization;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class EconomiesOfScaleCapacityUtilizationNA extends AEconomiesOfScaleCapacityUtilization {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.NorthAmerica;
    }

}
