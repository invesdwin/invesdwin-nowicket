package com.bsgcoach.reports.cor.plantoperations.parser;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.batch.item.file.transform.FieldSet;

import com.bsgcoach.reports.cor.CompanyOperatingReports;
import com.bsgcoach.reports.cor.parser.CompanyOperatingReportParserMapper;
import com.bsgcoach.reports.cor.parser.subreport.ISubReportParser;
import com.bsgcoach.reports.cor.plantoperations.PlantOperations;
import com.bsgcoach.reports.cor.plantoperations.Production;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class PlantOperationsSubReportParser implements ISubReportParser {

    private CompanyRegion curRegion = CompanyRegion.NorthAmerica;
    private boolean brandedProduction;
    private boolean pLabelProduction;
    private String curCategory2;
    private String curTitle1;
    private String curTitle2;

    @Override
    public void parse(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        updateCategoriesAndTitles(fieldSet);

        if (pLabelProduction) {
            final Production report = reports.getPlantOperations(curRegion).getPLabelProduction();
            parseProduction(fieldSet, report);
        } else if (brandedProduction) {
            final Production report = reports.getPlantOperations(curRegion).getBrandedProduction();
            parseProduction(fieldSet, report);
        } else {
            parsePlantOperations(fieldSet, reports);
        }
    }

    protected void updateCategoriesAndTitles(final FieldSet fieldSet) {
        final String subReport = fieldSet.readString(CompanyOperatingReportParserMapper.SUB_REPORT);
        if ("E-A".equals(subReport)) {
            curRegion = CompanyRegion.EuropeAfrica;
            curCategory2 = null;
            curTitle1 = null;
            curTitle2 = null;
            brandedProduction = false;
            pLabelProduction = false;
        } else if ("A-P".equals(subReport)) {
            curRegion = CompanyRegion.AsiaPacific;
            curCategory2 = null;
            curTitle1 = null;
            curTitle2 = null;
            brandedProduction = false;
            pLabelProduction = false;
        } else if ("L.A.".equals(subReport)) {
            curRegion = CompanyRegion.LatinAmerica;
            curCategory2 = null;
            curTitle1 = null;
            curTitle2 = null;
            brandedProduction = false;
            pLabelProduction = false;
        }

        final String category1 = fieldSet.readString(CompanyOperatingReportParserMapper.CATEGORY1);
        if ("Branded".equals(category1)) {
            brandedProduction = true;
            curCategory2 = null;
            curTitle1 = null;
            curTitle2 = null;
        } else if ("P-Label".equals(category1)) {
            pLabelProduction = true;
            curCategory2 = null;
            curTitle1 = null;
            curTitle2 = null;
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

    protected void parsePlantOperations(final FieldSet fieldSet, final CompanyOperatingReports reports) {
        if (curTitle1 != null || curTitle2 != null) {
            final PlantOperationsSubReportParserRow row = PlantOperationsSubReportParserRow.valueOfTitle(curTitle1,
                    curTitle2);
            if (row == null) {
                return;
            }
            final PlantOperations report = reports.getPlantOperations(curRegion);
            final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
            row.parse(report, value);
        }
    }

    protected void parseProduction(final FieldSet fieldSet, final Production report) {
        if (curCategory2 != null || curTitle1 != null || curTitle2 != null) {
            final PlantOperationsProductionSubReportParserRow row = PlantOperationsProductionSubReportParserRow.valueOfTitle(
                    curCategory2, curTitle1, curTitle2);
            if (row == null) {
                return;
            }
            final Decimal value = Decimal.valueOf(fieldSet.readBigDecimal(CompanyOperatingReportParserMapper.VALUE));
            row.parse(report, value);
        }
    }
}
