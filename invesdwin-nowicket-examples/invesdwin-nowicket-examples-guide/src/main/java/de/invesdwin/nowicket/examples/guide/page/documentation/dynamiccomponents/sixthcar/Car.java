package de.invesdwin.nowicket.examples.guide.page.documentation.dynamiccomponents.sixthcar;

import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.norva.beanpath.annotation.Format;
import de.invesdwin.norva.beanpath.annotation.Tooltip;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar.CarBrand;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar.MotorState;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.string.Strings;
import de.invesdwin.util.time.date.FDate;
import jakarta.validation.constraints.Pattern;

@NotThreadSafe
@GeneratedMarkup
public class Car extends AValueObject {

    private MotorState state = MotorState.off;
    private String licenseNumber = "MO-ON 1111";
    private String brand = "LunarIndustries";
    private Date registrationDate = new FDate().dateValue();

    @Pattern(regexp = "[A-Z]{1,3}-[A-Z]{1,2} [1-9][0-9]{0,3}")
    @Tooltip("Pattern Example: MO-ON 1234")
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getBrandTooltip() {
        String message = "Popular Brands: ";
        for (final CarBrand brand : CarBrand.values()) {
            message += brand + ", ";
        }
        message = Strings.removeEnd(message, ", ");
        return message;
    }

    public MotorState getState() {
        return state;
    }

    public void setLicenseNumber(final String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    @Format("yyyy -_- MM /_/ dd")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(final Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void turnOn() {
        this.state = MotorState.on;
    }

    public String disableTurnOn() {
        if (state == MotorState.on) {
            return "Car is already running";
        } else {
            return null;
        }
    }

    public void turnOff() {
        this.state = MotorState.off;
    }

    public String disableTurnOff() {
        if (state == MotorState.off) {
            return "Car is already stopped";
        } else {
            return null;
        }
    }

}