package de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandchoices;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.examples.guide.page.documentation.tagtransformations.tablesandchoices.row.ExtendedTableRow;
import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
@Eager
public class TablesAndChoices extends AValueObject {

    private final List<ExtendedTableRow> choices = new ArrayList<ExtendedTableRow>() {
        {
            for (int i = 1; i <= 3; i++) {
                add(new ExtendedTableRow(i));
            }
            add(null); // allow empty choice
        }
    };

    private ExtendedTableRow defaultTable;
    private ExtendedTableRow asSelect;
    private ExtendedTableRow asList;
    private ExtendedTableRow asRadio;
    private ExtendedTableRow asCheckbox;
    private List<ExtendedTableRow> asMultiselectTable;
    private List<ExtendedTableRow> asMultiselectList;
    private List<ExtendedTableRow> asMultiselectPalette;
    private List<ExtendedTableRow> asMultiselectCheckbox;

    public ExtendedTableRow getDefaultTable() {
        return defaultTable;
    }

    public void setDefaultTable(final ExtendedTableRow defaultSelect) {
        this.defaultTable = defaultSelect;
    }

    public List<ExtendedTableRow> getDefaultTableChoice() {
        return choices;
    }

    public ExtendedTableRow getAsSelect() {
        return asSelect;
    }

    public void setAsSelect(final ExtendedTableRow asSelect) {
        this.asSelect = asSelect;
    }

    public List<ExtendedTableRow> getAsSelectChoice() {
        return choices;
    }

    public ExtendedTableRow getAsList() {
        return asList;
    }

    public void setAsList(final ExtendedTableRow asList) {
        this.asList = asList;
    }

    public List<ExtendedTableRow> getAsListChoice() {
        return choices;
    }

    public ExtendedTableRow getAsRadio() {
        return asRadio;
    }

    public void setAsRadio(final ExtendedTableRow asRadio) {
        this.asRadio = asRadio;
    }

    public List<ExtendedTableRow> getAsRadioChoice() {
        return choices;
    }

    public ExtendedTableRow getAsCheckbox() {
        return asCheckbox;
    }

    public void setAsCheckbox(final ExtendedTableRow asCheckbox) {
        this.asCheckbox = asCheckbox;
    }

    public List<ExtendedTableRow> getAsCheckboxChoice() {
        return choices;
    }

    public List<ExtendedTableRow> getAsMultiselectTable() {
        return asMultiselectTable;
    }

    public void setAsMultiselectTable(final List<ExtendedTableRow> asTable) {
        this.asMultiselectTable = asTable;
    }

    public List<ExtendedTableRow> getAsMultiselectTableChoice() {
        return choices;
    }

    public List<ExtendedTableRow> getAsMultiselectList() {
        return asMultiselectList;
    }

    public void setAsMultiselectList(final List<ExtendedTableRow> asMultiselectList) {
        this.asMultiselectList = asMultiselectList;
    }

    public List<ExtendedTableRow> getAsMultiselectListChoice() {
        return choices;
    }

    public List<ExtendedTableRow> getAsMultiselectPalette() {
        return asMultiselectPalette;
    }

    public void setAsMultiselectPalette(final List<ExtendedTableRow> asMultiselectPalette) {
        this.asMultiselectPalette = asMultiselectPalette;
    }

    public List<ExtendedTableRow> getAsMultiselectPaletteChoice() {
        return choices;
    }

    public List<ExtendedTableRow> getAsMultiselectCheckbox() {
        return asMultiselectCheckbox;
    }

    public void setAsMultiselectCheckbox(final List<ExtendedTableRow> asMultiselectCheckbox) {
        this.asMultiselectCheckbox = asMultiselectCheckbox;
    }

    public List<ExtendedTableRow> getAsMultiselectCheckboxChoice() {
        return choices;
    }

}
