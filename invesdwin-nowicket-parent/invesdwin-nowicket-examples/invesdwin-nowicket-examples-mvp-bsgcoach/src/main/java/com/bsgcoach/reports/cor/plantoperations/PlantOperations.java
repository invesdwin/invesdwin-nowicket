package com.bsgcoach.reports.cor.plantoperations;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;
import de.invesdwin.util.math.decimal.scaled.Percent;

@NotThreadSafe
public class PlantOperations extends AValueObject {

    private final CompanyRegion companyRegion;

    //    Age of the Plant (in years)
    private Decimal ageOfThePlantInYears;
    //    Plant Capacity-------------
    private final PlantCapacity plantCapacity = new PlantCapacity();
    //    Upgrade Options--------
    private final UpgradeOptions upgradeOptions = new UpgradeOptions();
    //    Investment-----------------
    private final Investment investment = new Investment();
    //    Compenastion--------------
    //    ($ per worker)
    private final CompensationDollarsPerWorker compensationDollarsPerWorker = new CompensationDollarsPerWorker();
    //    Productivity---------------
    //      Factors
    private final ProductivityFactors productivityFactors = new ProductivityFactors();
    //    Productivity Calc---------
    private final ProductivityCalc productivityCalc = new ProductivityCalc();
    //    Workers Employed-------
    private final WorkersEmployed workersEmployed = new WorkersEmployed();
    //    Cumulatives-----------------
    private final Cumulatives cumulatives = new Cumulatives();
    //    Materials Costs----------
    private final MaterialsCosts materialsCosts = new MaterialsCosts();
    //    Branded
    //    Production
    private final Production brandedProduction = new Production();
    //    P-Label
    //    Production
    private final Production pLabelProduction = new Production();

    public PlantOperations(final CompanyRegion companyRegion) {
        this.companyRegion = companyRegion;
    }

    public CompanyRegion getCompanyRegion() {
        return companyRegion;
    }

    public Decimal getAgeOfThePlantInYears() {
        return ageOfThePlantInYears;
    }

    public void setAgeOfThePlantInYears(final Decimal ageOfThePlantInYears) {
        this.ageOfThePlantInYears = ageOfThePlantInYears;
    }

    public PlantCapacity getPlantCapacity() {
        return plantCapacity;
    }

    public UpgradeOptions getUpgradeOptions() {
        return upgradeOptions;
    }

    public Investment getInvestment() {
        return investment;
    }

    public CompensationDollarsPerWorker getCompensationDollarsPerWorker() {
        return compensationDollarsPerWorker;
    }

    public ProductivityFactors getProductivityFactors() {
        return productivityFactors;
    }

    public ProductivityCalc getProductivityCalc() {
        return productivityCalc;
    }

    public WorkersEmployed getWorkersEmployed() {
        return workersEmployed;
    }

    public Cumulatives getCumulatives() {
        return cumulatives;
    }

    public MaterialsCosts getMaterialsCosts() {
        return materialsCosts;
    }

    public Production getBrandedProduction() {
        return brandedProduction;
    }

    public Production getPLabelProduction() {
        return pLabelProduction;
    }

    public Percent getUtilisation() {
        //Branded Production  COR/Plant Operations/Branded Production/Pairs Produced/Total
        final Decimal brandedProduction = getBrandedProduction().getPairsProduced000s().getTotal();
        //Private Label Production    COR/Plant Operations/P-Label Production/Pairs Produced/Total
        final Decimal privateLabelProduction = getPLabelProduction().getPairsProduced000s().getTotal();
        //Current capacity utilisation    =(BRANDED_PRODUCTION(NA) + PLABEL_PRODUCTION(NA))/CAPACITY(NA)
        final Decimal capacity = getPlantCapacity().getCapacity();
        return new Percent(brandedProduction.add(privateLabelProduction), capacity);
    }

}
