package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import de.invesdwin.nowicket.examples.guide.page.wicket.ajaxtimer.tab.table.AjaxTimerTable;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@GeneratedMarkup
@NotThreadSafe
public class AjaxTimerTab extends AValueObject {

    @NotBlank
    @NotNull
    private String requiredInput;
    private List<AjaxTimerTable> tables;

    public AjaxTimerTab(final String id) {
        setRequiredInput(id);
        tables = new ArrayList<>();
        tables.add(new AjaxTimerTable(id + "-1", 100));
        tables.add(new AjaxTimerTable(id + "-2", 10));
    }

    public String getRequiredInput() {
        return requiredInput;
    }

    public void setRequiredInput(final String requiredInput) {
        this.requiredInput = requiredInput;
    }

    public List<AjaxTimerTable> getTables() {
        return tables;
    }

    public void setTables(final List<AjaxTimerTable> tables) {
        this.tables = tables;
    }

    public void save() {}

    @Override
    public String toString() {
        return requiredInput;
    }

}
