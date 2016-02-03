package com.bsgcoach.rules.mismatchofmarketingandproduction.sqrating;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import com.bsgcoach.web.request.CompanyRegion;

@Named
@Immutable
public class MismatchOfMarketingAndProductionSQRatingExistsEA extends AMismatchOfMarketingAndProductionSQRatingExists {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.EuropeAfrica;
    }

}
