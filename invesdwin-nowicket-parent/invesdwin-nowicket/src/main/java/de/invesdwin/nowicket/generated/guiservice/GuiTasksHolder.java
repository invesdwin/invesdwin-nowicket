package de.invesdwin.nowicket.generated.guiservice;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;

import de.invesdwin.norva.marker.ISerializableValueObject;
import de.invesdwin.nowicket.generated.guiservice.internal.tasks.GuiTasks;
import de.invesdwin.util.lang.Objects;

/**
 * Stores tasks per page
 * 
 */
@ThreadSafe
public class GuiTasksHolder implements ISerializableValueObject {

    private static final MetaDataKey<Page> PAGE_KEY = new MetaDataKey<Page>() {
    };
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
        final Page page = getPage();
        if (page == null) {
            return new GuiTasksHolder();
        }
        return get(page);
    }

    public static Page getPage() {
        Page page = RequestCycle.get().getMetaData(PAGE_KEY);
        if (page != null) {
            return page;
        }
        final IPageRequestHandler lastHandler = PageRequestHandlerTracker.getLastHandler(RequestCycle.get());
        if (lastHandler == null) {
            return null;
        }
        page = (Page) lastHandler.getPage();
        setPage(page);
        return page;
    }

    public static GuiTasksHolder get(final Page page) {
        if (page == null) {
            return new GuiTasksHolder();
        }
        GuiTasksHolder guiTasksHolder = page.getMetaData(HOLDER_KEY);
        if (guiTasksHolder == null) {
            guiTasksHolder = new GuiTasksHolder();
            page.setMetaData(HOLDER_KEY, guiTasksHolder);
        }
        return guiTasksHolder;
    }

    public static void setPage(final Page page) {
        final Page existingPage = RequestCycle.get().getMetaData(PAGE_KEY);
        if (existingPage != null && existingPage != page) {
            get(page).setGuiTasks(get(existingPage).getGuiTasks());
        }
        RequestCycle.get().setMetaData(PAGE_KEY, page);
    }

}
