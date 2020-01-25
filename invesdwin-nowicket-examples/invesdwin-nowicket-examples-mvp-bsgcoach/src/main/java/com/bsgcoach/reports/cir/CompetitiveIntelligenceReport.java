package com.bsgcoach.reports.cir;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyLetter;
import com.bsgcoach.web.request.CompanyRegion;

@NotThreadSafe
public class CompetitiveIntelligenceReport {

    private final int year;
    private final CompanyRegion companyRegion;
    private final CompanyLetter companyLetter;
    private final Internet internet = new Internet();
    private final Wholesale wholesale = new Wholesale();
    private final PLabel pLabel = new PLabel();
    private boolean hasValues;

    public CompetitiveIntelligenceReport(final int year, final CompanyRegion companyRegion,
            final CompanyLetter companyLetter) {
        this.year = year;
        this.companyRegion = companyRegion;
        this.companyLetter = companyLetter;
    }

    public int getYear() {
        return year;
    }

    public CompanyRegion getCompanyRegion() {
        return companyRegion;
    }

    public CompanyLetter getCompanyLetter() {
        return companyLetter;
    }

    public boolean isAvg() {
        return companyLetter == null;
    }

    public void setHasValues() {
        this.hasValues = true;
    }

    public boolean isHasValues() {
        return hasValues;
    }

    public Internet getInternet() {
        return internet;
    }

    public Wholesale getWholesale() {
        return wholesale;
    }

    public PLabel getPLabel() {
        return pLabel;
    }

}
