package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxchoice;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum Manufacturer {

    AUDI("A4", "A6", "TT"),
    FORD("CROWN", "ESCAPE", "EXPEDITION", "EXPLORER", "F-150"),
    CADILLAC("CTS", "DTS", "ESCALADE", "SRX", "DEVILLE");

    private String[] models;

    Manufacturer(final String... models) {
        this.models = models;
    }

    public String[] getModels() {
        return models;
    }

}
