package com.bsgcoach.web.request;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum CompanyRegion {
    NorthAmerica("North America", "N.A."),
    EuropeAfrica("Europe-Africa", "E.A."),
    AsiaPacific("Asia-Pacifc", "A.P."),
    LatinAmerica("Latin America", "L.A.");

    private String longTitle;
    private String shortTitle;

    CompanyRegion(final String longTitle, final String shortTitle) {
        this.longTitle = longTitle;
        this.shortTitle = shortTitle;
    }

    @Override
    public String toString() {
        return longTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

}
