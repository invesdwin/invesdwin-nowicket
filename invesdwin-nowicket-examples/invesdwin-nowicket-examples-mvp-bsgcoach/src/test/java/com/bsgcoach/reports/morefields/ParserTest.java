package com.bsgcoach.reports.morefields;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.bsgcoach.reports.cir.parser.CompetitiveIntelligenceReportParser;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParser;
import com.bsgcoach.reports.fir.parser.FootwearIndustryReportParser;

@NotThreadSafe
public class ParserTest {

    @Test
    public void testParseY12() throws Exception {
        final FootwearIndustryReportParser firparser = new FootwearIndustryReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/FIR_Y12.csv"));
        firparser.call();
        final CompetitiveIntelligenceReportParser cirparser = new CompetitiveIntelligenceReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/CIR_Y12.csv"));
        cirparser.call();
        final CompanyOperatingReportParser corparser = new CompanyOperatingReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/COR_Y12.csv"));
        corparser.call();
    }

    @Test
    public void testParseY11() throws Exception {
        final FootwearIndustryReportParser firparser = new FootwearIndustryReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/FIR_Y11.csv"));
        firparser.call();
        final CompetitiveIntelligenceReportParser cirparser = new CompetitiveIntelligenceReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/CIR_Y11.csv"));
        cirparser.call();
        final CompanyOperatingReportParser corparser = new CompanyOperatingReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/COR_Y11.csv"));
        corparser.call();
    }

}
