package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.create.NewTrip;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class TripRow extends AValueObject {

    private final NewTrip details;

    public TripRow(final NewTrip details) {
        this.details = details;
    }

    public String getTo() {
        return details.getTo();
    }

    public Integer getDistanceInKM() {
        return details.getDistanceInKM();
    }

    public String getFrom() {
        return details.getFrom();
    }

}
