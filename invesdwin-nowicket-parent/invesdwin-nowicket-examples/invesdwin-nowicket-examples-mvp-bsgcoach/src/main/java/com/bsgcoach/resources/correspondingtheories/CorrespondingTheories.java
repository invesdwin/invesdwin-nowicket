package com.bsgcoach.resources.correspondingtheories;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum CorrespondingTheories {

    PlantCapacity("Plant-Capacity", "#"),
    CapacityUtilization("Capacity-Utilization", "#"),
    PlantUpgrades("Plant-Upgrades", "#"),
    TotalQualityManagement("Total-Quality-Management", "#"),
    BestPracticesTraining("Best-Practices-Training", "#"),
    NumberOfModels("Number-Of-Models", "#"),
    SQRating("SQ-Rating", "#"),
    CostsOfRejects("Costs-Of-Rejects", "#"),
    DebtEquityRatio("Debt-Equity-Ratio", "#");

    private String title;
    private String readMoreUrl;

    CorrespondingTheories(final String title, final String readMoreUrl) {
        this.title = title;
        this.readMoreUrl = readMoreUrl;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getReadMoreUrl() {
        return readMoreUrl;
    }
}
