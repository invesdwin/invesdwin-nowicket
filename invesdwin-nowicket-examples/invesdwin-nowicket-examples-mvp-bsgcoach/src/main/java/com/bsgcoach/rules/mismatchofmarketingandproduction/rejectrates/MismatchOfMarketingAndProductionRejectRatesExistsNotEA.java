package com.bsgcoach.rules.mismatchofmarketingandproduction.rejectrates;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class MismatchOfMarketingAndProductionRejectRatesExistsNotEA
        extends AMismatchOfMarketingAndProductionRejectRatesExistsNot {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.EuropeAfrica;
    }

}
