package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.awt.Dimension;

import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;

public interface IGuiTasksService {

    void showPage(Object modelObject);

    void showStatusMessage(StatusMessageConfig config);

    /**
     * This puts a modal panel on top of other modal panels.
     */
    void showModalPanel(Object modelObject, Dimension dimension);

    /**
     * Checks if currently a modal panel is showing.
     */
    boolean isModalPanelShowing();

    /**
     * This hides the top most showing modal panel.
     */
    void hideModalPanel();

    void offerDownload(final OfferDownloadConfig config);

}
