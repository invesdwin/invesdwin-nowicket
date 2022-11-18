package com.bsgcoach.rules.mismatchofmarketingandproduction.rejectrates;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.web.request.CompanyRegion;

import jakarta.inject.Named;

@Named
@Immutable
public class MismatchOfMarketingAndProductionRejectRatesExistsNotAP
        extends AMismatchOfMarketingAndProductionRejectRatesExistsNot {

    @Override
    protected CompanyRegion getCompanyRegion() {
        return CompanyRegion.AsiaPacific;
    }

}
