package de.invesdwin.nowicket.generated.guiservice;

import java.io.File;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import de.invesdwin.nowicket.generated.guiservice.internal.tasks.IGuiTasksService;

public interface IGuiService extends IGuiTasksService {

    void showModalPanel(Object modelObject);

    void showStatusMessage(String message);

    void showStatusMessage(String title, String message);

    void processRequestFinally(Component component);

    void processInitializationFinished(MarkupContainer markupContainer);

    /**
     * Gets the path to the session directory.
     */
    File getSessionFolder();

}
