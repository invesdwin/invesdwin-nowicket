package com.bsgcoach.reports.cor.plannedshippingpercentages.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.plannedshippingpercentages.PlannedShippingPercentages;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;
import de.invesdwin.util.math.decimal.scaled.PercentScale;

@Immutable
public enum PlannedShippingPercentagesSubReportParserRow {

    NaToNa("N.A. Plant to-------------", "N.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.NorthAmerica, CompanyRegion.NorthAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    NaToEa("N.A. Plant to-------------", "E-A") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.NorthAmerica, CompanyRegion.EuropeAfrica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    NaToAp("N.A. Plant to-------------", "A-P") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.NorthAmerica, CompanyRegion.AsiaPacific,
                    new Percent(value, PercentScale.RATE));
        }
    },
    NaToLa("N.A. Plant to-------------", "L.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.NorthAmerica, CompanyRegion.LatinAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    EaToNa("E-A Plant to-------------", "N.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.EuropeAfrica, CompanyRegion.NorthAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    EaToEa("E-A Plant to-------------", "E-A") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.EuropeAfrica, CompanyRegion.EuropeAfrica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    EaToAp("E-A Plant to-------------", "A-P") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.EuropeAfrica, CompanyRegion.AsiaPacific,
                    new Percent(value, PercentScale.RATE));
        }
    },
    EaToLa("E-A Plant to-------------", "L.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.EuropeAfrica, CompanyRegion.LatinAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    ApToNa("A-P Plant to-------------", "N.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.AsiaPacific, CompanyRegion.NorthAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    ApToEa("A-P Plant to-------------", "E-A") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.AsiaPacific, CompanyRegion.EuropeAfrica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    ApToAp("A-P Plant to-------------", "A-P") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.AsiaPacific, CompanyRegion.AsiaPacific,
                    new Percent(value, PercentScale.RATE));
        }
    },
    ApToLa("A-P Plant to-------------", "L.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.AsiaPacific, CompanyRegion.LatinAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    LaToNa("L.A. Plant-----------------", "N.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.LatinAmerica, CompanyRegion.NorthAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    LaToEa("L.A. Plant-----------------", "E-A") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.LatinAmerica, CompanyRegion.EuropeAfrica,
                    new Percent(value, PercentScale.RATE));
        }
    },
    LaToAp("L.A. Plant-----------------", "A-P") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.LatinAmerica, CompanyRegion.AsiaPacific,
                    new Percent(value, PercentScale.RATE));
        }
    },
    LaToLa("L.A. Plant-----------------", "L.A.") {
        @Override
        public void parse(final PlannedShippingPercentages report, final Decimal value) {
            report.setPlannedShippingPercent(CompanyRegion.LatinAmerica, CompanyRegion.LatinAmerica,
                    new Percent(value, PercentScale.RATE));
        }
    };

    private String category2;
    private String title2;

    PlannedShippingPercentagesSubReportParserRow(final String category2, final String title2) {
        this.category2 = category2;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category2 + "_" + title2;
    }

    public static PlannedShippingPercentagesSubReportParserRow valueOfTitle(final String category2,
            final String title2) {
        final String trimmedTitle = category2.trim() + "_" + Strings.trim(title2);
        for (final PlannedShippingPercentagesSubReportParserRow r : PlannedShippingPercentagesSubReportParserRow
                .values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(PlannedShippingPercentages report, Decimal value);

}
