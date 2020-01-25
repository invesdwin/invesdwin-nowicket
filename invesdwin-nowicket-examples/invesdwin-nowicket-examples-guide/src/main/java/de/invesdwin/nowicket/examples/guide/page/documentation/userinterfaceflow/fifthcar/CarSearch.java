package de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar;

import java.util.ArrayList;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.CarDetails;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class CarSearch extends AValueObject {

    private ArrayList<CarSearchRow> cars;

    public CarSearch() {
        cars = new ArrayList<CarSearchRow>();
        cars.add(new CarSearchRow(new CarDetails(this, "LunarIndustries", "XY-ZA 123")));
        cars.add(new CarSearchRow(new CarDetails(this, "MarsRoveries", "AB-AA 456")));
        cars.add(new CarSearchRow(new CarDetails(this, "PlutoNebula", "CD-BB 789")));
        cars.add(new CarSearchRow(new CarDetails(this, "LunarIndustries", "ZZ-VV 555")));
    }

    public ArrayList<CarSearchRow> getCars() {
        return cars;
    }

    public void setCars(final ArrayList<CarSearchRow> cars) {
        this.cars = cars;
    }

}
