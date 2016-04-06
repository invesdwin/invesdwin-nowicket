package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.collapsibles;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tables.row.ExtendedTableRow;
import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class Collapsibles extends AValueObject {

    private final List<ExtendedTableRow> choices = new ArrayList<ExtendedTableRow>() {
        {
            for (int i = 1; i <= 3; i++) {
                add(new ExtendedTableRow(i));
            }
        }
    };

    private final List<ExtendedTableRow> defaultTable = Objects.deepClone(choices);
    private final List<ExtendedTableRow> asUnorderedList = Objects.deepClone(choices);
    private final List<ExtendedTableRow> asCollapsible = Objects.deepClone(choices);
    private final List<ExtendedTableRow> asAccordion = Objects.deepClone(choices);

    public List<ExtendedTableRow> getDefaultTable() {
        return defaultTable;
    }

    public List<ExtendedTableRow> getAsCollapsible() {
        return asCollapsible;
    }

    public List<ExtendedTableRow> getAsAccordion() {
        return asAccordion;
    }

    public List<ExtendedTableRow> getAsUnorderedList() {
        return asUnorderedList;
    }

}
