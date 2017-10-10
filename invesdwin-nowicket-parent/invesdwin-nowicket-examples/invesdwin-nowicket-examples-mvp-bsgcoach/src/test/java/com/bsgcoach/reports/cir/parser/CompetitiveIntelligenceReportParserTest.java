package com.bsgcoach.reports.cir.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.bsgcoach.reports.cir.CompetitiveIntelligenceReports;
import com.bsgcoach.web.request.CompanyLetter;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class CompetitiveIntelligenceReportParserTest {

    @Test
    public void testParse() throws Exception {
        final CompetitiveIntelligenceReportParser parser = new CompetitiveIntelligenceReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/CIR_Y19.csv"));
        final CompetitiveIntelligenceReports reports = parser.call();
        Assertions.assertThat(reports.getFirstYear()).isEqualTo(10);
        Assertions.assertThat(reports.getLastYear()).isEqualTo(19);
        for (final CompanyRegion region : CompanyRegion.values()) {
            for (int i = reports.getFirstYear(); i <= reports.getLastYear(); i++) {
                try {
                    Assertions.assertThat(reports.getAvg(i, region).isHasValues()).isTrue();
                    Assertions.assertThat(reports.getForCompany(i, region, CompanyLetter.A).isHasValues()).isTrue();
                    Assertions.assertThat(reports.getForCompany(i, region, CompanyLetter.L).isHasValues()).isFalse();
                } catch (final Throwable t) {
                    throw new RuntimeException("year=" + i + ", region=" + region, t);
                }
            }
        }
    }

}
