package com.eva.web.decision;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum CompanyType {
    JointStock("Joint Stock"),
    Limited("Limited"),
    SoleProprietorship("Sole Proprietorship");

    private String text;

    CompanyType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
