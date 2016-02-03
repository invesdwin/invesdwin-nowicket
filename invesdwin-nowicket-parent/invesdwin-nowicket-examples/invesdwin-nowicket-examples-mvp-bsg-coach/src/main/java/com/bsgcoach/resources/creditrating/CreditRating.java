package com.bsgcoach.resources.creditrating;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum CreditRating {
    APlus("A+"),
    A("A"),
    AMinus("A-"),
    BPlus("B+"),
    B("B"),
    BMinus("B-"),
    CPlus("C+"),
    C("C"),
    CMinus("C-");

    private String title;

    CreditRating(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
