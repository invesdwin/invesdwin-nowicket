package de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
public class TableRow extends AValueObject {

    private String column1;
    private String column2;
    private final TableRowNested nested;

    public TableRow(final int index) {
        this.column1 = index + "_1";
        this.column2 = index + "_2";
        this.nested = new TableRowNested(index + "_3");
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(final String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(final String column2) {
        this.column2 = column2;
    }

    public TableRowNested getNested() {
        return nested;
    }

    public void action() {
        GuiService.get().showStatusMessage("Clicked Action", toStringMultiline());
    }

}
