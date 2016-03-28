package de.invesdwin.nowicket.examples.guide.page.documentation.tutorialstart.secondcar;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class Car extends AValueObject {

    private String state;
    private String licenseNumber;
    private String brand;

    public Car() {
        this.state = "off";
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getState() {
        return state;
    }

    public void setLicenseNumber(final String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public void turnOn() {
        this.state = "on";
    }

    public void turnOff() {
        this.state = "off";
    }

}