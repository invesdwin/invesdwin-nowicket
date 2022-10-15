package com.bsgcoach.reports.cor.privatelabeloffersandbids.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParserMapper;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;

import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class PrivateLabelSqRatingOfPairsOfferedSubReportParser implements ISubReportParser {

    private String curTitle2;

    @Override
    public void parse(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        updateCategoryAndTitle(fieldSet);
        parseRow(fieldSet, reports);
    }

    protected void updateCategoryAndTitle(final FieldSet fieldSet) {
        final String title2 = fieldSet.readString(CompanyOperatingReportParserMapper.TITLE2);
        if (Strings.isNotBlank(title2)) {
            curTitle2 = title2;
        }
    }

    protected void parseRow(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        final PrivateLabelBidPricesSubReportParserRow row = PrivateLabelBidPricesSubReportParserRow.valueOfTitle(curTitle2);
        final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
        row.parse(reports, value);
    }

}
