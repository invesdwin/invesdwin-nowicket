package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.info.CarInfo;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.trip.TripInfo;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class CarTabs extends AValueObject {

    private final CarInfo carInfo;
    private final TripInfo tripInfo;

    public CarTabs(final String brand, final String licenseNumber) {
        this.carInfo = new CarInfo(brand, licenseNumber);
        this.tripInfo = new TripInfo();
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public TripInfo getTripInfo() {
        return tripInfo;
    }

}
