package de.invesdwin.nowicket.generated.guiservice.internal.tasks;

import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;

import de.invesdwin.nowicket.generated.guiservice.GuiService;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.util.Components;

@NotThreadSafe
public class GuiTasks implements IGuiTasksService, IGuiTask {

    private transient ShowPageGuiTask showPageGuiTask;
    private OfferDownloadGuiTask offerDownloadGuiTask;
    private final Deque<ShowModalPanelGuiTask> showModalPanelGuiTasks = new ArrayDeque<ShowModalPanelGuiTask>();
    private WaitForNextAjaxCallGuiTask waitForHideModalPanelGuiTask;
    private final Deque<ShowStatusMessageGuiTask> showStatusMessageGuiTasks = new ArrayDeque<ShowStatusMessageGuiTask>();

    public boolean showPageShouldWaitForNextAjaxCall() {
        return offerDownloadGuiTask != null || !showModalPanelGuiTasks.isEmpty()
                || !showStatusMessageGuiTasks.isEmpty();
    }

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
            final Collection<? extends Component> updatedComponents = lastShowing.hide();
            showModalPanelGuiTasks.remove(lastShowing);
            waitForHideModalPanelGuiTask = new WaitForNextAjaxCallGuiTask();
            if (GuiService.get().isDisableUpdateAllComponentsForCurrentRequest()) {
                Components.updateComponents(updatedComponents);
            }
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
    public Collection<? extends Component> process(final Component component) {
        final Form<?> form = Components.findForm(component);
        if (form == null) {
            //wait for a valid page to execute on
            return Collections.emptyList();
        }

        if (showPageGuiTask != null) {
            final Collection<? extends Component> updatedComponents = showPageGuiTask.process(component);
            showPageGuiTask = null;
            return updatedComponents;
        }
        ShowModalPanelGuiTask firstNotShowing = getFirstNotShowingModelPanel();
        if (waitForHideModalPanelGuiTask != null) {
            try {
                if (firstNotShowing != null) {
                    //skip if this workaround is not needed
                    return waitForHideModalPanelGuiTask.process(component);
                }
            } finally {
                waitForHideModalPanelGuiTask = null;
            }
        }
        final Map<Integer, Component> updatedComponentsIdentity = new LinkedHashMap<Integer, Component>();
        while (firstNotShowing != null) {
            firstNotShowing.process(component);
            firstNotShowing = getFirstNotShowingModelPanel();
        }
        while (!showStatusMessageGuiTasks.isEmpty()) {
            final ShowStatusMessageGuiTask last = showStatusMessageGuiTasks.removeLast();
            final Collection<? extends Component> updatedComponents = last.process(component);
            addIdentityComponents(updatedComponentsIdentity, updatedComponents);
        }
        if (offerDownloadGuiTask != null) {
            final Collection<? extends Component> updatedComponents = offerDownloadGuiTask.process(component);
            addIdentityComponents(updatedComponentsIdentity, updatedComponents);
            offerDownloadGuiTask = null;
            return updatedComponentsIdentity.values();
        }

        return updatedComponentsIdentity.values();
    }

    private void addIdentityComponents(final Map<Integer, Component> updatedComponentsIdentity,
            final Collection<? extends Component> updatedComponents) {
        for (final Component c : updatedComponents) {
            final int identity = System.identityHashCode(c);
            if (!updatedComponentsIdentity.containsKey(identity)) {
                updatedComponentsIdentity.put(identity, c);
            }
        }
    }

    @Override
    public void offerDownload(final OfferDownloadConfig config) {
        this.offerDownloadGuiTask = new OfferDownloadGuiTask(config);
    }

}
