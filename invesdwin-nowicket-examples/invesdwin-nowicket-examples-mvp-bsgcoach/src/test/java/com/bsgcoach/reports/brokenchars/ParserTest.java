package com.bsgcoach.reports.brokenchars;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.bsgcoach.reports.cir.parser.CompetitiveIntelligenceReportParser;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParser;
import com.bsgcoach.reports.fir.parser.FootwearIndustryReportParser;

@NotThreadSafe
public class ParserTest {

    @Test
    public void testParse() throws Exception {
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
