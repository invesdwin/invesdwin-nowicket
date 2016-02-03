package com.bsgcoach.reports.cor.plannedshippingpercentages;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.bean.tuple.Pair;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class PlannedShippingPercentages extends AValueObject {

    private final Map<Pair<CompanyRegion, CompanyRegion>, Percent> fromTo_percent = new HashMap<Pair<CompanyRegion, CompanyRegion>, Percent>();

    public Percent getPlannedShippingPercent(final CompanyRegion from, final CompanyRegion to) {
        return fromTo_percent.get(Pair.of(from, to));
    }

    public void setPlannedShippingPercent(final CompanyRegion from, final CompanyRegion to,
            final Percent plannedShippingPercent) {
        Assertions.assertThat(fromTo_percent.put(Pair.of(from, to), plannedShippingPercent)).isNull();
    }

}
