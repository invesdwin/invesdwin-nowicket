package com.bsgcoach.rules.mismatchofmarketingandproduction.numberofmodels;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class MismatchOfMarketingAndProductionNumberOfModelsExistsNotAP extends
        AMismatchOfMarketingAndProductionNumberOfModelsExistsNot {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.AsiaPacific;
    }

}
