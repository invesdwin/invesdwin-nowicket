package com.bsgcoach.rules.mismatchofmarketingandproduction.numberofmodels;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class MismatchOfMarketingAndProductionNumberOfModelsExistsAP extends
        AMismatchOfMarketingAndProductionNumberOfModelsExists {
    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.AsiaPacific;
    }

}
