package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandpanels;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandchoices.row.ExtendedTableRow;
import de.invesdwin.nowicket.examples.guide.page.documentation.userinterfaceflow.fifthcar.details.tabs.info.CarInfo;
import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.lang.Objects;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class TablesAndPanels extends AValueObject {

    private final List<ExtendedTableRow> choices = new ArrayList<ExtendedTableRow>() {
        {
            for (int i = 1; i <= 3; i++) {
                add(new ExtendedTableRow(i));
            }
        }
    };

    private final List<ExtendedTableRow> defaultTable = Objects.deepClone(choices);
    private final List<Object> asUnorderedList = (List) Objects.deepClone(choices);
    private final List<Object> asCollapsible = (List) Objects.deepClone(choices);
    private final List<Object> asAccordion = (List) Objects.deepClone(choices);
    private final List<Object> asTabbed = (List) Objects.deepClone(choices);

    public TablesAndPanels() {
        /**
         * We don't require static typing for these panel lists, thus you can even mix different types into one list
         */
        asUnorderedList.add(new CarInfo("4-1", "4-2"));
        asCollapsible.add(new CarInfo("4-1", "4-2"));
        asAccordion.add(new CarInfo("4-1", "4-2"));
        asTabbed.add(new CarInfo("4-1", "4-2"));
    }

    public List<ExtendedTableRow> getDefaultTable() {
        return defaultTable;
    }

    public List<Object> getAsCollapsible() {
        return asCollapsible;
    }

    public List<Object> getAsAccordion() {
        return asAccordion;
    }

    public List<Object> getAsUnorderedList() {
        return asUnorderedList;
    }

    public List<?> getAsTabbed() {
        return asTabbed;
    }

}
