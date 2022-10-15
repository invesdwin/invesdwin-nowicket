package com.bsgcoach.reports.cor.adminexpenses.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.adminexpenses.AdminExpenses;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParserMapper;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;

import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class AdminExpensesSubReportParser implements ISubReportParser {

    private String curCategory1;
    private String curCategory2;
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
            curCategory2 = null;
            curTitle2 = null;
        }
        final String category2 = fieldSet.readString(CompanyOperatingReportParserMapper.CATEGORY2);
        if (Strings.isNotBlank(category2)) {
            curCategory2 = category2;
            curTitle2 = null;
        }
        final String title2 = fieldSet.readString(CompanyOperatingReportParserMapper.TITLE2);
        if (Strings.isNotBlank(title2)) {
            curTitle2 = title2;
        }
    }

    protected void parseRow(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        final AdminExpensesSubReportParserRow row = AdminExpensesSubReportParserRow.valueOfTitle(curCategory1,
                curCategory2, curTitle2);
        final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
        final AdminExpenses report = reports.getAdminExpenses();
        row.parse(report, value);
    }

}
