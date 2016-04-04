package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxdatatable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.binding.annotation.Eager;
import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class AjaxDataTable extends AValueObject {

    private final List<AjaxDataTableRow> rowsSelectionChoice = new ArrayList<AjaxDataTableRow>();
    private Set<AjaxDataTableRow> rowsSelection = new HashSet<AjaxDataTableRow>();

    public AjaxDataTable() {
        regenerateRows();
    }

    @Eager
    public Set<AjaxDataTableRow> getRows() {
        return this.rowsSelection;
    }

    public void setRows(final Set<AjaxDataTableRow> rowsSelection) {
        this.rowsSelection = rowsSelection;
    }

    public List<AjaxDataTableRow> getRowsChoice() {
        return rowsSelectionChoice;
    }

    public void regenerateRows() {
        rowsSelectionChoice.clear();
        new AjaxDataTableRowGenerator().generate(rowsSelectionChoice, 31);
    }

    /**
     * here we demonstrate how to use the remove from utility element
     */
    public void removeFromRows(final AjaxDataTableRow row) {
        rowsSelection.remove(row);
        rowsSelectionChoice.remove(row);
        GuiService.get().showStatusMessage(new StatusMessageConfig().withType(StatusMessageType.info)
                .withTitle("Removed")
                .withMessage(row.toString()));
    }

    public String getSelected() {
        final StringBuilder sb = new StringBuilder();
        int selectedCount = 0;
        for (final AjaxDataTableRow row : rowsSelection) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append(row.toString());
            selectedCount++;
        }
        if (sb.length() == 0) {
            sb.append("No Contact Selected");
        } else {
            sb.insert(0, "(" + selectedCount + ") ");
        }
        return sb.toString();
    }

    public void removeSelected() {
        final StringBuilder sb = new StringBuilder();
        for (final AjaxDataTableRow row : rowsSelection) {
            if (sb.length() > 0) {
                sb.append("<br>");
            }
            sb.append(row.toString());
            rowsSelectionChoice.remove(row);
        }
        rowsSelection.clear();
        GuiService.get().showStatusMessage(new StatusMessageConfig().withType(StatusMessageType.info)
                .withTitle("Removed")
                .withMessage(sb.toString()));
    }

    public String disableRemoveSelected() {
        if (rowsSelection.isEmpty()) {
            return "None selected";
        } else {
            return null;
        }
    }

    public void selectAll() {
        rowsSelection.addAll(rowsSelectionChoice);
    }

    public String disableSelectAll() {
        if (rowsSelection.size() == rowsSelectionChoice.size()) {
            return "Already all selected";
        } else {
            return null;
        }
    }

    public void unselectAll() {
        rowsSelection.clear();
    }

    public String disableUnselectAll() {
        if (rowsSelection.isEmpty()) {
            return "Already all unselected";
        } else {
            return null;
        }
    }

}
