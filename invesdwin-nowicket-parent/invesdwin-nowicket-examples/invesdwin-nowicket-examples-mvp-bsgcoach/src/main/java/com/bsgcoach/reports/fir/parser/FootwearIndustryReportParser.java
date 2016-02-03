package com.bsgcoach.reports.fir.parser;

import java.util.concurrent.Callable;

import javax.annotation.concurrent.ThreadSafe;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.io.Resource;

import com.bsgcoach.reports.fir.FootwearIndustryReports;

@ThreadSafe
@Configurable
public class FootwearIndustryReportParser implements Callable<FootwearIndustryReports> {

    private final Resource firReportCsv;

    public FootwearIndustryReportParser(final Resource firReportCsv) {
        this.firReportCsv = firReportCsv;
    }

    @Override
    public FootwearIndustryReports call() throws Exception {
        final FootwearIndustryReports reports = new FootwearIndustryReports();
        final FlatFileItemReader<FootwearIndustryReports> items = FootwearIndustryReportParserMapper.newItemReader(
                firReportCsv, reports);
        try {
            items.open(new ExecutionContext());
            FootwearIndustryReports item = null;
            do {
                item = items.read();
            } while (item != null);
        } finally {
            items.close();
        }
        return reports;
    }

}
