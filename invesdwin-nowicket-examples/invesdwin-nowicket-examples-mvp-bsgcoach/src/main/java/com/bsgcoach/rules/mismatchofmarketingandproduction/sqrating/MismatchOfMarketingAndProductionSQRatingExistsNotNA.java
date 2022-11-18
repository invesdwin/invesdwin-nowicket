package com.bsgcoach.rules.mismatchofmarketingandproduction.sqrating;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class MismatchOfMarketingAndProductionSQRatingExistsNotNA
        extends AMismatchOfMarketingAndProductionSQRatingExistsNot {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.NorthAmerica;
    }

}
