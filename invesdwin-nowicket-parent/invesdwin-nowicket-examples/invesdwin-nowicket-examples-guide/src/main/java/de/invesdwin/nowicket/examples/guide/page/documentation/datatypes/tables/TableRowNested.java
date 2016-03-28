package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class TableRowNested extends AValueObject {

    private final String column3;

    public TableRowNested(final String column3) {
        this.column3 = column3;
    }

    public String getColumn3() {
        return column3;
    }

}
