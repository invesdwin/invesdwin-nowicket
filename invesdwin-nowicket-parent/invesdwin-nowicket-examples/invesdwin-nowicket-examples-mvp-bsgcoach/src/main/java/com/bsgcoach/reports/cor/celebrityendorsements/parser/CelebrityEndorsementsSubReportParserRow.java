package com.bsgcoach.reports.cor.celebrityendorsements.parser;

import javax.annotation.concurrent.Immutable;

import com.bsgcoach.reports.cor.celebrityendorsements.CelebrityEndorsements;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.error.UnknownArgumentException;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.decimal.Decimal;

@Immutable
public enum CelebrityEndorsementsSubReportParserRow {
    //    Current Contract-----------     Celeb 1
    CurrentContractCeleb1("Current Contract-----------", "Celeb 1") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb1(value);
        }
    },
    //    ($000s)       Celeb 2
    CurrentContractCeleb2("($000s)", "Celeb 2") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb2(value);
        }
    },
    //          Celeb 3
    CurrentContractCeleb3("($000s)", "Celeb 3") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb3(value);
        }
    },
    //          Celeb 4
    CurrentContractCeleb4("($000s)", "Celeb 4") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb4(value);
        }
    },
    //          Celeb 5
    CurrentContractCeleb5("($000s)", "Celeb 5") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb5(value);
        }
    },
    //          Celeb 6
    CurrentContractCeleb6("($000s)", "Celeb 6") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb6(value);
        }
    },
    //          Celeb 7
    CurrentContractCeleb7("($000s)", "Celeb 7") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb7(value);
        }
    },
    //          Celeb 8
    CurrentContractCeleb8("($000s)", "Celeb 8") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb8(value);
        }
    },
    //          Celeb 9
    CurrentContractCeleb9("($000s)", "Celeb 9") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb9(value);
        }
    },
    //          Celeb 10
    CurrentContractCeleb10("($000s)", "Celeb 10") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb10(value);
        }
    },
    //          Celeb 11
    CurrentContractCeleb11("($000s)", "Celeb 11") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb11(value);
        }
    },
    //          Celeb 12
    CurrentContractCeleb12("($000s)", "Celeb 12") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb12(value);
        }
    },
    //            Total
    CurrentContractTotal("($000s)", "Total") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setTotal(value);
        }
    },

    //  N.A. Appeal----------------     Celeb 1
    NAAppealCeleb1("N.A. Appeal----------------", "Celeb 1") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getCurrentContractDollar000s().setCeleb1(value);
        }
    },
    //          Celeb 2
    NAAppealCeleb2("N.A. Appeal----------------", "Celeb 2") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb2(value);
        }
    },
    //          Celeb 3
    NAAppealCeleb3("N.A. Appeal----------------", "Celeb 3") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb3(value);
        }
    },
    //          Celeb 4
    NAAppealCeleb4("N.A. Appeal----------------", "Celeb 4") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb4(value);
        }
    },
    //          Celeb 5
    NAAppealCeleb5("N.A. Appeal----------------", "Celeb 5") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb5(value);
        }
    },
    //          Celeb 6
    NAAppealCeleb6("N.A. Appeal----------------", "Celeb 6") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb6(value);
        }
    },
    //          Celeb 7
    NAAppealCeleb7("N.A. Appeal----------------", "Celeb 7") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb7(value);
        }
    },
    //          Celeb 8
    NAAppealCeleb8("N.A. Appeal----------------", "Celeb 8") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb8(value);
        }
    },
    //          Celeb 9
    NAAppealCeleb9("N.A. Appeal----------------", "Celeb 9") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb9(value);
        }
    },
    //          Celeb 10
    NAAppealCeleb10("N.A. Appeal----------------", "Celeb 10") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb10(value);
        }
    },
    //          Celeb 11
    NAAppealCeleb11("N.A. Appeal----------------", "Celeb 11") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb11(value);
        }
    },
    //          Celeb 12
    NAAppealCeleb12("N.A. Appeal----------------", "Celeb 12") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setCeleb12(value);
        }
    },
    //            Total
    NAAppealTotal("N.A. Appeal----------------", "Total") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.NorthAmerica).setTotal(value);
        }
    },

    //  E-A Appeal----------------      Celeb 1
    EAAppealCeleb1("E-A Appeal----------------", "Celeb 1") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb1(value);
        }
    },
    //          Celeb 2
    EAAppealCeleb2("E-A Appeal----------------", "Celeb 2") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb2(value);
        }
    },
    //          Celeb 3
    EAAppealCeleb3("E-A Appeal----------------", "Celeb 3") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb3(value);
        }
    },
    //          Celeb 4
    EAAppealCeleb4("E-A Appeal----------------", "Celeb 4") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb4(value);
        }
    },
    //          Celeb 5
    EAAppealCeleb5("E-A Appeal----------------", "Celeb 5") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb5(value);
        }
    },
    //          Celeb 6
    EAAppealCeleb6("E-A Appeal----------------", "Celeb 6") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb6(value);
        }
    },
    //          Celeb 7
    EAAppealCeleb7("E-A Appeal----------------", "Celeb 7") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb7(value);
        }
    },
    //          Celeb 8
    EAAppealCeleb8("E-A Appeal----------------", "Celeb 8") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb8(value);
        }
    },
    //          Celeb 9
    EAAppealCeleb9("E-A Appeal----------------", "Celeb 9") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb9(value);
        }
    },
    //          Celeb 10
    EAAppealCeleb10("E-A Appeal----------------", "Celeb 10") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb10(value);
        }
    },
    //          Celeb 11
    EAAppealCeleb11("E-A Appeal----------------", "Celeb 11") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb11(value);
        }
    },
    //          Celeb 12
    EAAppealCeleb12("E-A Appeal----------------", "Celeb 12") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setCeleb12(value);
        }
    },
    //            Total
    EAAppealTotal("E-A Appeal----------------", "Total") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.EuropeAfrica).setTotal(value);
        }
    },

    //  A-P Appeal----------------      Celeb 1
    APAppealCeleb1("A-P Appeal----------------", "Celeb 1") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb1(value);
        }
    },
    //          Celeb 2
    APAppealCeleb2("A-P Appeal----------------", "Celeb 2") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb2(value);
        }
    },
    //          Celeb 3
    APAppealCeleb3("A-P Appeal----------------", "Celeb 3") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb3(value);
        }
    },
    //          Celeb 4
    APAppealCeleb4("A-P Appeal----------------", "Celeb 4") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb4(value);
        }
    },
    //          Celeb 5
    APAppealCeleb5("A-P Appeal----------------", "Celeb 5") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb5(value);
        }
    },
    //          Celeb 6
    APAppealCeleb6("A-P Appeal----------------", "Celeb 6") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb6(value);
        }
    },
    //          Celeb 7
    APAppealCeleb7("A-P Appeal----------------", "Celeb 7") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb7(value);
        }
    },
    //          Celeb 8
    APAppealCeleb8("A-P Appeal----------------", "Celeb 8") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb8(value);
        }
    },
    //          Celeb 9
    APAppealCeleb9("A-P Appeal----------------", "Celeb 9") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb9(value);
        }
    },
    //          Celeb 10
    APAppealCeleb10("A-P Appeal----------------", "Celeb 10") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb10(value);
        }
    },
    //          Celeb 11
    APAppealCeleb11("A-P Appeal----------------", "Celeb 11") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb11(value);
        }
    },
    //          Celeb 12
    APAppealCeleb12("A-P Appeal----------------", "Celeb 12") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setCeleb12(value);
        }
    },
    //            Total
    APAppealTotal("A-P Appeal----------------", "Total") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.AsiaPacific).setTotal(value);
        }
    },

    //  L.A. Appeal----------------     Celeb 1
    LAAppealCeleb1("L.A. Appeal----------------", "Celeb 1") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb1(value);
        }
    },
    //          Celeb 2
    LAAppealCeleb2("L.A. Appeal----------------", "Celeb 2") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb2(value);
        }
    },
    //          Celeb 3
    LAAppealCeleb3("L.A. Appeal----------------", "Celeb 3") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb3(value);
        }
    },
    //          Celeb 4
    LAAppealCeleb4("L.A. Appeal----------------", "Celeb 4") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb4(value);
        }
    },
    //          Celeb 5
    LAAppealCeleb5("L.A. Appeal----------------", "Celeb 5") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb5(value);
        }
    },
    //          Celeb 6
    LAAppealCeleb6("L.A. Appeal----------------", "Celeb 6") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb6(value);
        }
    },
    //          Celeb 7
    LAAppealCeleb7("L.A. Appeal----------------", "Celeb 7") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb7(value);
        }
    },
    //          Celeb 8
    LAAppealCeleb8("L.A. Appeal----------------", "Celeb 8") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb8(value);
        }
    },
    //          Celeb 9
    LAAppealCeleb9("L.A. Appeal----------------", "Celeb 9") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb9(value);
        }
    },
    //          Celeb 10
    LAAppealCeleb10("L.A. Appeal----------------", "Celeb 10") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb10(value);
        }
    },
    //          Celeb 11
    LAAppealCeleb11("L.A. Appeal----------------", "Celeb 11") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb11(value);
        }
    },
    //          Celeb 12
    LAAppealCeleb12("L.A. Appeal----------------", "Celeb 12") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setCeleb12(value);
        }
    },
    //            Total
    LAAppealTotal("L.A. Appeal----------------", "Total") {
        @Override
        public void parse(final CelebrityEndorsements report, final Decimal value) {
            report.getRegionAppeal(CompanyRegion.LatinAmerica).setTotal(value);
        }
    };

    private String category2;
    private String title2;

    CelebrityEndorsementsSubReportParserRow(final String category2, final String title2) {
        this.category2 = category2;
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return category2 + "_" + title2;
    }

    public static CelebrityEndorsementsSubReportParserRow valueOfTitle(final String category2, final String title2) {
        final String trimmedTitle = Strings.trim(category2) + "_" + Strings.trim(title2);
        for (final CelebrityEndorsementsSubReportParserRow r : CelebrityEndorsementsSubReportParserRow.values()) {
            if (r.toString().equals(trimmedTitle)) {
                return r;
            }
        }
        throw UnknownArgumentException.newInstance(String.class, trimmedTitle);
    }

    public abstract void parse(final CelebrityEndorsements report, final Decimal value);

}
