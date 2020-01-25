package com.bsgcoach.reports.cor.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.bsgcoach.reports.cor.CompanyOperatingReports;

import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class CompanyOperatingReportParserTest {

    @Test
    public void testParse() throws Exception {
        final CompanyOperatingReportParser parser = new CompanyOperatingReportParser(
                new ClassPathResource("/" + getClass().getPackage().getName().replace(".", "/") + "/COR_Y19.csv"));
        final CompanyOperatingReports reports = parser.call();
        Assertions.assertThat(reports.getAdminExpenses().getGeneralAdministrativeExpenses()).isNotNull();
    }

}
