package com.bsgcoach.rules.mismatchofmarketingandproduction.numberofmodels;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class MismatchOfMarketingAndProductionNumberOfModelsExistsEA
        extends AMismatchOfMarketingAndProductionNumberOfModelsExists {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.EuropeAfrica;
    }

}
