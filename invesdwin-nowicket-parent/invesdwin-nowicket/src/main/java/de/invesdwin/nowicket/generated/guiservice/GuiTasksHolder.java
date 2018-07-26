package de.invesdwin.nowicket.generated.guiservice;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.norva.marker.ISerializableValueObject;
import de.invesdwin.nowicket.generated.guiservice.internal.tasks.GuiTasks;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.lang.Objects;

/**
 * Stores tasks per page
 * 
 */
@ThreadSafe
public class GuiTasksHolder implements ISerializableValueObject {

    private static final MetaDataKey<GuiTasksHolder> HOLDER_KEY = new MetaDataKey<GuiTasksHolder>() {
    };

    @GuardedBy("this")
    private GuiTasks guiTasks;

    public synchronized GuiTasks getGuiTasks() {
        if (guiTasks == null) {
            guiTasks = new GuiTasks();
        }
        return guiTasks;
    }

    public synchronized void setGuiTasks(final GuiTasks guiTasks) {
        if (guiTasks != null) {
            this.guiTasks = Objects.clone(guiTasks);
        } else {
            this.guiTasks = null;
        }
    }

    public static GuiTasksHolder get() {
        final Page page = RequestCycles.getPage();
        if (page == null) {
            return newGuiTasksHolder();
        }
        return get(page);
    }

    private static GuiTasksHolder newGuiTasksHolder() {
        final RequestCycle requestCycle = RequestCycles.getRequestCycle(null);
        if (requestCycle != null) {
            GuiTasksHolder guiTasksHolder = requestCycle.getMetaData(HOLDER_KEY);
            if (guiTasksHolder == null) {
                guiTasksHolder = new GuiTasksHolder();
                requestCycle.setMetaData(HOLDER_KEY, guiTasksHolder);
            }
            return guiTasksHolder;
        } else {
            return new GuiTasksHolder();
        }
    }

    public static GuiTasksHolder get(final Page page) {
        if (page == null) {
            return newGuiTasksHolder();
        }
        GuiTasksHolder guiTasksHolder = page.getMetaData(HOLDER_KEY);
        if (guiTasksHolder == null) {
            guiTasksHolder = newGuiTasksHolder();
            page.setMetaData(HOLDER_KEY, guiTasksHolder);
        }
        return guiTasksHolder;
    }

}
