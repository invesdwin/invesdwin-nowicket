package com.bsgcoach.reports.cor.brandedwarehouseproductions.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.brandedwarehouseproductions.BrandedWarehouseProductions;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParserMapper;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class BrandedWarehouseProductionsSubReportParser implements ISubReportParser {

    private CompanyRegion curRegion;
    private String curCategory2;
    private String curTitle1;
    private String curTitle2;

    @Override
    public void parse(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        updateCategoryAndTitle(fieldSet);
        parseRow(fieldSet, reports);
    }

    protected void updateCategoryAndTitle(final FieldSet fieldSet) {
        final String category1 = fieldSet.readString(CompanyOperatingReportParserMapper.CATEGORY1);
        if ("N.A.-----------".equals(category1)) {
            curRegion = CompanyRegion.NorthAmerica;
        } else if ("E-A------------".equals(category1)) {
            curRegion = CompanyRegion.EuropeAfrica;
        } else if ("A-P-------------".equals(category1)) {
            curRegion = CompanyRegion.AsiaPacific;
        } else if ("L.A.-----------".equals(category1)) {
            curRegion = CompanyRegion.LatinAmerica;
        }

        final String category2 = fieldSet.readString(CompanyOperatingReportParserMapper.CATEGORY2);
        if (Strings.isNotBlank(category2)) {
            curCategory2 = category2;
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
        final BrandedWarehouseProductionsSubReportParserRow row = BrandedWarehouseProductionsSubReportParserRow.valueOfTitle(
                curCategory2, curTitle1, curTitle2);
        final BrandedWarehouseProductions report = reports.getBrandedWarehouseProductions(curRegion);
        final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
        row.parse(report, value);
    }

}
