package com.bsgcoach.resources.significance;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

@NotThreadSafe
public enum Significance {
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8"),
    _9("9"),
    _10("10");

    private String title;

    Significance(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public ResourceReference getIcon() {
        return new PackageResourceReference(getClass(), "Significance_" + title + ".png");
    }

    public Significance minus(final int minus) {
        final int newOrdinal = Math.max(0, this.ordinal() - minus);
        final Significance newS = Significance.values()[newOrdinal];
        return newS;
    }

    public String getPercent() {
        return title + "0%";
    }

}
