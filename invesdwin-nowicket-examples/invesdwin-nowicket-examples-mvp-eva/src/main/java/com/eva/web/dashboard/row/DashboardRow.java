package com.eva.web.dashboard.row;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class DashboardRow extends AValueObject {

    private final Pupil pupil;

    public DashboardRow(final Pupil pupil) {
        this.pupil = pupil;
    }

    public String rowImg() {
        return pupil.getFile();
    }

}
