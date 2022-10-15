package com.bsgcoach.reports.cor.bankloans.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.bankloans.BankLoans;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParserMapper;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;

import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class BankLoansSubReportParser implements ISubReportParser {

    private String curCategory1;
    private String curTitle1;
    private String curTitle2;

    @Override
    public void parse(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        updateCategoryAndTitle(fieldSet);
        parseRow(fieldSet, reports);
    }

    protected void updateCategoryAndTitle(final FieldSet fieldSet) {
        final String category1 = fieldSet.readString(CompanyOperatingReportParserMapper.CATEGORY1);
        if (Strings.isNotBlank(category1)) {
            curCategory1 = category1;
            curTitle1 = null;
            curTitle2 = null;
        }
        final String title1 = fieldSet.readString(CompanyOperatingReportParserMapper.TITLE1);
        if (Strings.isNotBlank(title1)) {
            curTitle1 = title1;
            curTitle2 = null;
        }
        final String title2 = fieldSet.readString(CompanyOperatingReportParserMapper.TITLE2);
        if (Strings.isNotBlank(title2)) {
            curTitle2 = title2;
        }
    }

    protected void parseRow(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        final BankLoansSubReportParserRow row = BankLoansSubReportParserRow.valueOfTitle(curCategory1, curTitle1,
                curTitle2);
        if (row == null) {
            return;
        }
        final BankLoans report = reports.getBankLoans();
        final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
        row.parse(report, value);
    }

}
