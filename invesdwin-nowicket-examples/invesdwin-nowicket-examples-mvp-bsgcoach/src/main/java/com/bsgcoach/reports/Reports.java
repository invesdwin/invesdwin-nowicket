package com.bsgcoach.reports;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.core.io.Resource;

import com.bsgcoach.reports.cir.CompetitiveIntelligenceReports;
import com.bsgcoach.reports.cir.parser.CompetitiveIntelligenceReportParser;
import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParser;
import com.bsgcoach.reports.fir.FootwearIndustryReports;
import com.bsgcoach.reports.fir.parser.FootwearIndustryReportParser;
import com.bsgcoach.resources.creditrating.CreditRating;
import com.bsgcoach.web.request.CompanyLetter;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.time.fdate.FDate;

@NotThreadSafe
public class Reports extends AValueObject {

    private final CompanyLetter companyLetter;
    private final int gameEndsInYear;
    private final CreditRating creditRating;
    private final FootwearIndustryReports fir;
    private final CompanyOperatingReports cor;
    private final CompetitiveIntelligenceReports cir;

    public Reports(final CompanyLetter companyLetter, final int gameEndsInYear, final CreditRating creditRating,
            final Resource firReportCsv, final Resource corReportCsv, final Resource cirReportCsv) throws Exception {
        this.companyLetter = companyLetter;
        this.gameEndsInYear = gameEndsInYear;
        this.creditRating = creditRating;
        try {
            this.fir = new FootwearIndustryReportParser(firReportCsv).call();
        } catch (final Throwable t) {
            throw new RuntimeException("Error with <b>FIR</b> report on " + new FDate() + ".", t);
        }
        try {
            this.cor = new CompanyOperatingReportParser(corReportCsv).call();
        } catch (final Throwable t) {
            throw new RuntimeException("Error with <b>COR</b> report on " + new FDate() + ".", t);
        }
        try {
            this.cir = new CompetitiveIntelligenceReportParser(cirReportCsv).call();
        } catch (final Throwable t) {
            throw new RuntimeException("Error with <b>CIR</b> report on " + new FDate() + ".", t);
        }
    }

    public CompanyLetter getCompanyLetter() {
        return companyLetter;
    }

    public int getGameEndsInYear() {
        return gameEndsInYear;
    }

    public CreditRating getCreditRating() {
        return creditRating;
    }

    public FootwearIndustryReports getFir() {
        return fir;
    }

    public CompanyOperatingReports getCor() {
        return cor;
    }

    public CompetitiveIntelligenceReports getCir() {
        return cir;
    }

    public int getRoundsLeftToPlay() {
        return gameEndsInYear - cir.getLastYear();
    }

}
