package de.invesdwin.nowicket.examples.guide.page.wicket.ajaxdatatable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageType;
import de.invesdwin.nowicket.generated.markup.annotation.GeneratedMarkup;
import de.invesdwin.util.bean.AValueObject;

@NotThreadSafe
@GeneratedMarkup
public class AjaxDataTable extends AValueObject {

    private final List<AjaxDataTableRow> rows = new ArrayList<AjaxDataTableRow>();

    public AjaxDataTable() {
        regenerateRows();
    }

    public List<AjaxDataTableRow> getRows() {
        return rows;
    }

    public void regenerateRows() {
        rows.clear();
        new AjaxDataTableRowGenerator().generate(rows, 31);
    }

    /**
     * here we demonstrate how to use the remove from utility element
     */
    public void removeFromRows(final AjaxDataTableRow row) {
        rows.remove(row);
        GuiService.get().showStatusMessage(new StatusMessageConfig().withType(StatusMessageType.info)
                .withTitle("Removed")
                .withMessage(row.toString()));
    }

    public String getSelected() {
        final StringBuilder sb = new StringBuilder();
        int selectedCount = 0;
        for (final AjaxDataTableRow row : getRows()) {
            if (row.isSelected()) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append(row.toString());
                selectedCount++;
            }
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
        for (final AjaxDataTableRow row : new ArrayList<AjaxDataTableRow>(getRows())) {
            if (row.isSelected()) {
                if (sb.length() > 0) {
                    sb.append("<br>");
                }
                sb.append(row.toString());
                rows.remove(row);
            }
        }
        GuiService.get().showStatusMessage(new StatusMessageConfig().withType(StatusMessageType.info)
                .withTitle("Removed")
                .withMessage(sb.toString()));
    }

    public String disableRemoveSelected() {
        for (final AjaxDataTableRow row : getRows()) {
            if (row.isSelected()) {
                return null;
            }
        }
        return "None selected";
    }

    public void selectAll() {
        for (final AjaxDataTableRow row : getRows()) {
            row.setSelected(true);
        }
    }

    public String disableSelectAll() {
        for (final AjaxDataTableRow row : getRows()) {
            if (!row.isSelected()) {
                return null;
            }
        }
        return "Already all selected";
    }

    public void unselectAll() {
        for (final AjaxDataTableRow row : getRows()) {
            row.setSelected(false);
        }
    }

    public String disableUnselectAll() {
        for (final AjaxDataTableRow row : getRows()) {
            if (row.isSelected()) {
                return null;
            }
        }
        return "Already all unselected";
    }

}
