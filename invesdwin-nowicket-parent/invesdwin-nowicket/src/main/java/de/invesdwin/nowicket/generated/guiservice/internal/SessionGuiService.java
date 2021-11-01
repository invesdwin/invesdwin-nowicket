package de.invesdwin.nowicket.generated.guiservice.internal;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.request.RequestHandlerExecutor.ReplaceHandlerException;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.session.ISessionStore.UnboundListener;

import de.invesdwin.nowicket.application.auth.ABaseWebApplication;
import de.invesdwin.nowicket.application.auth.AWebSession;
import de.invesdwin.nowicket.component.modal.ModalConfig;
import de.invesdwin.nowicket.generated.guiservice.GuiTasksHolder;
import de.invesdwin.nowicket.generated.guiservice.IGuiService;
import de.invesdwin.nowicket.generated.guiservice.OfferDownloadConfig;
import de.invesdwin.nowicket.generated.guiservice.StatusMessageConfig;
import de.invesdwin.nowicket.generated.guiservice.internal.tasks.GuiTasks;
import de.invesdwin.nowicket.util.Components;
import de.invesdwin.nowicket.util.RequestCycles;
import de.invesdwin.util.lang.Files;

@NotThreadSafe
public class SessionGuiService implements IGuiService, Serializable {

    private static final MetaDataKey<SessionGuiService> KEY_SESSION_GUI_SERVICE = new MetaDataKey<SessionGuiService>() {
    };
    private static final MetaDataKey<Boolean> KEY_INITIALIZATION_FINISHED_BEHAVIOR_ADDED = new MetaDataKey<Boolean>() {
    };
    private static final MetaDataKey<Boolean> KEY_INITIALIZATION_FINISHED_BEHAVIOR_ALREADY_CONFIGURED = new MetaDataKey<Boolean>() {
    };
    private static final MetaDataKey<Boolean> KEY_DISABLE_UPDATE_ALL_COMPONENTS_FOR_CURRENT_REQUEST = new MetaDataKey<Boolean>() {
    };

    private static final org.slf4j.ext.XLogger LOG = org.slf4j.ext.XLoggerFactory.getXLogger(SessionGuiService.class);

    private File sessionFolder;

    public static SessionGuiService get() {
        SessionGuiService sessionGuiService = AWebSession.get().getMetaData(KEY_SESSION_GUI_SERVICE);
        if (sessionGuiService == null) {
            sessionGuiService = new SessionGuiService();
            AWebSession.get().setMetaData(KEY_SESSION_GUI_SERVICE, sessionGuiService);
        }
        return sessionGuiService;
    }

    @Override
    public synchronized void processInitializationFinished(final MarkupContainer markupContainer) {
        final Component root = Components.findRoot(markupContainer);
        final Boolean behaviorAdded = root.getMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ADDED);
        if (behaviorAdded == null || !behaviorAdded) {
            root.setMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ADDED, true);
            root.add(new Behavior() {

                @Override
                public void onConfigure(final Component component) {
                    super.onConfigure(component);
                    //second safety net is required since the hierarchy might not have been put together properly when the initializationFinished calls were made
                    final Component root = Components.findRoot(component);
                    final Boolean behaviorAlreadyConfigured = root
                            .getMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ALREADY_CONFIGURED);
                    if (behaviorAlreadyConfigured == null || !behaviorAlreadyConfigured) {
                        root.setMetaData(KEY_INITIALIZATION_FINISHED_BEHAVIOR_ALREADY_CONFIGURED, true);
                        //run before first render and execute any tasks that are scheduled directly
                        disableUpdateAllComponentsForCurrentRequest();
                        processRequestFinally(root);
                    }
                }

                @Override
                public boolean isTemporary(final Component component) {
                    return true;
                }

            });
        }
    }

    @Override
    public void disableUpdateAllComponentsForCurrentRequest() {
        final RequestCycle requestCycle = RequestCycle.get();
        if (requestCycle != null) {
            requestCycle.setMetaData(KEY_DISABLE_UPDATE_ALL_COMPONENTS_FOR_CURRENT_REQUEST, true);
        }
    }

    @Override
    public void processRequestFinally(final Component component) {
        try {
            final Collection<? extends Component> updatedComponents = getGuiTasks().process(component);
            final IPartialPageRequestHandler handler = RequestCycles.getPartialPageRequestHandler(component);
            if (isDisableUpdateAllComponentsForCurrentRequest()) {
                Components.updateComponents(handler, updatedComponents);
            } else {
                Components.updateAllComponents(handler, component);
            }
        } catch (final ReplaceHandlerException e) {
            resetGuiTasks(e);
            //rethrow so that redirect can happen
            throw e;
        } catch (final Throwable t) {
            resetGuiTasks(t);
        }
    }

    @Override
    public boolean isDisableUpdateAllComponentsForCurrentRequest() {
        final RequestCycle requestCycle = RequestCycle.get();
        if (requestCycle != null) {
            final Boolean disableUpdateAllComponentsForCurrentRequest = requestCycle
                    .getMetaData(KEY_DISABLE_UPDATE_ALL_COMPONENTS_FOR_CURRENT_REQUEST);
            return BooleanUtils.isTrue(disableUpdateAllComponentsForCurrentRequest);
        } else {
            return false;
        }
    }

    private void resetGuiTasks(final Throwable t) {
        LOG.catching(new RuntimeException(GuiTasks.class.getSimpleName()
                + ".process() threw an exception, resetting everything to keep the overall website working for the next request...",
                t));
        GuiTasksHolder.get().setGuiTasks(null);
    }

    @Override
    public void showPage(final Object modelObject) {
        getGuiTasks().showPage(modelObject);
    }

    @Override
    public void showModalPanel(final Object modelObject, final ModalConfig config) {
        getGuiTasks().showModalPanel(modelObject, config);
    }

    @Override
    public void showStatusMessage(final StatusMessageConfig config) {
        getGuiTasks().showStatusMessage(config);
    }

    @Override
    public boolean isModalPanelShowing() {
        return getGuiTasks().isModalPanelShowing();
    }

    @Override
    public void hideModalPanel() {
        getGuiTasks().hideModalPanel();
    }

    @Override
    public File getSessionFolder() {
        if (sessionFolder == null) {
            sessionFolder = new File(ABaseWebApplication.get().getSessionsDirectory(),
                    getClass().getSimpleName() + "/" + AWebSession.get().getId());
            //clean up folder initially to prevent session clash with stale data
            Files.deleteNative(sessionFolder);
            //add listener to clean up the session directory on session unbind aswell
            final ABaseWebApplication application = ABaseWebApplication.get();
            application.getSessionStore().registerUnboundListener(new UnboundListener() {
                @Override
                public void sessionUnbound(final String sessionId) {
                    if (sessionFolder.getAbsolutePath().contains(sessionId)) {
                        application.getSessionStore().unregisterUnboundListener(this);
                        Files.deleteNative(sessionFolder);
                    }
                }
            });
            try {
                Files.forceMkdir(sessionFolder);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sessionFolder;
    }

    @Override
    public void showModalPanel(final Object modelObject) {
        showModalPanel(modelObject, null);
    }

    @Override
    public void showStatusMessage(final String message) {
        showStatusMessage(null, message);
    }

    @Override
    public void showStatusMessage(final String title, final String message) {
        showStatusMessage(new StatusMessageConfig().setTitle(title).setMessage(message));
    }

    @Override
    public void offerDownload(final OfferDownloadConfig config) {
        getGuiTasks().offerDownload(config);
    }

    public GuiTasks getGuiTasks() {
        return GuiTasksHolder.get().getGuiTasks();
    }
}
