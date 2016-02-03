package com.eva.web.dashboard.row;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum Pupil {

    _1("angela.png"),
    _2("arni.png"),
    _3("jovani.png"),
    _4("phillia.png"),
    _5("phillip.png"),
    _6("vidar.png"),
    _7("wladimir.png");

    private String file;

    Pupil(final String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

}
