package com.bsgcoach.reports.cir.parser;

import java.util.concurrent.Callable;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.Resource;

import com.bsgcoach.reports.cir.CompetitiveIntelligenceReports;

@NotThreadSafe
public class CompetitiveIntelligenceReportParser implements Callable<CompetitiveIntelligenceReports> {

    private final Resource cirReportCsv;

    public CompetitiveIntelligenceReportParser(final Resource cirReportCsv) {
        this.cirReportCsv = cirReportCsv;
    }

    @Override
    public CompetitiveIntelligenceReports call() throws Exception {
        final CompetitiveIntelligenceReports reports = new CompetitiveIntelligenceReports();
        final FlatFileItemReader<CompetitiveIntelligenceReports> items = CompetitiveIntelligenceReportParserMapper.newItemReader(
                cirReportCsv, reports);
        try {
            items.open(new ExecutionContext());
            CompetitiveIntelligenceReports item = null;
            do {
                item = items.read();
            } while (item != null);
        } finally {
            items.close();
        }
        return reports;
    }

}
