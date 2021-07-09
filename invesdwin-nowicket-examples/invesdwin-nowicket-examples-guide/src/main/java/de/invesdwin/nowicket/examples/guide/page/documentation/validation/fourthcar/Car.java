package de.invesdwin.nowicket.examples.guide.page.documentation.validation.fourthcar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.invesdwin.norva.beanpath.annotation.Eager;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar.CarBrand;
import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.thirdcar.Trip;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.time.date.FDateBuilder;

@GeneratedMarkup
@NotThreadSafe
@Eager
public class Car extends AValueObject {

    private String licenseNumber = "MO-ON 11111";
    private CarBrand brand = CarBrand.MarsRoveries;
    private long kmMileage = 88574999;
    private BigDecimal purchasePrice = new BigDecimal("100000.11");
    private boolean technicalControl = true;
    private Date registrationDate = FDateBuilder.newDate(2000).dateValue();
    private Collection<Trip> tripBook;
    private Motor motor;
    private String numberOfGears = "54";

    public Car() {
        tripBook = new ArrayList<Trip>();
        tripBook.add(new Trip("Earth", "Moon", 384403));
        tripBook.add(new Trip("Moon", "Earth", 384403));
        motor = new Motor(0);
    }

    @Pattern(regexp = "[A-Z]{1,3}-[A-Z]{1,2} [1-9][0-9]{0,3}")
    public String getLicenseNumber() {
        return licenseNumber;
    }

    @NotNull
    public CarBrand getBrand() {
        return brand;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Digits(fraction = 2, integer = 19)
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    @Min(0)
    @Max(1000000)
    public long getKmMileage() {
        return kmMileage;
    }

    public boolean isTechnicalControl() {
        return technicalControl;
    }

    public boolean getTechnicalControl() {
        return technicalControl;
    }

    @Pattern(regexp = "[45]")
    @Size(max = 1)
    public String getNumberOfGears() {
        return numberOfGears;
    }

    public Motor getMotor() {
        return motor;
    }

    public Collection<Trip> getTripBook() {
        return tripBook;
    }

    public void setLicenseNumber(final String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setBrand(final CarBrand brand) {
        this.brand = brand;
        if (validatePurchasePrice(this.purchasePrice) != null) {
            this.purchasePrice = null;
        }
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

    public void setNumberOfGears(final String numberOfGears) {
        this.numberOfGears = numberOfGears;
    }

    public void setMotor(final Motor motor) {
        this.motor = motor;
    }

    public void setTripBook(final Collection<Trip> tripBook) {
        this.tripBook = tripBook;
    }

    public CarBrand[] getBrandChoice() {
        //filter PlutoNebula because someone said: "Pluto is not a planet!"
        return new CarBrand[] { CarBrand.LunarIndustries, CarBrand.MarsRoveries };
    }

    private boolean isPurchasePriceGreaterThan(final BigDecimal purchasePrice, final long price) {
        return purchasePrice != null && purchasePrice.compareTo(new BigDecimal("" + price)) >= 1;
    }

    public String validatePurchasePrice(final BigDecimal value) {
        if (brand != null && brand == CarBrand.MarsRoveries && isPurchasePriceGreaterThan(value, 20000)) {
            return "is too expensive";
        } else {
            return null;
        }
    }
}
