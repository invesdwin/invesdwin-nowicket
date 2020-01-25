package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.CarDetails;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class CarSearchRow extends AValueObject {

    private final CarDetails details;

    public CarSearchRow(final CarDetails details) {
        this.details = details;
    }

    public CarDetails details() {
        return details;
    }

    public String getLicenseNumber() {
        return details.getTabs().getCarInfo().getLicenseNumber();
    }

    public String getBrand() {
        return details.getTabs().getCarInfo().getBrand();
    }

    public String getState() {
        return details.getTabs().getCarInfo().getState();
    }

    public String getTrips() {
        return details.getTabs().getTripInfo().getTripBook().size() + " ("
                + details.getTabs().getTripInfo().getDistanceInKMSum() + " KM)";
    }

}
