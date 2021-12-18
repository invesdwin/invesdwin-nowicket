package com.bsgcoach.reports.fir.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.bsgcoach.reports.fir.FootwearIndustryReports;
import com.bsgcoach.web.request.CompanyLetter;

import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class FootwearIndustryReportParserTest {

    @Test
    public void testParse() throws Exception {
        final FootwearIndustryReportParser parser = new FootwearIndustryReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/FIR_Y19.csv"));
        final FootwearIndustryReports reports = parser.call();
        Assertions.assertThat(reports.getAvg().isHasValues()).isTrue();
        Assertions.assertThat(reports.getForCompany(CompanyLetter.A).isHasValues()).isTrue();
        Assertions.assertThat(reports.getForCompany(CompanyLetter.L).isHasValues()).isFalse();
    }

}
