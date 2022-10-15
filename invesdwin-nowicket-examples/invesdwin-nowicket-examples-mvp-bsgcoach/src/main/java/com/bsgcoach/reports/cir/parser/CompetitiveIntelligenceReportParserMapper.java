package com.bsgcoach.reports.cir.parser;

import java.math.BigDecimal;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import com.bsgcoach.reports.CsvItemReaderBuilder;
import com.bsgcoach.reports.cir.CompetitiveIntelligenceReport;
import com.bsgcoach.reports.cir.CompetitiveIntelligenceReports;
import com.bsgcoach.web.request.CompanyLetter;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class CompetitiveIntelligenceReportParserMapper implements FieldSetMapper<CompetitiveIntelligenceReports> {

    public static final CompetitiveIntelligenceReports INVALID_ROW = new CompetitiveIntelligenceReports();

    private static final String YEAR = "Year";
    private static final String CATEGORY = "Category";
    private static final String TITLE = "Title";
    private static final String SPACE = "Space";
    private static final String NORTH_AMERICA_A = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.A.name();
    private static final String NORTH_AMERICA_B = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.B.name();
    private static final String NORTH_AMERICA_C = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.C.name();
    private static final String NORTH_AMERICA_D = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.D.name();
    private static final String NORTH_AMERICA_E = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.E.name();
    private static final String NORTH_AMERICA_F = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.F.name();
    private static final String NORTH_AMERICA_G = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.G.name();
    private static final String NORTH_AMERICA_H = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.H.name();
    private static final String NORTH_AMERICA_I = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.I.name();
    private static final String NORTH_AMERICA_J = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.J.name();
    private static final String NORTH_AMERICA_K = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.K.name();
    private static final String NORTH_AMERICA_L = CompanyRegion.NorthAmerica.name() + "_" + CompanyLetter.L.name();
    private static final String NORTH_AMERICA_AVG = CompanyRegion.NorthAmerica.name() + "_AVG";
    private static final String EUROPE_AFRICA_A = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.A.name();
    private static final String EUROPE_AFRICA_B = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.B.name();
    private static final String EUROPE_AFRICA_C = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.C.name();
    private static final String EUROPE_AFRICA_D = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.D.name();
    private static final String EUROPE_AFRICA_E = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.E.name();
    private static final String EUROPE_AFRICA_F = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.F.name();
    private static final String EUROPE_AFRICA_G = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.G.name();
    private static final String EUROPE_AFRICA_H = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.H.name();
    private static final String EUROPE_AFRICA_I = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.I.name();
    private static final String EUROPE_AFRICA_J = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.J.name();
    private static final String EUROPE_AFRICA_K = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.K.name();
    private static final String EUROPE_AFRICA_L = CompanyRegion.EuropeAfrica.name() + "_" + CompanyLetter.L.name();
    private static final String EUROPE_AFRICA_AVG = CompanyRegion.EuropeAfrica.name() + "_AVG";
    private static final String ASIA_PACIFIC_A = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.A.name();
    private static final String ASIA_PACIFIC_B = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.B.name();
    private static final String ASIA_PACIFIC_C = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.C.name();
    private static final String ASIA_PACIFIC_D = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.D.name();
    private static final String ASIA_PACIFIC_E = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.E.name();
    private static final String ASIA_PACIFIC_F = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.F.name();
    private static final String ASIA_PACIFIC_G = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.G.name();
    private static final String ASIA_PACIFIC_H = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.H.name();
    private static final String ASIA_PACIFIC_I = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.I.name();
    private static final String ASIA_PACIFIC_J = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.J.name();
    private static final String ASIA_PACIFIC_K = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.K.name();
    private static final String ASIA_PACIFIC_L = CompanyRegion.AsiaPacific.name() + "_" + CompanyLetter.L.name();
    private static final String ASIA_PACIFIC_AVG = CompanyRegion.AsiaPacific.name() + "_AVG";
    private static final String LATIN_AMERICA_A = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.A.name();
    private static final String LATIN_AMERICA_B = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.B.name();
    private static final String LATIN_AMERICA_C = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.C.name();
    private static final String LATIN_AMERICA_D = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.D.name();
    private static final String LATIN_AMERICA_E = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.E.name();
    private static final String LATIN_AMERICA_F = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.F.name();
    private static final String LATIN_AMERICA_G = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.G.name();
    private static final String LATIN_AMERICA_H = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.H.name();
    private static final String LATIN_AMERICA_I = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.I.name();
    private static final String LATIN_AMERICA_J = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.J.name();
    private static final String LATIN_AMERICA_K = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.K.name();
    private static final String LATIN_AMERICA_L = CompanyRegion.LatinAmerica.name() + "_" + CompanyLetter.L.name();
    private static final String LATIN_AMERICA_AVG = CompanyRegion.LatinAmerica.name() + "_AVG";
    private static final String[] FORMAT = { YEAR, CATEGORY, TITLE, SPACE, //
            NORTH_AMERICA_A, NORTH_AMERICA_B, NORTH_AMERICA_C, NORTH_AMERICA_D, NORTH_AMERICA_E, NORTH_AMERICA_F,
            NORTH_AMERICA_G, NORTH_AMERICA_H, NORTH_AMERICA_I, NORTH_AMERICA_J, NORTH_AMERICA_K, NORTH_AMERICA_L,
            NORTH_AMERICA_AVG, SPACE, //
            EUROPE_AFRICA_A, EUROPE_AFRICA_B, EUROPE_AFRICA_C, EUROPE_AFRICA_D, EUROPE_AFRICA_E, EUROPE_AFRICA_F,
            EUROPE_AFRICA_G, EUROPE_AFRICA_H, EUROPE_AFRICA_I, EUROPE_AFRICA_J, EUROPE_AFRICA_K, EUROPE_AFRICA_L,
            EUROPE_AFRICA_AVG, SPACE, //
            ASIA_PACIFIC_A, ASIA_PACIFIC_B, ASIA_PACIFIC_C, ASIA_PACIFIC_D, ASIA_PACIFIC_E, ASIA_PACIFIC_F,
            ASIA_PACIFIC_G, ASIA_PACIFIC_H, ASIA_PACIFIC_I, ASIA_PACIFIC_J, ASIA_PACIFIC_K, ASIA_PACIFIC_L,
            ASIA_PACIFIC_AVG, SPACE, //
            LATIN_AMERICA_A, LATIN_AMERICA_B, LATIN_AMERICA_C, LATIN_AMERICA_D, LATIN_AMERICA_E, LATIN_AMERICA_F,
            LATIN_AMERICA_G, LATIN_AMERICA_H, LATIN_AMERICA_I, LATIN_AMERICA_J, LATIN_AMERICA_K, LATIN_AMERICA_L,
            LATIN_AMERICA_AVG };
    private final CompetitiveIntelligenceReports reports;

    private int curYear;
    private CompetitiveIntelligenceReportParserCategory curCategory;

    public CompetitiveIntelligenceReportParserMapper(final CompetitiveIntelligenceReports reports) {
        this.reports = reports;
    }

    @Override
    public CompetitiveIntelligenceReports mapFieldSet(final FieldSet fieldSet) throws BindException {
        final String year = fieldSet.readString(YEAR);
        if (Strings.isNotBlank(year)) {
            final String[] yearSplit = year.split(" ");
            Assertions.assertThat(yearSplit).hasSize(2);
            curYear = Integer.parseInt(yearSplit[1]);
        }
        final String title = fieldSet.readString(TITLE);
        if (Strings.isBlank(title)) {
            return INVALID_ROW;
        }
        final String categoryStr = fieldSet.readString(CATEGORY);
        if (Strings.isNotBlank(categoryStr)) {
            curCategory = CompetitiveIntelligenceReportParserCategory.valueOfTitle(categoryStr);
        }
        parseCompanyLetters(fieldSet, title);
        parseAvg(fieldSet, title);
        return reports;
    }

    private void parseCompanyLetters(final FieldSet fieldSet, final String title) {
        for (final CompanyRegion region : CompanyRegion.values()) {
            for (final CompanyLetter l : CompanyLetter.values()) {
                final BigDecimal value = fieldSet.readBigDecimal(region.name() + "_" + l.name());
                if (value != null) {
                    final CompetitiveIntelligenceReport r = reports.getForCompany(curYear, region, l);
                    r.setHasValues();
                    final CompetitiveIntelligenceReportParserRow row = CompetitiveIntelligenceReportParserRow
                            .valueOfTitle(curCategory, title);
                    row.parse(r, Decimal.valueOf(value));
                }
            }
        }
    }

    private void parseAvg(final FieldSet fieldSet, final String title) {
        for (final CompanyRegion region : CompanyRegion.values()) {
            final BigDecimal value = fieldSet.readBigDecimal(region.name() + "_AVG");
            if (value != null) {
                final CompetitiveIntelligenceReport r = reports.getAvg(curYear, region);
                r.setHasValues();
                final CompetitiveIntelligenceReportParserRow row = CompetitiveIntelligenceReportParserRow
                        .valueOfTitle(curCategory, title);
                row.parse(r, Decimal.valueOf(value));
            }
        }
    }

    public static FlatFileItemReader<CompetitiveIntelligenceReports> newItemReader(final Resource resource,
            final CompetitiveIntelligenceReports reports) {
        return new CsvItemReaderBuilder<CompetitiveIntelligenceReports>().setResource(resource)
                .setNames(FORMAT)
                .setFieldSetMapper(new CompetitiveIntelligenceReportParserMapper(reports))
                .setRecordSeparatorPolicy(new SimpleRecordSeparatorPolicy())
                .setStrict(false)
                .get();
    }

}
