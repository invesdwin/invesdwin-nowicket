package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tables.row;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.datatypes.tables.TableRow;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;

@GeneratedMarkup
@NotThreadSafe
public class ExtendedTableRow extends TableRow {

    public ExtendedTableRow(final int index) {
        super(index);
    }

    /**
     * We could also have overridden toString, as that is the default fallback if no container title method exists
     */
    public String title() {
        return TableRow.class.getSimpleName() + "-" + getColumn1() + "-" + getColumn2() + "-"
                + getNested().getColumn3();
    }

}
