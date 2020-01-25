package com.bsgcoach.reports.fir;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyLetter;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;

@NotThreadSafe
public class FootwearIndustryReports extends AValueObject {

    private final ALoadingCache<CompanyLetter, FootwearIndustryReport> forCompany = new ALoadingCache<CompanyLetter, FootwearIndustryReport>() {
        @Override
        protected FootwearIndustryReport loadValue(final CompanyLetter key) {
            return new FootwearIndustryReport(key);
        }
    };
    private final FootwearIndustryReport avg = new FootwearIndustryReport(null);

    public FootwearIndustryReport getForCompany(final CompanyLetter companyLetter) {
        return forCompany.get(companyLetter);
    }

    public FootwearIndustryReport getAvg() {
        return avg;
    }

}
