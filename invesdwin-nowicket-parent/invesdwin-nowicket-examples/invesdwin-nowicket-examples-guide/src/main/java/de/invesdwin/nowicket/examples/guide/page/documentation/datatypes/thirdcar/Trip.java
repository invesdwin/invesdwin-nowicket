package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class Trip extends AValueObject {

    private String from;
    private String to;
    private int distanceInKM;

    public Trip(final String from, final String to, final int km) {
        this.from = from;
        this.to = to;
        this.distanceInKM = km;
    }

    public String getTo() {
        return to;
    }

    public int getDistanceInKM() {
        return distanceInKM;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public void setDistanceInKM(final int distanceInKM) {
        this.distanceInKM = distanceInKM;
    }

    public void setFrom(final String from) {
        this.from = from;
    }
}
