package com.bsgcoach.reports.fir.parser;

import java.math.BigDecimal;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import com.bsgcoach.reports.CsvItemReaderBuilder;
import com.bsgcoach.reports.fir.FootwearIndustryReport;
import com.bsgcoach.reports.fir.FootwearIndustryReports;
import com.bsgcoach.web.request.CompanyLetter;

import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public final class FootwearIndustryReportParserMapper implements FieldSetMapper<FootwearIndustryReports> {

    public static final FootwearIndustryReports INVALID_ROW = new FootwearIndustryReports();

    private static final String CATEGORY = "Category";
    private static final String SPACE = "Space";
    private static final String TITLE1 = "Title1";
    private static final String TITLE2 = "Title2";
    private static final String A = CompanyLetter.A.name();
    private static final String B = CompanyLetter.B.name();
    private static final String C = CompanyLetter.C.name();
    private static final String D = CompanyLetter.D.name();
    private static final String E = CompanyLetter.E.name();
    private static final String F = CompanyLetter.F.name();
    private static final String G = CompanyLetter.G.name();
    private static final String H = CompanyLetter.H.name();
    private static final String I = CompanyLetter.I.name();
    private static final String J = CompanyLetter.J.name();
    private static final String K = CompanyLetter.K.name();
    private static final String L = CompanyLetter.L.name();
    private static final String AVG = "AVG";
    private static final String[] FORMAT = { CATEGORY, SPACE, TITLE1, TITLE2, A, B, C, D, E, F, G, H, I, J, K, L, AVG };
    private final FootwearIndustryReports reports;

    public FootwearIndustryReportParserMapper(final FootwearIndustryReports reports) {
        this.reports = reports;
    }

    @Override
    public FootwearIndustryReports mapFieldSet(final FieldSet fieldSet) throws BindException {
        String title = fieldSet.readString(TITLE1);
        if (Strings.isBlank(title)) {
            title = fieldSet.readString(TITLE2);
        }
        if (Strings.isBlank(title)) {
            return INVALID_ROW;
        }
        parseCompanyLetters(fieldSet, title);
        parseAvg(fieldSet, title);
        return reports;
    }

    private void parseCompanyLetters(final FieldSet fieldSet, final String title) {
        for (final CompanyLetter l : CompanyLetter.values()) {
            final BigDecimal value = fieldSet.readBigDecimal(l.name());
            if (value != null) {
                final FootwearIndustryReport r = reports.getForCompany(l);
                r.setHasValues();
                final FootwearIndustryReportParserRow row = FootwearIndustryReportParserRow.valueOfTitle(title);
                row.parse(r, Decimal.valueOf(value));
            }
        }
    }

    private void parseAvg(final FieldSet fieldSet, final String title) {
        final BigDecimal value = fieldSet.readBigDecimal(AVG);
        if (value != null) {
            final FootwearIndustryReport r = reports.getAvg();
            r.setHasValues();
            final FootwearIndustryReportParserRow row = FootwearIndustryReportParserRow.valueOfTitle(title);
            row.parse(r, Decimal.valueOf(value));
        }
    }

    public static FlatFileItemReader<FootwearIndustryReports> newItemReader(final Resource resource,
            final FootwearIndustryReports reports) {
        return new CsvItemReaderBuilder<FootwearIndustryReports>().setResource(resource)
                .setNames(FORMAT)
                .setFieldSetMapper(new FootwearIndustryReportParserMapper(reports))
                .setRecordSeparatorPolicy(new SimpleRecordSeparatorPolicy())
                .setStrict(false)
                .get();
    }

}
