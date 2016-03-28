package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.time.fdate.FDateBuilder;

@Eager
@NotThreadSafe
@GeneratedMarkup
public class Car extends AValueObject {

    private String licenseNumber = "MO-ON 1";
    private CarBrand brand = CarBrand.LunarIndustries;
    private long kmMileage = 885749;
    private BigDecimal purchasePrice = new BigDecimal("100000");
    private boolean technicalControl = true;
    private Date registrationDate = FDateBuilder.newDate(2000).dateValue();
    private Trip[] tripBook;
    private Motor motor;

    public Car() {
        tripBook = new Trip[] { new Trip("Earth", "Moon", 384403), new Trip("Moon", "Earth", 384403) };
        motor = new Motor(120);
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public long getKmMileage() {
        return kmMileage;
    }

    public boolean isTechnicalControl() {
        return technicalControl;
    }

    public Motor getMotor() {
        return motor;
    }

    public Trip[] getTripBook() {
        return tripBook;
    }

    public void setLicenseNumber(final String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setBrand(final CarBrand brand) {
        this.brand = brand;
    }

    public void setRegistrationDate(final Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPurchasePrice(final BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setKmMileage(final long kmMileage) {
        this.kmMileage = kmMileage;
    }

    public void setTechnicalControl(final boolean technicalControl) {
        this.technicalControl = technicalControl;
    }

    public void setMotor(final Motor motor) {
        this.motor = motor;
    }

    public void setTripBook(final Trip[] tripBook) {
        this.tripBook = tripBook;
    }

}
