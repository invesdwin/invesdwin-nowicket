package com.bsgcoach.reports.cor.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import com.bsgcoach.reports.CsvItemReaderBuilder;
import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.parser.subreport.CompanyOperatingReportParserSubReport;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;

import de.invesdwin.util.lang.Strings;

@NotThreadSafe
public class CompanyOperatingReportParserMapper implements FieldSetMapper<CompanyOperatingReports> {

    public static final CompanyOperatingReports INVALID_ROW = new CompanyOperatingReports();

    public static final String SUB_REPORT = "SubReport";
    public static final String CATEGORY1 = "Category1";
    public static final String CATEGORY2 = "Category2";
    public static final String TITLE1 = "Title1";
    public static final String TITLE2 = "Title2";
    public static final String VALUE = "Value";
    private static final String[] FORMAT = { SUB_REPORT, CATEGORY1, CATEGORY2, TITLE1, TITLE2, VALUE };
    private final CompanyOperatingReports reports;

    private CompanyOperatingReportParserSubReport curSubReport;
    private ISubReportParser curSubReportParser;

    public CompanyOperatingReportParserMapper(final CompanyOperatingReports reports) {
        this.reports = reports;
    }

    @Override
    public CompanyOperatingReports mapFieldSet(final FieldSet fieldSet) throws BindException {
        final String subReport = fieldSet.readString(SUB_REPORT);
        if (Strings.isNotBlank(subReport)) {
            final CompanyOperatingReportParserSubReport newSubReport = CompanyOperatingReportParserSubReport
                    .valueOfTitle(curSubReport, subReport);
            if (newSubReport != curSubReport) {
                curSubReport = newSubReport;
                curSubReportParser = curSubReport.newSubReportParser();
            }
        }

        if (Strings.isBlank(fieldSet.readString(SUB_REPORT)) && Strings.isBlank(fieldSet.readString(CATEGORY1))
                && Strings.isBlank(fieldSet.readString(CATEGORY2)) && Strings.isBlank(fieldSet.readString(TITLE1))
                && Strings.isBlank(fieldSet.readString(TITLE2)) || Strings.isBlank(fieldSet.readString(VALUE))) {
            return INVALID_ROW;
        }

        if (curSubReportParser != null) {
            curSubReportParser.parse(fieldSet, reports);
            return reports;
        } else {
            return INVALID_ROW;
        }
    }

    public static FlatFileItemReader<CompanyOperatingReports> newItemReader(final Resource resource,
            final CompanyOperatingReports reports) {
        return new CsvItemReaderBuilder<CompanyOperatingReports>().setResource(resource)
                .setNames(FORMAT)
                .setFieldSetMapper(new CompanyOperatingReportParserMapper(reports))
                .setRecordSeparatorPolicy(new SimpleRecordSeparatorPolicy())
                .setStrict(false)
                .get();
    }

}
