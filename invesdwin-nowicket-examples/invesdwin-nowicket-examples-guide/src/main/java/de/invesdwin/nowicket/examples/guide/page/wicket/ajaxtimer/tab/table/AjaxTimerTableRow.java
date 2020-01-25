package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab.table;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class AjaxTimerTableRow extends AValueObject {

    private final String key;
    private final String value;

    public AjaxTimerTableRow(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
