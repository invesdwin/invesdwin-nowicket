package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import de.invesdwin.nowicket.component.modal.ModalConfig;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;

public interface IGuiTasksService {

    void showPage(Object modelObject);

    void showStatusMessage(StatusMessageConfig config);

    /**
     * This puts a modal panel on top of other modal panels.
     */
    void showModalPanel(Object modelObject, ModalConfig config);

    /**
     * Checks if currently a modal panel is showing.
     */
    boolean isModalPanelShowing();

    /**
     * This hides the top most showing modal panel.
     * 
     * @return the updated components (they will be updated during processRequestFinally, or have to be manually updated
     *         if that is not being called by custom code)
     */
    void hideModalPanel();

    void offerDownload(OfferDownloadConfig config);

}
