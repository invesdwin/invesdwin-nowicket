package com.bsgcoach.reports.cir;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyLetter;
import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.bean.tuple.Pair;
import de.invesdwin.util.bean.tuple.Triple;
import de.invesdwin.util.collections.loadingcache.ALoadingCache;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class CompetitiveIntelligenceReports extends AValueObject {

    private final ALoadingCache<Triple<Integer, CompanyRegion, CompanyLetter>, CompetitiveIntelligenceReport> forCompany = new ALoadingCache<Triple<Integer, CompanyRegion, CompanyLetter>, CompetitiveIntelligenceReport>() {
        @Override
        protected CompetitiveIntelligenceReport loadValue(final Triple<Integer, CompanyRegion, CompanyLetter> key) {
            final int year = key.getFirst();
            final CompanyRegion companyRegion = key.getSecond();
            final CompanyLetter companyLetter = key.getThird();
            return new CompetitiveIntelligenceReport(year, companyRegion, companyLetter);
        }
    };

    private final ALoadingCache<Pair<Integer, CompanyRegion>, CompetitiveIntelligenceReport> avg = new ALoadingCache<Pair<Integer, CompanyRegion>, CompetitiveIntelligenceReport>() {
        @Override
        protected CompetitiveIntelligenceReport loadValue(final Pair<Integer, CompanyRegion> key) {
            final int year = key.getFirst();
            final CompanyRegion companyRegion = key.getSecond();
            return new CompetitiveIntelligenceReport(year, companyRegion, null);
        }
    };

    public CompetitiveIntelligenceReport getForCompany(final int year, final CompanyRegion companyRegion,
            final CompanyLetter companyLetter) {
        return forCompany.get(Triple.of(year, companyRegion, companyLetter));
    }

    public CompetitiveIntelligenceReport getAvg(final int year, final CompanyRegion companyRegion) {
        return avg.get(Pair.of(year, companyRegion));
    }

    public int getFirstYear() {
        final List<Decimal> years = new ArrayList<Decimal>();
        years.add(new Decimal("10"));
        for (final CompetitiveIntelligenceReport e : avg.values()) {
            if (e.isHasValues()) {
                years.add(Decimal.valueOf(e.getYear()));
            }
        }
        return Decimal.valueOf(years).min().intValue();
    }

    public int getLastYear() {
        final List<Decimal> years = new ArrayList<Decimal>();
        years.add(new Decimal("10"));
        for (final CompetitiveIntelligenceReport e : avg.values()) {
            if (e.isHasValues()) {
                years.add(Decimal.valueOf(e.getYear()));
            }
        }
        return Decimal.valueOf(years).max().intValue();
    }

}
