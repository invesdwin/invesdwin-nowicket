package de.invesdwin.nowicket.generated.guiservice;

import java.io.File;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import de.invesdwin.nowicket.generated.guiservice.internal.tasks.IGuiTasksService;

public interface IGuiService extends IGuiTasksService {

    void showModalPanel(final Object modelObject);

    void showStatusMessage(final String message);

    void showStatusMessage(final String title, final String message);

    void processRequestFinally(Component component);

    void processInitializationFinished(MarkupContainer markupContainer);

    /**
     * Gets the path to the session directory.
     */
    File getSessionFolder();

}
