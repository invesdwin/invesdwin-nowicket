package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;

import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;

@NotThreadSafe
public class GuiTasks implements IGuiTasksService, IGuiTask {

    private ShowPageGuiTask showPageGuiTask;
    private final Deque<ShowModalPanelGuiTask> showModalPanelGuiTasks = new ArrayDeque<ShowModalPanelGuiTask>();
    private final Deque<ShowStatusMessageGuiTask> showStatusMessageGuiTasks = new ArrayDeque<ShowStatusMessageGuiTask>();

    @Override
    public void showPage(final Object modelObject) {
        this.showPageGuiTask = new ShowPageGuiTask(modelObject);
    }

    @Override
    public void showModalPanel(final Object modelObject, final Dimension dimension) {
        this.showModalPanelGuiTasks.addLast(new ShowModalPanelGuiTask(modelObject, dimension));
    }

    @Override
    public void showStatusMessage(final StatusMessageConfig config) {
        this.showStatusMessageGuiTasks.addLast(new ShowStatusMessageGuiTask(config));
    }

    @Override
    public boolean isModalPanelShowing() {
        final ShowModalPanelGuiTask lastShowing = getLastShowingModelPanel();
        return lastShowing != null;
    }

    @Override
    public void hideModalPanel() {
        final ShowModalPanelGuiTask lastShowing = getLastShowingModelPanel();
        if (lastShowing != null) {
            lastShowing.hide();
            showModalPanelGuiTasks.remove(lastShowing);
        } else {
            throw new IllegalStateException("No modal panel to hide!");
        }
    }

    private ShowModalPanelGuiTask getLastShowingModelPanel() {
        ShowModalPanelGuiTask lastShowing = null;
        for (final ShowModalPanelGuiTask task : showModalPanelGuiTasks) {
            if (task.isShowing()) {
                lastShowing = task;
            }
        }
        return lastShowing;
    }

    private ShowModalPanelGuiTask getFirstNotShowingModelPanel() {
        for (final ShowModalPanelGuiTask task : showModalPanelGuiTasks) {
            if (!task.isShowing()) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void process(final Component component) {
        if (showPageGuiTask != null) {
            showPageGuiTask.process(component);
            showPageGuiTask = null;
            return;
        }
        ShowModalPanelGuiTask firstNotShowing = getFirstNotShowingModelPanel();
        while (firstNotShowing != null) {
            firstNotShowing.process(component);
            firstNotShowing = getFirstNotShowingModelPanel();
        }
        while (!showStatusMessageGuiTasks.isEmpty()) {
            final ShowStatusMessageGuiTask last = showStatusMessageGuiTasks.removeLast();
            last.process(component);
        }
    }
}
