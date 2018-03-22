package de.invesdwin.nowicket.generated.guiservice;

import java.io.File;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import de.invesdwin.nowicket.generated.guiservice.internal.tasks.IGuiTasksService;

public interface IGuiService extends IGuiTasksService {

    void showModalPanel(Object modelObject);

    void showStatusMessage(String message);

    void showStatusMessage(String title, String message);

    /**
     * This tells the gui tasks to update only the components that they change. All other components need to be updated
     * manually since this will not happen in processReuquestFinally.
     */
    void disableUpdateAllComponentsForCurrentRequest();

    /**
     * This indicates whether components need to be updated manually during this request.
     */
    boolean isDisableUpdateAllComponentsForCurrentRequest();

    /**
     * Executes gui service tasks and updates all components of this has not been skipped. If it was skipped, only the
     * relevant components will be updated.
     */
    void processRequestFinally(Component component);

    void processInitializationFinished(MarkupContainer markupContainer);

    /**
     * Gets the path to the session directory.
     */
    File getSessionFolder();

}
