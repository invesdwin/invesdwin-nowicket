package com.bsgcoach.reports.cor.stockprice.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParserMapper;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;
import com.bsgcoach.reports.cor.stockprice.StockPrice;

import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class StockPriceSubReportParser implements ISubReportParser {

    private String curCategory2;

    @Override
    public void parse(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        updateCategoryAndTitle(fieldSet);
        parseRow(fieldSet, reports);
    }

    protected void updateCategoryAndTitle(final FieldSet fieldSet) {
        final String category2 = fieldSet.readString(CompanyOperatingReportParserMapper.CATEGORY2);
        if (Strings.isNotBlank(category2)) {
            curCategory2 = category2;
        }
    }

    protected void parseRow(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        final StockPriceSubReportParserRow row = StockPriceSubReportParserRow.valueOfTitle(curCategory2);
        final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
        final StockPrice report = reports.getStockPrice();
        row.parse(report, value);
    }

}
