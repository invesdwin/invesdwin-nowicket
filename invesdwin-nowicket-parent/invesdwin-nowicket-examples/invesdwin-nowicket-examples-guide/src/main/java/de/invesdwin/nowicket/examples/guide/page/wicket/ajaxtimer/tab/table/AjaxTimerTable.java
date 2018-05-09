package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab.table;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class AjaxTimerTable extends AValueObject {

    private final List<AjaxTimerTableRow> rows = new ArrayList<>();

    public AjaxTimerTable(final String id, final int countRows) {
        for (int i = 0; i < countRows; i++) {
            final String iStr = String.valueOf(i);
            rows.add(new AjaxTimerTableRow(iStr, id + "_" + iStr));
        }
    }

    public List<AjaxTimerTableRow> getRows() {
        return rows;
    }

    public void button() {

    }

}
