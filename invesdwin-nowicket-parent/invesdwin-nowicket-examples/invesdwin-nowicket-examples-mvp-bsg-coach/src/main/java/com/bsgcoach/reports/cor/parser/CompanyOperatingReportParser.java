package com.bsgcoach.reports.cor.parser;

import java.util.concurrent.Callable;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.Resource;

import com.bsgcoach.reports.cor.CompanyOperatingReports;

@NotThreadSafe
public class CompanyOperatingReportParser implements Callable<CompanyOperatingReports> {

    private final Resource cirReportCsv;

    public CompanyOperatingReportParser(final Resource cirReportCsv) {
        this.cirReportCsv = cirReportCsv;
    }

    @Override
    public CompanyOperatingReports call() throws Exception {
        final CompanyOperatingReports reports = new CompanyOperatingReports();
        final FlatFileItemReader<CompanyOperatingReports> items = CompanyOperatingReportParserMapper.newItemReader(
                cirReportCsv, reports);
        try {
            items.open(new ExecutionContext());
            CompanyOperatingReports item = null;
            do {
                item = items.read();
            } while (item != null);
        } finally {
            items.close();
        }
        return reports;
    }

}
